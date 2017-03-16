package org.graf.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.graf.model.CocktailKarte;
import org.graf.services.CocktailService;
import org.graf.web.formbeans.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private final CocktailService cocktailService;

	@Autowired
	public HomeController(CocktailService cocktailService) {
		this.cocktailService = cocktailService;
	}	

	@RequestMapping("/")
	public ModelAndView filter(@ModelAttribute Filter filter) {
		Map<String, Object> attributeMap = new HashMap<>();
		CocktailKarte cocktailKarte = cocktailService.findCocktailKarte();
		attributeMap.put("cocktails", cocktailKarte.findCocktailsWithZutaten(filter.getZutaten()));
		attributeMap.put("allZutaten", cocktailKarte.getAllZutaten());
		attributeMap.put("filter", filter);		
		attributeMap.put("filterAsString", filter.filterString());

		return new ModelAndView("index", attributeMap);
	}
}
