package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Ranking;

import java.util.List;

public interface RankingService {
    Ranking save(Ranking ranking);
    Ranking update(Ranking ranking);
    void delete(Ranking ranking);
    List<Ranking> getAll();
    Ranking getById(Long id);
}
