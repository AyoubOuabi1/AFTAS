package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.services.CompetitionService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/competition")
public class CompetitionController {

    CompetitionService competitionService;
    CompetitionController (CompetitionService competitionService) {
        this.competitionService =competitionService;
    }

    @GetMapping("")
    public List<CompetitionDto> getAll() {
        return competitionService.getAll();
    }

    @GetMapping("/open")
    public List<CompetitionDto> getOpenCompetitions() {
        return competitionService.getOpenCompetitions();
    }
    @GetMapping("/active")
    public List<CompetitionDto> getActiveCompetitions() {
        return competitionService.getActiveCompetitions();
    }
    @GetMapping("all")
    public ResponseEntity<Page<CompetitionDto>> getAllEntities(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "all") String status ) {

        Pageable pageable = PageRequest.of(page, size);
        Page<CompetitionDto> competitionDtos = competitionService.getAllEntities(pageable, status);
        return ResponseEntity.ok(competitionDtos);
    }


    @GetMapping("/myCompetitions")
    public ResponseEntity<Page<CompetitionDto>> getCompetitionsByUserId(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CompetitionDto> competition = competitionService.getCompetitionsByUserId(pageable);
        return ResponseEntity.ok(competition);
    }


    @PostMapping("")
    public ResponseEntity<?> save(@Valid  @RequestBody CompetitionDto competitionDto){
        try{
            return ResponseEntity.ok(competitionService.save(competitionDto));
        }catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody CompetitionDto competitionDto){
        try{
            return ResponseEntity.ok(competitionService.save(competitionDto));
        }catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id){
        try {
            competitionService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?>  get(@Valid @PathVariable Long id){
         try {
             return ResponseEntity.ok(competitionService.getById(id));
        }catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }


}
