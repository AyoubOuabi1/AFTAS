package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Ranking;
import com.ayoub.aftas.aftas.respositories.RankingRepository;
import com.ayoub.aftas.aftas.services.RankingService;

import java.util.List;

public class RankingServiceImp implements RankingService {

    RankingRepository rankingRepository;

    public RankingServiceImp(RankingRepository rankingRepository){
        this.rankingRepository = rankingRepository;
    }

    @Override
    public Ranking save(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking update(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public void delete(Ranking ranking) {
        rankingRepository.delete(ranking);
    }

    @Override
    public List<Ranking> getAll() {
        return rankingRepository.findAll();
    }

    @Override
    public Ranking getById(Long id) {
        return rankingRepository.findById(id).get();
    }
}
