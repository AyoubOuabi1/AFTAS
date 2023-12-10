package com.ayoub.aftas.aftas.respositories;

import com.ayoub.aftas.aftas.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
}
