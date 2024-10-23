package org.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.JourFerie;
import org.example.service.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/")
public class JourFerieController {


    private final JourFerieService jourFerieService;


    public JourFerieController(JourFerieService jourFerieService) {
        this.jourFerieService = jourFerieService;
    }

    @GetMapping("")
    public ResponseEntity<List<JourFerie>> getJourFeries() {
        return ResponseEntity.ok(jourFerieService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<JourFerie> postJourFerie(@RequestBody JourFerie jourFerie) {
        JourFerie jourFerie1 = jourFerieService.create(jourFerie);
        return ResponseEntity.ok(jourFerie1);
    }

    @GetMapping("jours-feries")
    public String getJoursFeries(@RequestParam("zone") String zone, @RequestParam("annee") String annee) {
        return jourFerieService.recupererJoursFeries(zone, annee);
    }

    @PostMapping("jours-feries-me")
    public ResponseEntity<List<JourFerie>> postJoursFeriesFromApi(@RequestParam("annee") String annee) throws JsonProcessingException {
        jourFerieService.enregistrerJoursFeries(annee);
        return ResponseEntity.ok(jourFerieService.findAll());
    }

}
