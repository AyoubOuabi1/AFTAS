package com.ayoub.aftas.aftas.dto;

import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.entities.Hunting;
import com.ayoub.aftas.aftas.entities.Member;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    private Member member;

    private Competition competition;

    private Fish fish;
}
