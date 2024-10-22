package org.example.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String code; // Code du département (ex. "75" pour Paris)

    private String nom; // Nom du département (ex. "Paris")

    // Relation avec les jours fériés
    @ManyToMany(mappedBy = "departements")
    @JsonManagedReference
    private Set<JourFerie> joursFeries = new HashSet<>();

    // Getters, setters, et constructeurs
    public Departement() {
    }

    public Departement(String code, String nom) {
        this.code = code;
        this.nom = nom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Set<JourFerie> getJoursFeries() {
        return joursFeries;
    }

    public void addJoursFeries(JourFerie jourFerie) {
        this.joursFeries.add(jourFerie);
    }



    public void setJoursFeries(Set<JourFerie> joursFeries) {
        this.joursFeries = joursFeries;
    }
}
