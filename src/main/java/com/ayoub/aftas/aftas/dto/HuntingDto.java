package com.ayoub.aftas.aftas.dto;

import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HuntingDto {

    private Long id;

    private Integer numberOfFish;

    private User member;

    private Competition competition;

    private Fish fish;
}
