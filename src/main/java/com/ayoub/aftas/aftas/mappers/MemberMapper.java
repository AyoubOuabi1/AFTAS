package com.ayoub.aftas.aftas.mappers;

import com.ayoub.aftas.aftas.dto.MemberDto;
import com.ayoub.aftas.aftas.entities.Member;

public class MemberMapper {
    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .num(member.getNum())
                .name(member.getName())
                .familyName(member.getFamilyName())
                .accessionDate(member.getAccessionDate())
                .nationality(member.getNationality())
                .identityDocument(member.getIdentityDocument())
                .identityDocument(member.getIdentityDocument())
                .identityNumber(member.getIdentityNumber())
                .build();
    }

    public static Member mapFromDto(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .num(memberDto.getNum())
                .name(memberDto.getName())
                .familyName(memberDto.getFamilyName())
                .accessionDate(memberDto.getAccessionDate())
                .nationality(memberDto.getNationality())
                .identityDocument(memberDto.getIdentityDocument())
                .identityNumber(memberDto.getIdentityNumber())
                .build();
    }

    public static Member mapFromDtoWithOutId(MemberDto memberDto){
        return Member.builder()
                .num(memberDto.getNum())
                .name(memberDto.getName())
                .familyName(memberDto.getFamilyName())
                .accessionDate(memberDto.getAccessionDate())
                .nationality(memberDto.getNationality())
                .identityDocument(memberDto.getIdentityDocument())
                .identityNumber(memberDto.getIdentityNumber())
                .build();
    }
}
