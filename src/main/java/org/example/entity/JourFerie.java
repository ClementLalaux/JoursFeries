package org.example.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "jour_ferie")
public class JourFerie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "description", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "zone", nullable = false)
    private DepartementEnum zone; // Zone associée


    // Constructeurs
    public JourFerie() {
    }

    public JourFerie(LocalDate date, String description, DepartementEnum zone) {
        this.date = date;
        this.description = description;
        this.zone = zone;
    }

    public JourFerie(LocalDate date, String description, DepartementEnum zone, String departements) {
        this.date = date;
        this.description = description;
        this.zone = zone;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public DepartementEnum getZone() {
        return zone;
    }

    public void setZone(DepartementEnum zone) {
        this.zone = zone;
    }


    @Override
    public String toString() {
        return "JourFerie{" +
                "id=" + id +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", zone=" + zone +
                '}';
    }
}
