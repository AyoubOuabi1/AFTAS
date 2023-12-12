package com.ayoub.aftas.aftas.dto;

import com.ayoub.aftas.aftas.entities.Level;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
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
public class FishDto {

    @Nullable
    private Long id;

    @NotBlank(message = "name cannot be blank")
    private String name;


    @Positive(message = "Average Weight must be a positive number")
    private float averageWeight;

    @NotNull(message = "Level ID cannot be null")
    Long levelId;

    @Nullable
    Level level;
}
