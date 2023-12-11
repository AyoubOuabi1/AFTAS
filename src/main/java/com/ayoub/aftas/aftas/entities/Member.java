package com.ayoub.aftas.aftas.entities;

import com.ayoub.aftas.aftas.dto.MemberDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "members")
@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer num;

    private String name;

    private String familyName;

    private LocalDate accessionDate;

    private String nationality;
    private String identityDocument;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member",cascade = CascadeType.ALL)
    private List<Hunting> hunts;
    // Getters and setters

    public static MemberDto toDto(Member member){
        return MemberDto.builder()
                .id(member.getId())
                .num(member.getNum())
                .name(member.getName())
                .familyName(member.getFamilyName())
                .accessionDate(member.getAccessionDate())
                .nationality(member.getNationality())
                .identityDocument(member.getIdentityDocument())
                .build();
    }

    public static Member getMember(MemberDto memberDto){
        return Member.builder()
                .id(memberDto.getId())
                .num(memberDto.getNum())
                .name(memberDto.getName())
                .familyName(memberDto.getFamilyName())
                .accessionDate(memberDto.getAccessionDate())
                .nationality(memberDto.getNationality())
                .identityDocument(memberDto.getIdentityDocument())
                .build();
    }

    public static Member getMemberWithoutId(MemberDto memberDto){
        return Member.builder()
                .num(memberDto.getNum())
                .name(memberDto.getName())
                .familyName(memberDto.getFamilyName())
                .accessionDate(memberDto.getAccessionDate())
                .nationality(memberDto.getNationality())
                .identityDocument(memberDto.getIdentityDocument())
                .build();
    }
}
