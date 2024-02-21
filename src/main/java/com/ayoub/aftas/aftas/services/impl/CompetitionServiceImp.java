package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.dto.UserDto;
import com.ayoub.aftas.aftas.entities.Competition;
import com.ayoub.aftas.aftas.entities.User;
import com.ayoub.aftas.aftas.mappers.CompetitionMapper;
import com.ayoub.aftas.aftas.mappers.UserMapper;
import com.ayoub.aftas.aftas.respositories.CompetitionRepository;
import com.ayoub.aftas.aftas.services.CompetitionService;
import com.ayoub.aftas.aftas.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompetitionServiceImp implements CompetitionService{

    CompetitionRepository competitionRepository;
    private ModelMapper modelMapper;
    UserService userService;

    UserMapper userMapper;
    CompetitionServiceImp(CompetitionRepository competitionRepository,ModelMapper modelMapper,
                          UserService userService,UserMapper userMapper){
        this.competitionRepository = competitionRepository;
        this.modelMapper = modelMapper;
        this.userService = userService;
        this.userMapper = userMapper;
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
                Competition savedCompetition = competitionRepository.save(competition);
                return CompetitionMapper.mapToDto(savedCompetition);
            } else {
                throw new InternalServerError("Competition with the same date already exists");
            }
        }else {
            throw new InternalServerError("you can create a new Competition only before  48h of start date ");

        }
    }
    private void updateCompetitionStatus() {
        List<Competition> competitionList = competitionRepository.findAll();
        LocalDate currentDate = LocalDate.now();

        for (Competition competition : competitionList) {
            LocalDate competitionDate = competition.getDate();
            //LocalTime endTime = competition.getEndTime().toLocalTime();

            if (competitionDate.isEqual(currentDate)) {
                competition.setStatus("active");
               /* if (endTime.isBefore(currentTime)) {
                    competition.setStatus("close");
                } else if (competition.getStartTime().toLocalTime().isBefore(currentTime)) {

                }*/
            } else if (competitionDate.isBefore(currentDate)) {
                competition.setStatus("close");
            } else if (competitionDate.isAfter(currentDate)){
                competition.setStatus("open");
            }
        }

        competitionRepository.saveAll(competitionList);
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
    public List<CompetitionDto> getOpenCompetitions() {
        List<CompetitionDto> competitionDtoList= new ArrayList<CompetitionDto>();
        competitionRepository.getCompetitionsByStatus("open").stream().forEach(competition -> {
            competitionDtoList.add(CompetitionMapper.mapToDto(competition));
        });
        return competitionDtoList;
    }

    @Override
    public List<CompetitionDto> getActiveCompetitions() {
        List<CompetitionDto> competitionDtoList= new ArrayList<>();
        competitionRepository.getCompetitionsByStatus("active").stream().forEach(competition -> {
            competitionDtoList.add(CompetitionMapper.mapToDto(competition));
        });
        return competitionDtoList;
    }


    @Override
    public Page<CompetitionDto> getAllEntities(Pageable pageable, String status) {
        updateCompetitionStatus();
        Page<Competition> competitions;
        if (status != null && status.equalsIgnoreCase("all")) {
            competitions = competitionRepository.findAll(pageable);
        } else {
            competitions = competitionRepository.findByStatus(status, pageable);
        }
        return competitions.map(CompetitionMapper::mapToDto);
    }

    @Override
    public Page<CompetitionDto> getCompetitionsByUserId(Pageable pageable) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = authentication.getPrincipal() instanceof User ? (User) authentication.getPrincipal() : null;
        assert user != null;
        Page<Competition> competitions = competitionRepository.findCompetitionByUserId(user.getId(), pageable);
        return competitions.map(CompetitionMapper::mapToDto);

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

    @Override
    public Page<CompetitionDto> findByStatus(String status, Pageable pageable) {
        return null;
    }


}
