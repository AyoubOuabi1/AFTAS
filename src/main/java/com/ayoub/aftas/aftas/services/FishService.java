package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Fish;

import java.util.List;

public interface FishService {

    Fish save(Fish fish);
    Fish update(Fish fish);
    void delete(Fish fish);
    List<Fish> getAll();
    Fish getById(Long id);
}
