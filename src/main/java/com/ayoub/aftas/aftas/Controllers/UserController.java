package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.dto.UserDto;
import com.ayoub.aftas.aftas.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Constant.APIVersion + "/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    /*@GetMapping("")
    public List<MemberDto> get(){
        return memberService.getAll();
    }*/
    @GetMapping("/competitions")
    public List<UserDto> get(@RequestParam Long competitionId){
        return userService.findMembersNotRankedInCompetition(competitionId);
    }

    @GetMapping("/competitions/rank")
    public List<UserDto> getMembersByCompetition(@RequestParam Long competitionId){
        return userService.findMembersRankedInCompetition(competitionId);
    }

    @GetMapping("")
    public List<UserDto> get(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserDto getById(@PathVariable Long id){
        return userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserDto update(@PathVariable Long id, @RequestParam String role){
        return userService.update(id, role);
    }
}
