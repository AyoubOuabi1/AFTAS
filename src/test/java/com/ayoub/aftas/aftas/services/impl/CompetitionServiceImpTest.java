package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.respositories.CompetitionRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CompetitionServiceImpTest {

    @Mock
    private CompetitionRepository competitionRepository;

    @InjectMocks
    private CompetitionServiceImp competitionService;
    @Test
    void save_validCompetitionDto_returnsCompetitionDto() {
        CompetitionDto competitionDto = buildValidCompetitionDto();
        List<Competition> existingCompetitions = Arrays.asList(CompetitionMapper.mapFromDto(buildExistingCompetitionDto()));

        when(competitionRepository.findAll()).thenReturn(existingCompetitions);
        when(competitionRepository.save(any(Competition.class))).thenReturn(buildSavedCompetition());

        CompetitionDto savedCompetitionDto = competitionService.save(competitionDto);

        assertNotNull(savedCompetitionDto);
    }

    @Test
    void save_createCompetitionBefore48h_throwsInternalServerError() {
         CompetitionDto competitionDto = buildValidCompetitionDto();
        competitionDto.setDate(LocalDate.now().plus(1, ChronoUnit.DAYS));
        assertThrows(InternalServerError.class, () -> competitionService.save(competitionDto));
    }
    private CompetitionDto buildValidCompetitionDto() {
        return CompetitionDto.builder()
                .id(1L)
                .date(LocalDate.now().plus(3, ChronoUnit.DAYS))
                .startTime(Time.valueOf(LocalTime.of(10, 0)))
                .endTime(Time.valueOf(LocalTime.of(12, 0)))
                .numberOfParticipants(50)
                .location("FES")
                .status("open")
                .amount(100.0f)
                .build();
    }

    private CompetitionDto buildExistingCompetitionDto() {
        return CompetitionDto.builder()
                .id(2L)
                .code("FES-2023-01-02")
                .date(LocalDate.now().plus(5, ChronoUnit.DAYS))
                .startTime(Time.valueOf(LocalTime.of(14, 0)))
                .endTime(Time.valueOf(LocalTime.of(16, 0)))
                .numberOfParticipants(75)
                .location("FES")
                .status("open")
                .amount(150.0f)
                .build();
    }

    private Competition buildSavedCompetition() {
        CompetitionDto competitionDto = buildValidCompetitionDto();
        return CompetitionMapper.mapFromDtoWithoutId(competitionDto);
    }

}