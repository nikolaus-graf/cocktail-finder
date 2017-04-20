package org.graf.web.controller;

import org.graf.model.Cocktail;
import org.graf.model.Zutat;
import org.graf.services.CocktailService;
import org.graf.web.formbeans.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static org.graf.web.formbeans.Result.SUCCESS;
import static org.springframework.util.StringUtils.isEmpty;

@RestController
public class CocktailDataController {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    private final CocktailService cocktailService;


    @Autowired
    public CocktailDataController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/data/cocktails")
    public CocktailTableInfo getCocktails(@RequestParam(value = "zutaten[]", required = false) String[] zutaten) {
        return new CocktailTableInfo(mapToTableData(cocktailService.findCocktailsWithZutaten(zutaten == null ? emptyList() : asList(zutaten))));
    }

    @GetMapping("/data/zutaten")
    public ZutatTableInfo getZutaten() {
        return new ZutatTableInfo(cocktailService.findAllZutaten().stream()
                .sorted(comparing(Zutat::getName))
                .map(zutat -> new String[]{zutat.getName(), Boolean.toString(zutat.hasCocktails())})
                .collect(toList()));
    }

    @PutMapping("zutaten/data/zutat")
    public Result saveZutat(@RequestBody ZutatInfo zutatInfo) {
        if (isEmpty(zutatInfo.getName())) {
            return new Result("Kein Name gesetzt");
        }
        try {
            cocktailService.ensureZutatExists(zutatInfo.getName());
        } catch (Exception ex) {
            logger.error("unable to save zutat {}", zutatInfo.toString(), ex);
            return new Result(ex.getMessage());
        }
        return SUCCESS;
    }

    @DeleteMapping("zutaten/data/zutat/{name}")
    public Result removeZutat(@PathVariable(value = "name") String name) {
        try {
            cocktailService.removeZutat(name);
        } catch (Exception ex) {
            logger.error("unable to remove zutat {}", name, ex);
            return new Result(ex.getMessage());
        }
        return SUCCESS;
    }

    @PutMapping("cocktails/data/cocktail")
    public Result saveCocktail(@RequestBody CocktailInfo cocktailInfo) {
        if (isEmpty(cocktailInfo.getName())) {
            return new Result("Kein Name gesetzt");
        }
        if (cocktailInfo.getZutaten() == null || cocktailInfo.getZutaten().length < 2) {
            return new Result("Es müssen mindestens zwei Zutaten ausgewält sein");
        }
        try {
            cocktailService.saveCocktail(cocktailInfo.getName(), asList(cocktailInfo.getZutaten()));
        } catch (Exception ex) {
            logger.error("unable to save cocktail {}", cocktailInfo.toString(), ex);
            return new Result(ex.getMessage());
        }
        return SUCCESS;
    }

    @DeleteMapping("cocktails/data/cocktail/{name}")
    public Result removeCocktail(@PathVariable(value = "name") String name) {
        try {
            cocktailService.removeCocktail(name);
        } catch (Exception ex) {
            logger.error("unable to remove cocktail {}", name, ex);
            return new Result(ex.getMessage());
        }
        return SUCCESS;
    }

    private List<String[]> mapToTableData(List<Cocktail> cocktailsWithZutaten) {
        return cocktailsWithZutaten.stream()
                .map(cocktail -> new String[]{cocktail.getName(), cocktail.zutatenAsString()})
                .collect(toList());
    }
}
