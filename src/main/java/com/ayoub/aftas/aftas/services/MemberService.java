package com.ayoub.aftas.aftas.services;

import com.ayoub.aftas.aftas.entities.Member;

import java.util.List;

public interface MemberService {
    Member save(Member member);
    Member update(Member member);
    void delete(Member member);
    List<Member> getAll();
    Member getById(Long id);
}
