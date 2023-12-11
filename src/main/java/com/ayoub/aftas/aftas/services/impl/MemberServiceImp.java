package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.entities.Member;
import com.ayoub.aftas.aftas.respositories.MemberRepository;
import com.ayoub.aftas.aftas.services.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberServiceImp implements MemberService {

    MemberRepository memberRepository;

    public MemberServiceImp(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    @Override
    public Member save(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public Member update(Member member) {
        return  memberRepository.save(member);
    }

    @Override
    public void delete(Member member) {
        memberRepository.delete(member);
    }

    @Override
    public List<Member> getAll() {
        return  memberRepository.findAll();
    }

    @Override
    public Member getById(Long id) {
        return memberRepository.findById(id).get();
    }
}
