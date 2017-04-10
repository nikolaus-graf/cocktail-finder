package org.graf.web.controller;

import org.graf.model.Cocktail;
import org.graf.model.Zutat;
import org.graf.services.CocktailService;
import org.graf.web.formbeans.CocktailInfo;
import org.graf.web.formbeans.CocktailTableInfo;
import org.graf.web.formbeans.ZutatInfo;
import org.graf.web.formbeans.ZutatTableInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@RestController
public class CocktailDataController {

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
    public void saveZutat(@RequestBody ZutatInfo zutatInfo) {
        cocktailService.ensureZutatExists(zutatInfo.getName());
    }

    @DeleteMapping("zutaten/data/zutat/{name}")
    public void removeZutat(@PathVariable(value="name") String name){
        cocktailService.removeZutat(name);
    }

    @PutMapping("cocktails/data/cocktail")
    public void saveCocktail(@RequestBody CocktailInfo cocktailInfo) {
        cocktailService.saveCocktail(cocktailInfo.getName(), asList(cocktailInfo.getZutaten()));
    }

    @DeleteMapping("cocktails/data/cocktail/{name}")
    public void removeCocktail(@PathVariable(value="name") String name){
        cocktailService.removeCocktail(name);
    }

    private List<String[]> mapToTableData(List<Cocktail> cocktailsWithZutaten) {
        return cocktailsWithZutaten.stream()
                .map(cocktail -> new String[]{cocktail.getName(), cocktail.zutatenAsString()})
                .collect(toList());
    }
}
