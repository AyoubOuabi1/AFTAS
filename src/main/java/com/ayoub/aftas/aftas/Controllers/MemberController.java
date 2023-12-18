package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.dto.MemberDto;
import com.ayoub.aftas.aftas.entities.Member;
import com.ayoub.aftas.aftas.mappers.MemberMapper;
import com.ayoub.aftas.aftas.services.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/member")
public class MemberController {

    MemberService memberService;

    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    /*@GetMapping("")
    public List<MemberDto> get(){
        return memberService.getAll();
    }*/
    @GetMapping("")
    public List<MemberDto> get(){
        return memberService.findMembersWithoutCompetition();
    }
    @PostMapping("")
    public Member save(@RequestBody  MemberDto memberDto){
        Member member = MemberMapper.mapFromDtoWithOutId(memberDto);
        return memberService.save(member);
    }

    @PutMapping("/{id}")
    public Member update(@RequestBody MemberDto memberDto){
        Member member = MemberMapper.mapFromDto(memberDto);
        return memberService.update(member);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        Member member = MemberMapper.mapFromDto(memberService.getById(id));
        memberService.delete(member);
    }

    @GetMapping("/{id}")
    public MemberDto getById(@PathVariable Long id){
        return memberService.getById(id);
    }
}
