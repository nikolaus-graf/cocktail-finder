package org.graf.services;

import javax.annotation.PostConstruct;

import org.graf.model.Cocktail;
import org.graf.model.CocktailKarte;
import org.graf.model.Zutat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class DbInitializerService {
	
	private final CocktailService cocktailService;
	
	@Autowired
	public DbInitializerService( CocktailService cocktailService){
		this.cocktailService = cocktailService;
	}
	
	@PostConstruct
	@Transactional
	public void initializeDatabase(){
		Zutat rum = cocktailService.ensureZutatExists("Rum");
		Zutat cola = cocktailService.ensureZutatExists("Cola");
		Zutat limette = cocktailService.ensureZutatExists("Limette");
		Zutat tequila = cocktailService.ensureZutatExists("Tequila");
		Zutat wodka =  cocktailService.ensureZutatExists("Wodka");
		Zutat orangenSaft = cocktailService.ensureZutatExists("Orangen Saft");				
		Zutat ananasSaft = cocktailService.ensureZutatExists("Ananas Saft");
		Zutat amaretto = cocktailService.ensureZutatExists("Amaretto");
		
		CocktailKarte cocktailKarte = new CocktailKarte();
		cocktailKarte.addCocktail(new Cocktail("Cuba Libre", cola, rum, limette));
		cocktailKarte.addCocktail(new Cocktail("Long Island Ice Tea", cola, rum, tequila, wodka, limette));
		cocktailKarte.addCocktail(new Cocktail("Hurricane", orangenSaft, ananasSaft, rum, limette, amaretto));
		
		cocktailService.save(cocktailKarte);		
	}

}
