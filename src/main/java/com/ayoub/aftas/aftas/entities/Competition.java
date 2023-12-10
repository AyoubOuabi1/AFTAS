package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "competitions")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private LocalDate date;

    private Time startTime;

    private Time endTime;

    private Integer numberOfParticipants;

    private String location;

    private float amount;



    // Relationship with Ranking entity
    @OneToMany(mappedBy = "competition",cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "competition",cascade = CascadeType.ALL)
    private List<Hunting> hunts;

    // Getters and setters
}
