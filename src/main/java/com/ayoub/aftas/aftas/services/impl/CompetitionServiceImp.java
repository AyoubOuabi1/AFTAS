package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.competition.CompetitionInternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.competition.CompetitionNotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.respositories.CompetitionRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CompetitionServiceImp implements CompetitionService{

    CompetitionRepository competitionRepository;
    CompetitionServiceImp(CompetitionRepository competitionRepository){
        this.competitionRepository = competitionRepository;
    }

    @Override
    public CompetitionDto save(CompetitionDto competitionDto) throws CompetitionInternalServerError {

        List<CompetitionDto> competitionList = getAll();
        boolean isValid=competitionList.stream().anyMatch(competitionDto1 -> (
                competitionDto1.getDate().isEqual(competitionDto.getDate()))
        );
        if(!isValid){
            String code=competitionDto.getLocation()
                    .substring(0,2)
                    .concat("-")
                    .concat(competitionDto.getDate()
                            .toString()
                            );
            competitionDto.setCode(code);
            Competition competition= CompetitionMapper.mapFromDtoWithoutId(competitionDto);
            return CompetitionMapper.mapToDto(competitionRepository.save(competition));
        }else {
            throw new CompetitionInternalServerError("Competition with the same date already exists");
        }

    }

    @Override
    public CompetitionDto update(CompetitionDto competitionDto) throws CompetitionNotFoundException {
        try {
            CompetitionDto existingCompetition = getById(competitionDto.getId());

            if (existingCompetition != null) {
                Competition competitionToUpdate = CompetitionMapper.mapFromDto(competitionDto);
                return CompetitionMapper.mapToDto(competitionRepository.save(competitionToUpdate));
            } else {
                throw new CompetitionNotFoundException("Competition not found with ID: " + competitionDto.getId());
            }
        } catch (CompetitionNotFoundException e) {
             throw e;
        } catch (Exception e) {
             throw new RuntimeException("Error updating competition", e);
        }
    }

    @Override
    public void delete(Long id)  throws CompetitionNotFoundException{
        try {
            CompetitionDto competitionDto=getById(id);
            if(competitionDto!=null){
                Competition competition=CompetitionMapper.mapFromDto(competitionDto);
                competitionRepository.delete(competition);

            }else {
                throw new CompetitionNotFoundException("Competition not found with id " + id);
            }
        }catch (CompetitionNotFoundException e) {
            throw e;
        }catch (Exception e){
            throw new RuntimeException("error deleting competition", e);
        }

    }

    @Override
    public List<CompetitionDto> getAll() {
        List<CompetitionDto> competitionDtoList= new ArrayList<CompetitionDto>();
        competitionRepository.findAll().stream().forEach(competition -> {
            competitionDtoList.add(CompetitionMapper.mapToDto(competition));
        });
        return competitionDtoList;
    }

    @Override
    public CompetitionDto getById(Long id) throws CompetitionNotFoundException {
        if (id != null) {
            Optional<Competition> competitionOptional = competitionRepository.findById(id);

            if (competitionOptional.isPresent()) {
                return CompetitionMapper.mapToDto(competitionOptional.get());
            } else {
                throw new CompetitionNotFoundException("Competition not found with ID: " + id);
            }
        }

        return null;
    }

}
