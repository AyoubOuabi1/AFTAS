package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "levels")
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer code;

    private String description;

    private Integer points;

    // Relationship with Fish entity
    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;

    // Getters and setters
}
