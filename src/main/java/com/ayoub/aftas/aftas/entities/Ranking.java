package com.ayoub.aftas.aftas.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rankings")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Ranking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer rank;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")

    private Competition competition;

 }
