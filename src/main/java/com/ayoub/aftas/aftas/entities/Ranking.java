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
    @EmbeddedId()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private RankId id;

    private Integer rank;

    private Integer score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionId")
    private Competition competition;

 }
