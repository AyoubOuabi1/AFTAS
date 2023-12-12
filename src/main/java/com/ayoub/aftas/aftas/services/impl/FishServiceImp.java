package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.fish.FishNotFoundException;
import com.ayoub.aftas.aftas.dto.FishDto;
import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.mappers.FishMapper;
import com.ayoub.aftas.aftas.respositories.FishRepository;
import com.ayoub.aftas.aftas.services.FishService;
import com.ayoub.aftas.aftas.services.LevelService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FishServiceImp implements FishService {

    private final FishRepository fishRepository;
    LevelService levelService;

    public FishServiceImp(FishRepository fishRepository,LevelService levelService) {
        this.fishRepository = fishRepository;
        this.levelService = levelService;
    }

    @Override
    public FishDto save(FishDto fishDto)  {
        Fish fish = FishMapper.mapFromDtoWithOutId(fishDto);
       // fish.setLevel(levelService.getById(fishDto.getId()));
        fish.setLevel(levelService.getById(fishDto.getLevelId()));
        return FishMapper.mapToDto(fishRepository.save(fish));
    }

    @Override
    public FishDto update(FishDto fishDto) throws FishNotFoundException {
        try {
            FishDto existingFishDto = getById(fishDto.getId());

            if (existingFishDto != null) {
                Fish fishToUpdate = FishMapper.mapFromDto(fishDto);
                fishToUpdate.setLevel(levelService.getById(fishDto.getId()));
                return FishMapper.mapToDto(fishRepository.save(fishToUpdate));
            } else {
                throw new FishNotFoundException("Fish not found with ID: " + fishDto.getId());
            }
        } catch (FishNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error updating fish", e);
        }
    }

    @Override
    public void delete(Long id) throws FishNotFoundException {
        try {
            FishDto fishDto = getById(id);
            if (fishDto != null) {
                fishRepository.delete(FishMapper.mapFromDto(fishDto));
            } else {
                throw new FishNotFoundException("Fish not found with id " + id);
            }
        } catch (FishNotFoundException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error deleting fish", e);
        }
    }

    @Override
    public List<FishDto> getAll() {
        List<FishDto> fishDtoList = new ArrayList<>();
        fishRepository.findAll().stream().forEach(fish ->{
            fishDtoList.add(FishMapper.mapToDto(fish));
        });


        return fishDtoList;
    }

    @Override
    public FishDto getById(Long id) throws FishNotFoundException {
        if (id != null) {
            Optional<Fish> fishOptional = fishRepository.findById(id);

            if (fishOptional.isPresent()) {
                return FishMapper.mapToDto(fishOptional.get());
            } else {
                throw new FishNotFoundException("Fish not found with ID: " + id);
            }
        }

        return null;
    }
}
