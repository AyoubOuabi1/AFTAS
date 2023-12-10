package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.respositories.CompetitionRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompetitionServiceImp implements CompetitionService{

    CompetitionRepository competitionRepository;

    CompetitionServiceImp(CompetitionRepository competitionRepository){
        this.competitionRepository = competitionRepository;
    }

    @Override
    public Competition save(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public Competition update(Competition competition) {
        return competitionRepository.save(competition);
    }

    @Override
    public void delete(Competition competition) {
        competitionRepository.delete(competition);
    }

    @Override
    public List<Competition> getAll() {
        return competitionRepository.findAll();
    }

    @Override
    public Competition getById(Long id) {
        if(id != null){
            return competitionRepository.findById(id).get();
        }
        return null;
    }
}
