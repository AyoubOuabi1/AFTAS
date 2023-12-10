package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Hunting;
import com.ayoub.aftas.aftas.respositories.HuntingRepository;
import com.ayoub.aftas.aftas.services.HuntingService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HuntingServiceImp implements HuntingService {

    HuntingRepository huntingRepository;

    public HuntingServiceImp(HuntingRepository huntingRepository){
        this.huntingRepository = huntingRepository;
    }
    @Override
    public Hunting save(Hunting hunting) {
        return huntingRepository.save(hunting);
    }

    @Override
    public Hunting update(Hunting hunting) {
        return huntingRepository.save(hunting);
    }

    @Override
    public void delete(Hunting hunting) {
        huntingRepository.delete(hunting);
    }

    @Override
    public List<Hunting> getAll() {
        return huntingRepository.findAll();
    }

    @Override
    public Hunting getById(Long id) {
        return huntingRepository.findById(id).get();
    }
}
