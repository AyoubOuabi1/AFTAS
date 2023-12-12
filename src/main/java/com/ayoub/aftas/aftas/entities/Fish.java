package com.ayoub.aftas.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Table(name = "fishes")
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Fish {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private float averageWeight;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @JsonIgnore
    @OneToMany(mappedBy = "fish" ,cascade = CascadeType.ALL)
    private List<Hunting> hunts;
 }
