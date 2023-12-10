package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Hunting;

import java.util.List;

public interface HuntingService {

    Hunting save(Hunting hunting);
    Hunting update(Hunting hunting);
    void delete(Hunting hunting);
    List<Hunting> getAll();
    Hunting getById(Long id);
}
