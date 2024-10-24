package org.example.repository;

import org.example.entity.JourFerie;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourFerieRepository extends CrudRepository<JourFerie, Long> {
    boolean existsByDate(String date);
}
