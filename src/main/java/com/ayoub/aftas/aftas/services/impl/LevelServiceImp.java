package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Level;
import com.ayoub.aftas.aftas.respositories.LevelRepository;
import com.ayoub.aftas.aftas.services.LevelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LevelServiceImp implements LevelService {

    LevelRepository levelRepository;

    public LevelServiceImp(LevelRepository levelRepository){
        this.levelRepository = levelRepository;
    }
    @Override
    public Level save(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public Level update(Level level) {
        return levelRepository.save(level);
    }

    @Override
    public void delete(Level level) {
        levelRepository.delete(level);
    }

    @Override
    public List<Level> getAll() {
        return levelRepository.findAll();
    }

    @Override
    public Level getById(Long id) {
        if(id != null){
            return levelRepository.findById(id).get();
        }
        return null;
    }
}
