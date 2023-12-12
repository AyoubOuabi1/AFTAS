package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.MemberDto;
import com.ayoub.aftas.aftas.dto.RankingDto;
import com.ayoub.aftas.aftas.entities.Ranking;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RankingService {
    Ranking save(RankingDto ranking);
    Ranking update(Ranking ranking);
    void delete(Ranking ranking);
    List<Ranking> getAll();
    Ranking getById(Long id);
    Ranking getRankingByCompetition_idAndMember_id(Long competitionId, Long memberId);

    List<MemberDto> getWinners(Long Competition);
}
