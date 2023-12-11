package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "fishes")
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float averageWeight;

    // Relationship with Level entity
    @OneToMany(mappedBy = "fish" ,cascade = CascadeType.ALL)
    private List<Level> levels;

 }
