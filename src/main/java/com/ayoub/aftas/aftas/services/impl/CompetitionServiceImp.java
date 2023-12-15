package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.respositories.CompetitionRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
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
    public CompetitionDto save(CompetitionDto competitionDto) throws InternalServerError {
        LocalDate localDate = LocalDate.now().plus(2, ChronoUnit.DAYS);
        if(competitionDto.getDate().isAfter(localDate)) {
            List<CompetitionDto> competitionList = getAll();
            boolean isValid = competitionList.stream().anyMatch(competitionDto1 -> (
                    competitionDto1.getDate().isEqual(competitionDto.getDate()))
            );
            if (!isValid) {
                String code = competitionDto.getLocation()
                        .substring(0, 3)
                        .concat("-")
                        .concat(competitionDto.getDate()
                                .toString()
                        );
                competitionDto.setCode(code);
                Competition competition = CompetitionMapper.mapFromDtoWithoutId(competitionDto);
                competition.setStatus("open");
                return CompetitionMapper.mapToDto(competitionRepository.save(competition));
            } else {
                throw new InternalServerError("Competition with the same date already exists");
            }
        }else {
            throw new InternalServerError("you can create a new Competition only before  48h of start date ");

        }
    }

    @Override
    public CompetitionDto update(CompetitionDto competitionDto) throws NotFoundException {
        try {
            CompetitionDto existingCompetition = getById(competitionDto.getId());

            if (existingCompetition != null) {
                Competition competitionToUpdate = CompetitionMapper.mapFromDto(competitionDto);
                return CompetitionMapper.mapToDto(competitionRepository.save(competitionToUpdate));
            } else {
                throw new NotFoundException("Competition not found with ID: " + competitionDto.getId());
            }
        } catch (NotFoundException e) {
             throw e;
        } catch (Exception e) {
             throw new RuntimeException("Error updating competition", e);
        }
    }

    @Override
    public void delete(Long id)  throws NotFoundException {
        try {
            CompetitionDto competitionDto=getById(id);
            if(competitionDto!=null){
                Competition competition=CompetitionMapper.mapFromDto(competitionDto);
                competitionRepository.delete(competition);

            }else {
                throw new NotFoundException("Competition not found with id " + id);
            }
        }catch (NotFoundException e) {
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
    public CompetitionDto getById(Long id) throws NotFoundException {
        if (id != null) {
            Optional<Competition> competitionOptional = competitionRepository.findById(id);

            if (competitionOptional.isPresent()) {
                return CompetitionMapper.mapToDto(competitionOptional.get());
            } else {
                throw new NotFoundException("Competition not found with ID: " + id);
            }
        }

        return null;
    }

}
