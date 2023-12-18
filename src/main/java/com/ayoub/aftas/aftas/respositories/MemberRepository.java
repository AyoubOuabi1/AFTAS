package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {

    @Query("SELECT m FROM Member m WHERE m.rankings IS EMPTY")
    List<Member> findMembersWithoutCompetition();
}
