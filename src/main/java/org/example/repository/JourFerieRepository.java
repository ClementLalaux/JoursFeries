package org.example.repository;

import org.example.entity.JourFerie;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface JourFerieRepository extends CrudRepository<JourFerie, Long> {
    boolean existsByDate(LocalDate date);

}
