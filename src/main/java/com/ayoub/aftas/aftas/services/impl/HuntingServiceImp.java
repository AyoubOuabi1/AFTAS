package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.HuntingDto;
import com.ayoub.aftas.aftas.dto.HuntingInputDto;
import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.entities.Hunting;
import com.ayoub.aftas.aftas.entities.Ranking;
import com.ayoub.aftas.aftas.mappers.*;
import com.ayoub.aftas.aftas.respositories.HuntingRepository;
import com.ayoub.aftas.aftas.respositories.RankingRepository;
import com.ayoub.aftas.aftas.services.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HuntingServiceImp implements HuntingService {

    HuntingRepository huntingRepository;
    CompetitionService competitionService;
    UserService userService;
    FishService fishService;

    RankingService rankingService;
    UserMapper userMapper;

    public HuntingServiceImp(HuntingRepository huntingRepository,
                             CompetitionService competitionService,
                             UserService userService,
                             FishService fishService,
                             RankingService rankingService,
                             UserMapper userManager){
        this.huntingRepository = huntingRepository;
        this.competitionService = competitionService;
        this.userService = userService;
        this.fishService = fishService;
        this.rankingService = rankingService;
        this.userMapper = userManager;
    }

    @Override
    public HuntingDto save(HuntingInputDto huntingInputDto) {
        Ranking ranking=rankingService.getRankingByCompetition_idAndMember_id(
                huntingInputDto.getCompetitionId(),
                huntingInputDto.getMemberId()
        );
        Fish fish=FishMapper.mapFromDto(fishService.getById(huntingInputDto.getFishId()));
        if(huntingInputDto.getAverageWeight()>=fish.getAverageWeight()){
            Hunting existHunt=huntingRepository.
                    findByUser_IdAndCompetition_IdAndFish_Id
                            (huntingInputDto.getMemberId(),
                                    huntingInputDto.getCompetitionId(),
                                    fish.getId()
                            );
            if(existHunt!=null){
                int numberOfFish=existHunt.getNumberOfFish()+1;
                existHunt.setNumberOfFish(numberOfFish);

                ranking.setScore(ranking.getScore()+fish.getLevel().getPoints());
                return HuntingMapper.mapToDto(huntingRepository.save(existHunt));
            }else {
                Hunting hunting=Hunting.builder()
                        .numberOfFish(1)
                        .competition(
                                CompetitionMapper.mapFromDto(
                                        competitionService.getById(huntingInputDto.getCompetitionId())
                                )
                        )
                        .user(userMapper.toEntity(userService.getById(huntingInputDto.getMemberId())))
                        .fish(fish)
                        .build();
                ranking.setScore(ranking.getScore()+fish.getLevel().getPoints());
                return HuntingMapper.mapToDto(huntingRepository.save(hunting));
            }
        }else {

            throw new InternalServerError("Cannot add this hunting record; the fish must have the same or greater weight than the currently selected fish");
        }

    }

    @Override
    public HuntingDto update(HuntingInputDto huntingInputDto) {
        try{
            Hunting hunting=Hunting.builder()
                    .numberOfFish(1)
                    .competition(
                            CompetitionMapper.mapFromDto(
                                    competitionService.getById(huntingInputDto.getCompetitionId())
                            )
                    )
                    .user(userMapper.toEntity(userService.getById(huntingInputDto.getMemberId())))
                    .build();
            return HuntingMapper.mapToDto(huntingRepository.save(hunting));
        }catch (Exception e){
            throw  new InternalServerError("Failed to update Hunt");
        }

    }

    @Override
    public void delete(Long id) {
        try{
            HuntingDto hunt=getById(id);
            if(hunt!=null){
                huntingRepository.delete(HuntingMapper.mapFromDto(hunt));
            }else {
                throw  new NotFoundException("hunt not found with id " + id);
            }
        }catch (NotFoundException e){
            throw e;
        } catch (Exception e){
            throw new InternalServerError("error deleting hunt");
        }
    }

    @Override
    public List<HuntingDto> getAll() {
        List<HuntingDto> huntList = new ArrayList<>();
        huntingRepository.findAll().stream().forEach(dto -> {
            huntList.add(HuntingMapper.mapToDto(dto));
        });
        return huntList;
    }

    @Override
    public HuntingDto getById(Long id) {
        if (id != null) {
            Optional<Hunting> huntingDtoOptional = huntingRepository.findById(id);

            if (huntingDtoOptional.isPresent()) {
                return HuntingMapper.mapToDto(huntingDtoOptional.get());
            } else {
                throw new NotFoundException("Fish not found with ID: " + id);
            }
        }else {
            throw new InternalServerError("id cannot be null");
        }
    }
}
