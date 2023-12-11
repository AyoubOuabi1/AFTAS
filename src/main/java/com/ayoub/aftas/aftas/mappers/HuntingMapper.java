package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.HuntingDto;
import com.ayoub.aftas.aftas.entities.Hunting;

public class HuntingMapper {
    public static HuntingDto mapToDto(Hunting hunting){
        return HuntingDto.builder()
                .id(hunting.getId())
                .numberOfFish(hunting.getNumberOfFish())
                .member(hunting.getMember())
                .competition(hunting.getCompetition())
                .fish(hunting.getFish())
                .build();
    }
    public static Hunting mapFromDto(HuntingDto huntingDto){
        return Hunting.builder()
                .id(huntingDto.getId())
                .numberOfFish(huntingDto.getNumberOfFish())
                .fish(huntingDto.getFish())
                .competition(huntingDto.getCompetition())
                .member(huntingDto.getMember())
                .build();
    }

}
