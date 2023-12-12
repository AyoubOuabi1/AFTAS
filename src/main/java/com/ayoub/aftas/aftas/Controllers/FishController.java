package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.Config.exceptions.InternalServerError;
import com.ayoub.aftas.aftas.Config.exceptions.NotFoundException;
import com.ayoub.aftas.aftas.dto.FishDto;
import com.ayoub.aftas.aftas.services.FishService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/fish")
public class FishController {

    private final FishService fishService;

    public FishController(FishService fishService) {
        this.fishService = fishService;
    }

    @GetMapping("")
    public List<FishDto> getAll() {
        return fishService.getAll();
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody FishDto fishDto) {
        try {
            return ResponseEntity.ok(fishService.save(fishDto));
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("")
    public ResponseEntity<?> update(@Valid @RequestBody FishDto fishDto) {
        try {
            return ResponseEntity.ok(fishService.update(fishDto));
        } catch (InternalServerError e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@Valid @PathVariable Long id) {
        try {
            fishService.delete(id);
            return ResponseEntity.ok("Fish successfully deleted");
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@Valid @PathVariable Long id) {
        try {
            return ResponseEntity.ok(fishService.getById(id));
        } catch (NotFoundException e) {
            return ResponseEntity.status(HttpServletResponse.SC_NOT_FOUND).body(e.getMessage());
        }
    }
}
