package org.graf.repositories;

import org.graf.model.Zutat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZutatRepository extends JpaRepository<Zutat, Long> {

    Zutat findByZutat(String zutat);

}
