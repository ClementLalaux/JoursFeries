package org.example.repository;

import org.example.entity.JourFerie;
import org.example.entity.ZoneEnum;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface JourFerieRepository extends CrudRepository<JourFerie, Long> {
    boolean existsByDate(LocalDate date);
    List<JourFerie> findByZone(ZoneEnum zone);

}
