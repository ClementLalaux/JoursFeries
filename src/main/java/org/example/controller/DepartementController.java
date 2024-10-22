package org.example.controller;

import org.example.entity.Departement;
import org.example.entity.JourFerie;
import org.example.service.DepartementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dep/")
public class DepartementController {

    @Autowired
    private DepartementService departementService;

    @GetMapping("")
    public ResponseEntity<List<Departement>> getDepartements() {
        return ResponseEntity.ok(departementService.findAll());
    }

    @GetMapping("code")
    public ResponseEntity<Departement> getDepartementsByCode(@RequestParam("code") String code) {
        return ResponseEntity.ok(departementService.findByCode(code));
    }
}
