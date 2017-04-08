package org.graf.model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "CocktailKarte")
public class CocktailKarte extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cocktailKarte", cascade = ALL, orphanRemoval = true, fetch = EAGER)
    private List<Cocktail> cocktails = new ArrayList<>();

    public CocktailKarte() {

    }

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
        cocktail.setCocktailKarte(this);
    }

    public List<Zutat> getAllUsedZutaten() {
        return cocktails.stream()
                .flatMap(cocktail -> cocktail.getZutaten().stream())
                .distinct()
                .sorted(comparing(Zutat::getName))
                .collect(toList());
    }

    public List<Cocktail> findCocktailsWithZutaten(List<String> zutaten) {
        return cocktails.stream()
                .filter(cocktail -> cocktail.containsAllZutaten(zutaten))
                .sorted(comparing(Cocktail::getName))
                .collect(toList());
    }

    public void removeCocktail(String name) {
        Cocktail cocktail = cocktails.stream()
                .filter(c -> name.equals(c.getName()))
                .findFirst().orElse(null);
        if(cocktail != null){
            cocktails.remove(cocktail);

        }
    }
}
