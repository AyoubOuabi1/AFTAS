package com.ayoub.aftas.aftas.entities;

import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "competitions")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

    private String status;

    @JsonIgnore
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @JsonIgnore
    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL)
    private List<Hunting> hunts;
}

