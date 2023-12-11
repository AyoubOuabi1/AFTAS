package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.Hunting.HuntingInternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.Hunting.HuntingNotFoundException;
import com.ayoub.aftas.aftas.Config.exceptions.fish.FishNotFoundException;
import com.ayoub.aftas.aftas.dto.HuntingDto;
import com.ayoub.aftas.aftas.dto.HuntingInputDto;
import com.ayoub.aftas.aftas.entities.Fish;
import com.ayoub.aftas.aftas.entities.Hunting;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.mappers.FishMapper;
import com.ayoub.aftas.aftas.mappers.HuntingMapper;
import com.ayoub.aftas.aftas.respositories.FishRepository;
import com.ayoub.aftas.aftas.respositories.HuntingRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import com.ayoub.aftas.aftas.services.HuntingService;
import com.ayoub.aftas.aftas.services.MemberService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HuntingServiceImp implements HuntingService {

    HuntingRepository huntingRepository;
    CompetitionService competitionService;
    MemberService memberService;
    FishRepository fishRepository;

    public HuntingServiceImp(HuntingRepository huntingRepository,
                             CompetitionService competitionService,
                             MemberService memberService,
                             FishRepository fishRepository){
        this.huntingRepository = huntingRepository;
        this.competitionService = competitionService;
        this.memberService = memberService;
        this.fishRepository = fishRepository;
    }

    @Override
    public HuntingDto save(HuntingInputDto huntingInputDto) {
        Hunting existHunt=huntingRepository.
                findByMember_IdAndCompetition_IdAndFish_Id
                        (huntingInputDto.getMemberId(),
                                huntingInputDto.getCompetitionId(),
                                huntingInputDto.getFishId()
                );
        if(existHunt!=null){
            int numberOfFish=existHunt.getNumberOfFish()+1;
            existHunt.setNumberOfFish(numberOfFish);
            return HuntingMapper.mapToDto(huntingRepository.save(existHunt));
        }else {
            Hunting hunting=Hunting.builder()
                    .numberOfFish(huntingInputDto.getNumberOfFish())
                    .competition(
                            CompetitionMapper.mapFromDto(
                                    competitionService.getById(huntingInputDto.getCompetitionId())
                            )
                    )
                    .member(memberService.getById(huntingInputDto.getMemberId()))
                    .build();
            return HuntingMapper.mapToDto(huntingRepository.save(hunting));
        }

    }

    @Override
    public HuntingDto update(HuntingInputDto huntingInputDto) {
        try{
            Hunting hunting=Hunting.builder()
                    .numberOfFish(huntingInputDto.getNumberOfFish())
                    .competition(
                            CompetitionMapper.mapFromDto(
                                    competitionService.getById(huntingInputDto.getCompetitionId())
                            )
                    )
                    .member(memberService.getById(huntingInputDto.getMemberId()))
                    .build();
            return HuntingMapper.mapToDto(huntingRepository.save(hunting));
        }catch (Exception e){
            throw  new HuntingInternalServerError("Failed to update Hunt");
        }

    }

    @Override
    public void delete(Long id) {
        try{
            HuntingDto hunt=getById(id);
            if(hunt!=null){
                huntingRepository.delete(HuntingMapper.mapFromDto(hunt));
            }else {
                throw  new HuntingNotFoundException("hunt not found with id " + id);
            }
        }catch (HuntingNotFoundException e){
            throw e;
        } catch (Exception e){
            throw new HuntingInternalServerError("error deleting hunt");
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
                throw new FishNotFoundException("Fish not found with ID: " + id);
            }
        }

        return null;

    }
}
