package org.graf.web.controller;

import org.graf.model.Cocktail;
import org.graf.services.CocktailService;
import org.graf.web.formbeans.CocktailData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

@RestController
public class CocktailDataController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailDataController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/data/cocktails")
    public CocktailData getCocktails(@RequestParam(value = "zutaten[]", required = false) String[] zutaten) {
        return new CocktailData(mapToTableData(cocktailService.findCocktailsWithZutaten(zutaten == null ? emptyList() : asList(zutaten))));
    }

    private List<String[]> mapToTableData(List<Cocktail> cocktailsWithZutaten) {
        return cocktailsWithZutaten.stream()
                .map(cocktail -> new String[]{cocktail.getName(), cocktail.zutatenAsString()})
                .collect(toList());
    }
}
