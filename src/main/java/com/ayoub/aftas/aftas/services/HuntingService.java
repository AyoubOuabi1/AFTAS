package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HuntingService {

    Hunting save(Hunting hunting);
    Hunting update(Hunting hunting);
    void delete(Hunting hunting);
    List<Hunting> getAll();
    Hunting getById(Long id);
}
