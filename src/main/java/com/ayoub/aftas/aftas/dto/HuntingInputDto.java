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
    @Positive(message = "number of fish must be greater or equal to 0")
    private Integer numberOfFish;

    @NotNull(message = "Member ID cannot be null")
    private Long memberId;

    @NotNull(message = "Competition ID cannot be null")
    private Long competitionId;

    @NotNull(message = "Competition ID cannot be null")
    private Long fishId;

}
