package org.graf.web.controller;

import org.graf.services.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class HomeController {

    private final CocktailService cocktailService;

    @Autowired
    public HomeController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        Map<String, Object> attributeMap = new HashMap<>();

        attributeMap.put("zutaten", cocktailService.findAllUsedZutaten());

        return new ModelAndView("home", attributeMap);
    }
}
