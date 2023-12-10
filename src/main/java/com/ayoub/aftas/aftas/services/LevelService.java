package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Level;

import java.util.List;

public interface LevelService {

    Level save(Level level);
    Level update(Level level);
    void delete(Level level);
    List<Level> getAll();

    Level getById(Long id);
}
