package org.example.repository;

import org.example.entity.Departement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartementRepository extends CrudRepository<Departement,Long> {
    Optional<Departement> findByCode(String code);

    List<Departement> findByCodeIn(List<String> codes);
}
