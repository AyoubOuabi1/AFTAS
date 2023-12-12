package com.ayoub.aftas.aftas.dto;

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
public class HuntingInputDto {
    @Nullable
    private Long id;

    @NotNull(message = "Member ID cannot be null")
    private Long memberId;

    @NotNull(message = "Competition ID cannot be null")
    private Long competitionId;

    @NotNull(message = "Fish ID cannot be null")
    private Long fishId;

    @Positive(message = "Average Weight must be a positive number")
    private float averageWeight;

}
