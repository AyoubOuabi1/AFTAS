package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.FishDto;
import com.ayoub.aftas.aftas.entities.Fish;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FishService {

    FishDto save(FishDto fish);
    FishDto update(FishDto fish);
    void delete(Long id);
    List<FishDto> getAll();
    FishDto getById(Long id);
}
