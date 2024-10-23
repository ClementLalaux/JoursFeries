package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.JourFerie;
import org.example.entity.DepartementEnum;
import org.example.repository.JourFerieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.*;

@Service
public class JourFerieService {

    @Autowired
    private JourFerieRepository jourFerieRepository;

    private final RestTemplate restTemplate;

    public JourFerieService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public JourFerie create(JourFerie jourFerie) {
        return jourFerieRepository.save(jourFerie);
    }

    public List<JourFerie> findAll() {
        return (List<JourFerie>) jourFerieRepository.findAll();
    }

    public JourFerie findById(Long id) {
        return jourFerieRepository.findById(id).orElse(null);
    }

    public String recupererJoursFeries(String zone, String annee) {
        String url = String.format("https://calendrier.api.gouv.fr/jours-feries/%s/%s.json", zone, annee);
        return restTemplate.getForObject(url, String.class);
    }

    private Map<String, String> recupererJoursFeriesDeZone(String zone, String annee) {
        String url = String.format("https://calendrier.api.gouv.fr/jours-feries/%s/%s.json", zone, annee);
        try {
            String response = restTemplate.getForObject(url, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, HashMap.class);
        } catch (RestClientException | JsonProcessingException e) {
            System.err.println("Erreur lors de l'appel de l'API pour la zone : " + zone);
            e.printStackTrace();
        }
        return null;
    }

    public void recupererJoursFeriesTouteRegion(String annee) {
        List<String> zones = Arrays.asList("metropole", "alsace-moselle", "guadeloupe", "guyane", "la-reunion", "martinique", "mayotte");

        for (String zone : zones) {
            try {
                Map<String, String> joursFeriesMap = recupererJoursFeriesDeZone(zone, annee);
                if (joursFeriesMap != null) {
                    //traiterJoursFeries(joursFeriesMap, zone);
                }
            } catch (Exception e) {
                System.err.println("Erreur inattendue lors du traitement de la zone : " + zone);
                e.printStackTrace();
            }
        }
    }

//    private void traiterJoursFeries(Map<String, String> joursFeriesMap, String zone) {
//        for (Map.Entry<String, String> entry : joursFeriesMap.entrySet()) {
//            try {
//                LocalDate date = LocalDate.parse(entry.getKey());
//                String departements = ""; // Initialiser la chaîne des départements
//                JourFerie jourFerie = new JourFerie(date, entry.getValue(), null, departements);
//
//                if (!existsByDate(date)) {
//                    if (zone.equals("metropole")) {
//                        jourFerie.setZone(DepartementEnum.METROPOLE); // Remplacez par la zone appropriée
//                    } else if (zone.equals("alsace-moselle")) {
//
//                        departements = traiterAlsaceLorraine(jourFerie);
//                    } else {
//                        departements = traiterAutresIles(jourFerie, zone);
//                    }
//                    jourFerieRepository.save(jourFerie);
//                }
//            } catch (DataAccessException e) {
//                System.err.println("Erreur lors de l'enregistrement d'un jour férié pour la zone : " + zone);
//                e.printStackTrace();
//            }
//        }
//    }
//
//    private void traiterAlsaceLorraine(JourFerie jourFerie) {
//
//    }
//
//    private void traiterAutresIles(JourFerie jourFerie, String zone) {
//        switch (zone) {
//            case "guadeloupe":
//                jourFerie.setZone(DepartementEnum.GUADELOUPE);
//            case "guyane":
//                jourFerie.setZone(DepartementEnum.GUYANE);
//            case "la-reunion":
//                jourFerie.setZone(DepartementEnum.REUNION);
//            case "martinique":
//                jourFerie.setZone(DepartementEnum.MARTINIQUE);
//            case "mayotte":
//                jourFerie.setZone(DepartementEnum.MAYOTTE);
//            default:
//                break;
//        }
//    }

    public boolean existsByDate(LocalDate date) {
        return jourFerieRepository.existsByDate(date);
    }
}
