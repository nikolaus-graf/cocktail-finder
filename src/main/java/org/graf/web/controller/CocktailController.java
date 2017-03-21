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
public class CocktailController {

	private final CocktailService cocktailService;

	@Autowired
	public CocktailController(CocktailService cocktailService) {
		this.cocktailService = cocktailService;
	}	

	@RequestMapping("/")
	public ModelAndView filter(@ModelAttribute Filter filter) {
		Map<String, Object> attributeMap = new HashMap<>();

		attributeMap.put("zutaten", cocktailService.getAllZutaten());

		return new ModelAndView("cocktails", attributeMap);
	}
}
