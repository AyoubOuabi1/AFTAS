package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;

@Entity

@Table(name = "rankings")

public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rank;

    private Integer score;

    // Relationship with Member entity
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    // Relationship with Competition entity
    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    // Getters and setters
}
