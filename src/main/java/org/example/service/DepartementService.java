package org.example.service;

import org.example.entity.Departement;
import org.example.repository.DepartementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class DepartementService {

    @Autowired
    private DepartementRepository departementRepository;

    public Departement findById(Long id) {
        return departementRepository.findById(id).orElse(null);
    }

    public Departement findByCode(String code) {
        return departementRepository.findByCode(code).get();
    }

    public List<Departement> findAll() {
        return (List<Departement>) departementRepository.findAll();
    }
}
