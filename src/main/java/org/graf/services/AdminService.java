package org.graf.services;

import org.graf.model.Zutat;
import org.graf.repositories.ZutatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
public class AdminService {

    private final ZutatRepository zutatRepository;

    @Autowired
    public AdminService(ZutatRepository zutatRepository) {
        this.zutatRepository = zutatRepository;
    }

    @Transactional
    public List<Zutat> findAllZutaten() {
        return zutatRepository.findAll().stream()
                .sorted(comparing(Zutat::getName))
                .collect(toList());
    }

    @Transactional
    public Zutat saveZutat(String name) {
        Zutat zutat = new Zutat(name);
        return zutatRepository.save(zutat);
    }
}
