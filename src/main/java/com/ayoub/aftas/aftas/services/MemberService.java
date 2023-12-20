package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.dto.MemberDto;
import com.ayoub.aftas.aftas.entities.Member;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MemberService {
    Member save(Member member);
    Member update(Member member);
    void delete(Member member);
    List<MemberDto> getAll();
    MemberDto getById(Long id);

    List<MemberDto> findMembersNotRankedInCompetition(Long competitionId);
    List<MemberDto> findMembersRankedInCompetition(Long competitionId);
}
