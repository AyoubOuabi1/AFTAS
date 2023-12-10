package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Competition;

import java.util.List;

public interface CompetitionService {

    Competition save(Competition competition);

    Competition update(Competition competition);

    void delete(Competition competition);

    List<Competition> getAll();

    Competition getById(Long id);

}