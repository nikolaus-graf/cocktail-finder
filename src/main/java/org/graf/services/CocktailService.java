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
    public Set<Zutat> getAllZutaten() {
        return findCocktailKarte().getAllZutaten();
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
    public Zutat ensureZutatExists(String zutat) {
        Zutat zutatDb = zutatRepository.findByZutat(zutat);
        if (zutatDb == null) {
            zutatDb = new Zutat(zutat);
            zutatDb = zutatRepository.save(zutatDb);
        }
        return zutatDb;
    }

    @Transactional
    public void save(CocktailKarte cocktailKarte) {
        cocktailKarteRepository.save(cocktailKarte);
    }
}
