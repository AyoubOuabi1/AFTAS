package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {

    Fish save(Fish fish);
    Fish update(Fish fish);
    void delete(Fish fish);
    List<Fish> getAll();
    Fish getById(Long id);
}
