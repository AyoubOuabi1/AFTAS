package com.ayoub.aftas.aftas.dto;

import com.ayoub.aftas.aftas.helpers.CustomTimeDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
 import java.sql.Time;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CompetitionDto {
    @Nullable
    private Long id;

    @Nullable
    private String code;

    @NotNull(message = "Date cannot be null")
    private LocalDate date;

    @NotNull(message = "Start time cannot be null")
    @JsonDeserialize(using = CustomTimeDeserializer.class)
    private Time startTime;

    @NotNull(message = "End time cannot be null")
    @JsonDeserialize(using = CustomTimeDeserializer.class)
    private Time endTime;

    @NotNull(message = "Number of participants cannot be null")
    @Positive(message = "Number of participants must be a positive number")
    private Integer numberOfParticipants;

    @NotBlank(message = "Location cannot be blank")
    private String location;

    @Nullable
    private String status;

    @Positive(message = "Amount must be a positive number")
    private float amount;
}
