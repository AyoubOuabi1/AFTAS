package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.FishDto;
import com.ayoub.aftas.aftas.entities.Fish;

public class FishMapper {

    public static FishDto mapToDto(Fish fish){
        return FishDto.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .levelId(fish.getLevel().getId())
                .level(fish.getLevel())
                .build();
    }

    public static Fish mapFromDto(FishDto fish){
        return Fish.builder()
                .id(fish.getId())
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .level(fish.getLevel())
                .build();
    }

    public static Fish mapFromDtoWithOutId(FishDto fish){
        return Fish.builder()
                .name(fish.getName())
                .averageWeight(fish.getAverageWeight())
                .build();
    }


}
