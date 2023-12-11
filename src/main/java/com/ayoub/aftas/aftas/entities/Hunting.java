package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "hunts")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Hunting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numberOfFish;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    private Competition competition;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "fish_id")
    private Fish fish;
}

