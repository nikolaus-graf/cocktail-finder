package org.graf.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.EAGER;

@Entity
@Table(name = "Zutat", uniqueConstraints = {@UniqueConstraint(name = "uq_zutat_zutat", columnNames = {"name"})})
public class Zutat extends AbstractBaseEntity {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "zutaten", fetch = EAGER)
    private List<Cocktail> cocktails = new ArrayList<>();

    public Zutat() {

    }

    public Zutat(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addCocktail(Cocktail cocktail) {
        cocktails.add(cocktail);
    }

    public boolean hasCocktails(){
        return !cocktails.isEmpty();
    }

    @Override
    public String toString() {
        return "Zutat [name=" + name + "]";
    }



}
