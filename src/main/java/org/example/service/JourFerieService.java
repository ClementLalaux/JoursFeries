package org.example.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.entity.JourFerie;
import org.example.entity.DepartementEnum;
import org.example.entity.ZoneEnum;
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
            //
            return objectMapper.readValue(response, HashMap.class);
        } catch (RestClientException | JsonProcessingException e) {
            System.err.println("Erreur lors de l'appel de l'API pour la zone : " + zone);
            e.printStackTrace();
        }
        return null;
    }

    public void enregistrerJoursFeries(String annee) {
        // Récupère toutes les zones définies dans l'énumération
        for (ZoneEnum zone : ZoneEnum.values()) {
            Map<String, String> joursFeries = recupererJoursFeriesDeZone(zone.getApiName(), annee);
            if (joursFeries != null) {
                for (Map.Entry<String, String> entry : joursFeries.entrySet()) {
                    LocalDate date = LocalDate.parse(entry.getKey());
                    String description = entry.getValue();

                    if (!existsByDate(date)) {
                        // Crée et enregistre le jour férié
                        JourFerie jourFerie = new JourFerie(date, description, zone);
                        create(jourFerie);
                    }
                }
            }
        }
    }

    public boolean existsByDate(LocalDate date) {
        return jourFerieRepository.existsByDate(date);
    }

    public List<Map<String, Object>> getJoursFeriesAvecZone(ZoneEnum zone) {
        // Récupère les jours fériés par zone
        List<JourFerie> joursFeries = jourFerieRepository.findByZone(zone);

        // Filtre les départements associés à la zone
        List<DepartementEnum> departements = Arrays.stream(DepartementEnum.values())
                .filter(dept -> dept.getZone() == zone)
                .toList();

        // Crée une liste pour stocker les résultats
        List<Map<String, Object>> resultats = new ArrayList<>();

        // Parcourt chaque jour férié et associe les départements
        for (JourFerie jourFerie : joursFeries) {
            Map<String, Object> jourFerieAvecDepartements = new HashMap<>();
            jourFerieAvecDepartements.put("jourFerie", jourFerie);
            jourFerieAvecDepartements.put("departements", departements);

            resultats.add(jourFerieAvecDepartements);
        }

        return resultats;
    }

    public List<JourFerie> getJoursFeriesParDepartement(DepartementEnum departement) {
        // Récupère les jours fériés en fonction de la zone associée au département
        List<JourFerie> joursFeriesDeLaZone = jourFerieRepository.findByZone(departement.getZone());

        // Retourne tous les jours fériés trouvés pour la zone du département
        return joursFeriesDeLaZone;
    }


}
