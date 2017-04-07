package org.graf.web.controller;

import org.graf.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ZutatenController {

    private final CocktailService cocktailService;

    @Autowired
    public ZutatenController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @RequestMapping("/zutaten")
    public ModelAndView zutaten() {
        Map<String, Object> attributeMap = new HashMap<>();

        attributeMap.put("zutaten", cocktailService.findAllZutaten());

        return new ModelAndView("zutaten", attributeMap);
    }
}
