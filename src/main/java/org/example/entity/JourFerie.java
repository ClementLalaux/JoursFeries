package org.example.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class JourFerie {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String date;

        private String description;

        @Enumerated(EnumType.STRING)
        private Zone zone;

        @ManyToMany(fetch = FetchType.LAZY)
        @JoinTable(
                name = "jour_ferie_departement",
                joinColumns = @JoinColumn(name = "jour_ferie_id"),
                inverseJoinColumns = @JoinColumn(name = "departement_id")
        )
        @JsonBackReference
        private Set<Departement> departements = new HashSet<>();

    public JourFerie() {
    }


// getters, setters, constructors

        public enum Zone {
            METROPOLE, ALSACE_LORRAINE, ILES
        }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public Set<Departement> getDepartements() {
        return departements;
    }

    public void setDepartements(Set<Departement> departements) {
        this.departements = departements;
    }

    public void addDepartement(Departement departement) {
        this.departements.add(departement);
    }

    public JourFerie(String date, String description) {
        this.date = date;
        this.description = description;
    }

    public JourFerie(String date, String description, Zone zone) {
        this.date = date;
        this.description = description;
        this.zone = zone;
    }
}
