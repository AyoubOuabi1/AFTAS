package com.ayoub.aftas.aftas.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "levels")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Nullable
    private Long id;

    private Integer code;

    private String description;

    private Integer points;

    @JsonIgnore
    @OneToMany(mappedBy = "level" ,cascade = CascadeType.ALL)
    private List<Fish> fishs;

}
