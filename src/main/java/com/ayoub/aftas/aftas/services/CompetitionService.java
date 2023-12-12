package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {

    CompetitionDto save(CompetitionDto competition) throws NotFoundException;

    CompetitionDto update(CompetitionDto competition) throws NotFoundException;

    void delete(Long id) throws NotFoundException;

    List<CompetitionDto> getAll();

    CompetitionDto getById(Long id) throws NotFoundException;

}