package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.Config.exceptions.ranking.RankingInternalServerError;
import com.ayoub.aftas.aftas.dto.RankingDto;
import com.ayoub.aftas.aftas.entities.Ranking;
import com.ayoub.aftas.aftas.services.RankingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/ranking")
public class RankingController {

    RankingService rankingService;

    RankingController (RankingService rankingService) {
        this.rankingService = rankingService;
    }

    @GetMapping
    public List<Ranking> getRanks() {
        return rankingService.getAll();
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RankingDto rankingDto ){
        try{
            return ResponseEntity.ok(rankingService.save(rankingDto));
        }catch(RankingInternalServerError  e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
