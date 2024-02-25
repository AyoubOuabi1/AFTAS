package com.ayoub.aftas.aftas.Controllers;

import com.ayoub.aftas.aftas.Config.Constant;
import com.ayoub.aftas.aftas.dto.UserDto;
import com.ayoub.aftas.aftas.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(Constant.APIVersion + "/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("")
    public List<UserDto> getAll(){
        return userService.getAll();
    }

    @PutMapping("/updatestatus/{id}")
    public Map<String, String> updateStatus (@PathVariable Long id){
        return userService.updateStatus(id);
    }

    @GetMapping("/competitions")
    public List<UserDto> findMembersNotRankedInCompetition(@RequestParam Long competitionId){
        return userService.findMembersNotRankedInCompetition(competitionId);
    }

    @GetMapping("/competitions/rank")
    public List<UserDto> getMembersByCompetition(@RequestParam Long competitionId){
        return userService.findMembersRankedInCompetition(competitionId);
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
