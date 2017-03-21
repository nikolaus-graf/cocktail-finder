package org.graf;

import org.graf.model.Cocktail;
import org.graf.model.CocktailKarte;
import org.graf.model.Zutat;
import org.junit.Test;

import java.util.Arrays;


public class CocktailFinderApplicationTests {

    @Test
    public void test() {
        Zutat zucker = new Zutat("Zucker");
        Zutat cola = new Zutat("Cola");
        Zutat wodka = new Zutat("Wodka");
        Zutat lime = new Zutat("Lime");
        Zutat orange = new Zutat("Orange");


        Cocktail longIsland = new Cocktail("Long Island Ice Tea").addZutat(zucker).addZutat(cola).addZutat(wodka).addZutat(lime);
        Cocktail screwDriver = new Cocktail("Screw Driver").addZutat(orange).addZutat(wodka);

        CocktailKarte cocktailKarte = new CocktailKarte();
        cocktailKarte.addCocktail(longIsland);
        cocktailKarte.addCocktail(screwDriver);

        cocktailKarte.findCocktailsWithZutaten(Arrays.asList(wodka.getZutat(), cola.getZutat())).forEach(System.out::println);

    }

}

