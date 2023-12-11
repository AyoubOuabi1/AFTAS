package com.ayoub.aftas.aftas.entities;

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

    @OneToMany(mappedBy = "fish" ,cascade = CascadeType.ALL)
    private List<Level> levels;

 }
