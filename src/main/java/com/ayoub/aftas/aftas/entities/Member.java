package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer num;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;
    private String identityDocument;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Hunting> hunts;
    // Getters and setters
}
