package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Competition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CompetitionService {

    Competition save(Competition competition);

    Competition update(Competition competition);

    void delete(Competition competition);

    List<Competition> getAll();

    Competition getById(Long id);

}