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
    @JoinColumn(name = "member_id")
    @MapsId("memberId")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "competition_id")
    @MapsId("competitionId")
    private Competition competition;

 }
