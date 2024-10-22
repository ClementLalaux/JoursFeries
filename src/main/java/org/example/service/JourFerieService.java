package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.Departement;
import org.example.entity.JourFerie;
import org.example.repository.DepartementRepository;
import org.example.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class JourFerieService {


    @Autowired
    private JourFerieRepository jourFerieRepository;

    @Autowired
    private DepartementService departementService;

    private final RestTemplate restTemplate;
    @Autowired
    private DepartementRepository departementRepository;

    public JourFerieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JourFerie create(JourFerie jourFerie){
        return jourFerieRepository.save(jourFerie);
    }

    public List<JourFerie> findAll(){
        return (List<JourFerie>) jourFerieRepository.findAll();
    }

    public JourFerie findById(Long id){
        return jourFerieRepository.findById(id).get();
    }

    public String recupererJoursFeries(String zone, String annee) {
        String url = String.format("https://calendrier.api.gouv.fr/jours-feries/%s/%s.json", zone, annee);
        return restTemplate.getForObject(url, String.class);
    }

    public void recupererJoursFeriesTouteRegion(String annee) {
        List<String> zones = Arrays.asList("metropole", "alsace-moselle", "guadeloupe", "guyane", "la-reunion", "martinique", "mayotte");

        for (String zone : zones) {
            try {
                Map<String, String> joursFeriesMap = recupererJoursFeriesDeZone(zone, annee);
                if (joursFeriesMap != null) {
                    traiterJoursFeries(joursFeriesMap, zone);
                }
            } catch (Exception e) {
                System.err.println("Erreur inattendue lors du traitement de la zone : " + zone);
                e.printStackTrace();
            }
        }
    }

    private Map<String, String> recupererJoursFeriesDeZone(String zone, String annee) {
        String url = String.format("https://calendrier.api.gouv.fr/jours-feries/%s/%s.json", zone, annee);
        String response;

        try {
            response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, HashMap.class);
        } catch (RestClientException e) {
            System.err.println("Erreur lors de l'appel de l'API pour la zone : " + zone);
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            System.err.println("Erreur de conversion JSON pour la zone : " + zone);
            e.printStackTrace();
        }

        return null;
    }

    private void traiterJoursFeries(Map<String, String> joursFeriesMap, String zone) {
        for (Map.Entry<String, String> entry : joursFeriesMap.entrySet()) {
            try {
                JourFerie jourFerie = new JourFerie(entry.getKey(), entry.getValue());
                if (!existsByDate(jourFerie.getDate())) {
                    if (zone.equals("metropole")) {
                        jourFerie.setZone(JourFerie.Zone.METROPOLE);
                    } else if (zone.equals("alsace-moselle")) {
                        traiterAlsaceLorraine(jourFerie);
                    } else {
                        traiterAutresIles(jourFerie, zone);
                    }
                    jourFerieRepository.save(jourFerie);
                }
            } catch (DataAccessException e) {
                System.err.println("Erreur lors de l'enregistrement d'un jour férié pour la zone : " + zone);
                e.printStackTrace();
            }
        }
    }

    private void traiterAlsaceLorraine(JourFerie jourFerie) {
        jourFerie.setZone(JourFerie.Zone.ALSACE_LORRAINE);
        Set<Departement> departementsAlsaceLorraine = new HashSet<>(departementRepository.findByCodeIn(Arrays.asList("57", "67", "68")));
        jourFerie.setDepartements(departementsAlsaceLorraine);

        // Utiliser une liste pour stocker les départements à sauvegarder
        List<Departement> departementsToSave = new ArrayList<>();

        for (Departement d : departementsAlsaceLorraine) {
            if (!d.getJoursFeries().contains(jourFerie)) { // Vérifie que le jour férié n'est pas déjà lié
                d.getJoursFeries().add(jourFerie);
                jourFerie.addDepartement(d);
                departementsToSave.add(d); // Ajoute le département à la liste pour la sauvegarde
            }
        }

        // Sauvegarder tous les départements à la fin
        try {
            departementRepository.saveAll(departementsToSave);
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de la sauvegarde des départements pour la zone Alsace-Lorraine");
            e.printStackTrace();
        }
    }

    private void traiterAutresIles(JourFerie jourFerie, String zone) {
        jourFerie.setZone(JourFerie.Zone.ILES);
        Departement departement = switch (zone) {
            case "guadeloupe" -> departementService.findByCode("971");
            case "guyane" -> departementService.findByCode("973");
            case "la-reunion" -> departementService.findByCode("974");
            case "martinique" -> departementService.findByCode("972");
            case "mayotte" -> departementService.findByCode("976");
            default -> new Departement(); // Valeur par défaut si aucun code ne correspond
        };
        departement.addJoursFeries(jourFerie);
        jourFerie.addDepartement(departement);
        try {
            departementRepository.save(departement);
        } catch (DataAccessException e) {
            System.err.println("Erreur lors de la sauvegarde du département pour la zone : " + zone);
            e.printStackTrace();
        }
    }

    public boolean existsByDate(String date) {
        return jourFerieRepository.existsByDate(date);
    }
}
