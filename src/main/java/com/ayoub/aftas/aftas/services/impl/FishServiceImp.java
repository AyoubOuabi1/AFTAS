package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.respositories.FishRepository;
import com.ayoub.aftas.aftas.services.FishService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FishServiceImp implements FishService {

    FishRepository fishRepository;
    FishServiceImp(FishRepository fishRepository){
        this.fishRepository = fishRepository;
    }
    @Override
    public Fish save(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public Fish update(Fish fish) {
        return fishRepository.save(fish);
    }

    @Override
    public void delete(Fish fish) {
        fishRepository.delete(fish);
    }

    @Override
    public List<Fish> getAll() {
        return fishRepository.findAll();
    }

    @Override
    public Fish getById(Long id) {
        return fishRepository.findById(id).get();
    }
}
