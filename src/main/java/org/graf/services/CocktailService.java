package org.graf.services;

import org.graf.model.Cocktail;
import org.graf.model.CocktailKarte;
import org.graf.model.Zutat;
import org.graf.repositories.CocktailKarteRepository;
import org.graf.repositories.ZutatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class CocktailService {

    private final CocktailKarteRepository cocktailKarteRepository;
    private final ZutatRepository zutatRepository;

    @Autowired
    public CocktailService(CocktailKarteRepository cocktailKarteRepository, ZutatRepository zutatRepository) {
        this.cocktailKarteRepository = cocktailKarteRepository;
        this.zutatRepository = zutatRepository;
    }

    @Transactional(readOnly = true)
    public Set<Zutat> getAllUsedZutaten() {
        return findCocktailKarte().getAllUsedZutaten();
    }

    @Transactional(readOnly = true)
    public List<Zutat> findAllZutaten() {
        return zutatRepository.findAll().stream()
                .sorted(comparing(Zutat::getName))
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public CocktailKarte findCocktailKarte() {
        return cocktailKarteRepository.findAll().stream()
                .findFirst().orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Cocktail> findCocktailsWithZutaten(List<String> zutaten) {
        return findCocktailKarte().findCocktailsWithZutaten(zutaten);
    }

    @Transactional
    public Zutat ensureZutatExists(String name) {
        Zutat zutat = zutatRepository.findByName(name);
        if (zutat == null) {
            zutat = new Zutat(name);
            zutat = zutatRepository.save(zutat);
        }
        return zutat;
    }

    @Transactional
    public void save(CocktailKarte cocktailKarte) {
        cocktailKarteRepository.save(cocktailKarte);
    }

    @Transactional
    public void saveCocktail(String name, List<String> zutatenName) {
        List<Zutat> zutaten = zutatenName.stream()
                .map(zutat -> zutatRepository.findByName(zutat))
                .collect(toList());
        findCocktailKarte().addCocktail(new Cocktail(name, zutaten));
    }
}
