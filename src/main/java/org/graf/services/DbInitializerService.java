package org.graf.services;

import org.graf.model.Cocktail;
import org.graf.model.CocktailKarte;
import org.graf.model.Zutat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static java.util.Arrays.asList;

@Component
public class DbInitializerService {

    private final CocktailService cocktailService;
    private final UserService userService;

    @Autowired
    public DbInitializerService(CocktailService cocktailService, UserService userService) {
        this.cocktailService = cocktailService;
        this.userService = userService;
    }

    @PostConstruct
    @Transactional
    public void initializeDatabase() {
        Zutat rum = cocktailService.ensureZutatExists("Rum");
        Zutat cola = cocktailService.ensureZutatExists("Cola");
        Zutat limette = cocktailService.ensureZutatExists("Limette");
        Zutat tequila = cocktailService.ensureZutatExists("Tequila");
        Zutat wodka = cocktailService.ensureZutatExists("Wodka");
        Zutat orangenSaft = cocktailService.ensureZutatExists("Orangen Saft");
        Zutat ananasSaft = cocktailService.ensureZutatExists("Ananas Saft");
        Zutat amaretto = cocktailService.ensureZutatExists("Amaretto");
        Zutat minze = cocktailService.ensureZutatExists("Minze");
        Zutat braunerZucker = cocktailService.ensureZutatExists("Brauner Zucker");
        Zutat soda = cocktailService.ensureZutatExists("Soda");
        Zutat cachaca = cocktailService.ensureZutatExists("Cachaca");

        CocktailKarte cocktailKarte = new CocktailKarte();
        cocktailKarte.addCocktail(new Cocktail("Cuba Libre", asList(cola, rum, limette)));
        cocktailKarte.addCocktail(new Cocktail("Long Island Ice Tea", asList(cola, rum, tequila, wodka, limette)));
        cocktailKarte.addCocktail(new Cocktail("Hurricane", asList(orangenSaft, ananasSaft, rum, limette, amaretto)));
        cocktailKarte.addCocktail(new Cocktail("Mojito", asList(braunerZucker, rum, limette, minze, soda)));
        cocktailKarte.addCocktail(new Cocktail("Caipirinha", asList(braunerZucker, cachaca, limette)));

        cocktailService.save(cocktailKarte);

        userService.create("admin", new BCryptPasswordEncoder().encode("admin"));
    }

}
