package com.ayoub.aftas.aftas.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RankingDto {

    @NotNull(message = "Competition ID cannot be null")
    Long competitionId;

    @NotNull(message = "Member ID cannot be null")
    Long memberId;
}
