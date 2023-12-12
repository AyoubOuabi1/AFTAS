package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.HuntingDto;
import com.ayoub.aftas.aftas.dto.HuntingInputDto;
import com.ayoub.aftas.aftas.services.HuntingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/hunting")
public class HuntingController {

    private final HuntingService huntingService;

    public HuntingController(HuntingService huntingService) {
        this.huntingService = huntingService;
    }

    @GetMapping("")
    public List<HuntingDto> getAll() {
        return huntingService.getAll();
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody HuntingInputDto huntingInputDto) {
        try {
            return ResponseEntity.ok(huntingService.save(huntingInputDto));
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody HuntingInputDto huntingInputDto) {
        try {
            return ResponseEntity.ok(huntingService.update(huntingInputDto));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            huntingService.delete(id);
            return ResponseEntity.ok("Hunting successfully deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(huntingService.getById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
