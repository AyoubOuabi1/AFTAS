package com.ayoub.aftas.aftas.Controllers;

 import com.ayoub.aftas.aftas.dto.AuthenticateDto;
 import com.ayoub.aftas.aftas.dto.RegisterDto;
 import com.ayoub.aftas.aftas.dto.ResponseDto;
 import com.ayoub.aftas.aftas.services.UserService;
 import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

 import static com.ayoub.aftas.aftas.Config.Constant.APIVersion;


@RestController
@RequestMapping(APIVersion+"/auth/")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<ResponseDto> register(@Valid @RequestBody RegisterDto registerDto) throws ValidationException{
        return ResponseEntity.ok(userService.register(registerDto));
    }


    @PostMapping("login")
    public ResponseEntity<ResponseDto> authenticate(@Valid @RequestBody AuthenticateDto authenticateDto){
        return ResponseEntity.ok(userService.authenticate(authenticateDto));
    }


}
