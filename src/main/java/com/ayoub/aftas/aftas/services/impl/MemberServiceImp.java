package com.ayoub.aftas.aftas.services.impl;

import com.ayoub.aftas.aftas.dto.MemberDto;
import com.ayoub.aftas.aftas.entities.Member;
import com.ayoub.aftas.aftas.mappers.MemberMapper;
import com.ayoub.aftas.aftas.respositories.MemberRepository;
import com.ayoub.aftas.aftas.services.MemberService;
import jakarta.persistence.ManyToOne;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public List<MemberDto> getAll() {
        List<MemberDto> list = new ArrayList<>();
        memberRepository.findAll().stream().forEach(member->{
           list.add(MemberMapper.toDto(member));
        });
        return  list;
    }

    @Override
    public MemberDto getById(Long id) {
        return MemberMapper.toDto(memberRepository.findById(id).get());
    }
}
