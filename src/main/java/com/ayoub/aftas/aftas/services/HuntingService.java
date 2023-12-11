package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.HuntingDto;
import com.ayoub.aftas.aftas.dto.HuntingInputDto;
import com.ayoub.aftas.aftas.entities.Hunting;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HuntingService {

    HuntingDto save(HuntingInputDto hunting);
    HuntingDto update(HuntingInputDto hunting);
    void delete(Long id);
    List<HuntingDto> getAll();
    HuntingDto getById(Long id);
}
