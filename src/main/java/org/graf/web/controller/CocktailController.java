package org.graf.web.controller;

import org.graf.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class CocktailController {

    private final CocktailService cocktailService;

    @Autowired
    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        Map<String, Object> attributeMap = new HashMap<>();

        attributeMap.put("zutaten", cocktailService.getAllZutaten());

        return new ModelAndView("cocktails", attributeMap);
    }

    @RequestMapping("/admin")
    public ModelAndView admin() {
        Map<String, Object> attributeMap = new HashMap<>();

        return new ModelAndView("admin", attributeMap);
    }
}
