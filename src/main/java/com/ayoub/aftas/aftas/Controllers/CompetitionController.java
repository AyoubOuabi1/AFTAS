package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.CompetitionDto;
import com.ayoub.aftas.aftas.services.CompetitionService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
            return ResponseEntity.ok("competition successfully deleted");
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
