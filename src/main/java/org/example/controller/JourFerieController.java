package org.example.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.entity.DepartementEnum;
import org.example.entity.JourFerie;
import org.example.entity.ZoneEnum;
import org.example.service.JourFerieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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

    @GetMapping("/alsace-moselle")
    public List<Map<String, Object>> getJoursFeriesAlsaceMoselle() {
        // Appelle le service pour récupérer les jours fériés avec les départements associés à la zone ALSACE_MOSELLE
        return jourFerieService.getJoursFeriesAvecZone(ZoneEnum.ALSACE_MOSELLE);
    }

    @GetMapping("/departementCode")
    public List<JourFerie> getJoursFeriesParDepartementCode(@RequestParam("code") String codeDepartement) {
        // Récupère l'énumération du département à partir du code
        DepartementEnum departement = Arrays.stream(DepartementEnum.values())
                .filter(dept -> dept.getCode().equals(codeDepartement))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Département non trouvé avec le code : " + codeDepartement));

        // Appelle le service pour récupérer les jours fériés liés à ce département
        return jourFerieService.getJoursFeriesParDepartement(departement);
    }

    @GetMapping("/departementNom")
    public List<JourFerie> getJoursFeriesParDepartementName(@RequestParam("nom") String nomDepartement) {
        // Récupère l'énumération du département à partir du code
        DepartementEnum departement = Arrays.stream(DepartementEnum.values())
                .filter(dept -> dept.getNom().equals(nomDepartement))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Département non trouvé avec le nom : " + nomDepartement));

        // Appelle le service pour récupérer les jours fériés liés à ce département
        return jourFerieService.getJoursFeriesParDepartement(departement);
    }


}
