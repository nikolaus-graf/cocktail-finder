package org.graf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.joining;
import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "Cocktail", uniqueConstraints = {@UniqueConstraint(name = "uq_cocktail_name", columnNames = {"name"})})
public class Cocktail extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @ManyToMany(fetch = EAGER)
    @JoinTable(name = "cocktail_zutat", joinColumns = {@JoinColumn(name = "cocktail_id", foreignKey = @ForeignKey(name = "fk_cocktail_zutat_cocktail_id"))},
            inverseJoinColumns = {@JoinColumn(name = "zutat_id", foreignKey = @ForeignKey(name = "fk_cocktail_zutat_zutat_id"))})
    private List<Zutat> zutaten = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cocktailkarte_id", foreignKey = @ForeignKey(name = "fk_cocktail_cocktailkarte_id"))
    private CocktailKarte cocktailKarte;

    @Deprecated
    public Cocktail() {
        //JPA use only
    }

    public Cocktail(String name, List<Zutat> zutaten) {
        this.name = name;
        zutaten.forEach(zutat -> addZutat(zutat));
    }

    public String getName() {
        return name;
    }

    public Cocktail setName(String name) {
        this.name = name;
        return this;
    }

    public List<Zutat> getZutaten() {
        return unmodifiableList(zutaten);
    }

    public Cocktail addZutat(Zutat zutat) {
        zutaten.add(zutat);
        zutat.addCocktail(this);
        return this;
    }

    public CocktailKarte getCocktailKarte() {
        return cocktailKarte;
    }

    public void setCocktailKarte(CocktailKarte cocktailKarte) {
        this.cocktailKarte = cocktailKarte;
    }

    public boolean containsAllZutaten(List<String> zutatenList) {
        for (String zutat : zutatenList) {
            boolean found = this.zutaten.stream()
                    .map(Zutat::getName)
                    .anyMatch(match -> zutat.equals(match));
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public String zutatenAsString() {
        return this.zutaten.stream()
                .map(Zutat::getName)
                .sorted()
                .collect(joining(", "));
    }

    @Override
    public String toString() {
        return "Cocktail [name=" + name + ", zutaten=" + zutaten + "]";
    }


}
