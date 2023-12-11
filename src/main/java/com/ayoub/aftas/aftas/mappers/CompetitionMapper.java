package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.Competition;

public class CompetitionMapper {

    public static CompetitionDto mapToDto(Competition competition) {
        return CompetitionDto.builder()
                .id(competition.getId())
                .code(competition.getCode())
                .date(competition.getDate())
                .startTime(competition.getStartTime())
                .endTime(competition.getEndTime())
                .numberOfParticipants(competition.getNumberOfParticipants())
                .location(competition.getLocation())
                .amount(competition.getAmount())
                .status(competition.getStatus())
                .build();
    }


    public static Competition mapFromDto(CompetitionDto competitionDto) {
        return Competition.builder()
                .id(competitionDto.getId())
                .code(competitionDto.getCode())
                .date(competitionDto.getDate())
                .startTime(competitionDto.getStartTime())
                .endTime(competitionDto.getEndTime())
                .numberOfParticipants(competitionDto.getNumberOfParticipants())
                .location(competitionDto.getLocation())
                .amount(competitionDto.getAmount())
                .status(competitionDto.getStatus())
                .build();
    }

    public static Competition mapFromDtoWithoutId(CompetitionDto competitionDto) {
        return Competition.builder()
                .code(competitionDto.getCode())
                .date(competitionDto.getDate())
                .startTime(competitionDto.getStartTime())
                .endTime(competitionDto.getEndTime())
                .numberOfParticipants(competitionDto.getNumberOfParticipants())
                .location(competitionDto.getLocation())
                .amount(competitionDto.getAmount())
                .status(competitionDto.getStatus())
                .build();
    }
}
