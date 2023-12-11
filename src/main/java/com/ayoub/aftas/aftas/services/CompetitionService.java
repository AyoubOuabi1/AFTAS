package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.Config.exceptions.competition.CompetitionNotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {

    CompetitionDto save(CompetitionDto competition) throws CompetitionNotFoundException;

    CompetitionDto update(CompetitionDto competition) throws CompetitionNotFoundException;

    void delete(Long id) throws CompetitionNotFoundException;

    List<CompetitionDto> getAll();

    CompetitionDto getById(Long id) throws CompetitionNotFoundException;

}