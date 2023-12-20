package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m " +
            "WHERE m.id NOT IN ( " +
            "  SELECT r.member.id FROM Ranking r WHERE r.competition.id = :competitionId " +
            ")")
    List<Member> findMembersNotRankedInCompetition(@Param("competitionId") Long competitionId);

    @Query("SELECT m FROM Member m " +
            "WHERE m.id  IN ( " +
            "  SELECT r.member.id FROM Ranking r WHERE r.competition.id = :competitionId " +
            ")")
    List<Member> findMembersRankedInCompetition(@Param("competitionId") Long competitionId);
}
