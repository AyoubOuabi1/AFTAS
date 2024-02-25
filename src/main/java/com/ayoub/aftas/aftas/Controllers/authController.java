package com.ayoub.aftas.aftas.Controllers;

 import com.ayoub.aftas.aftas.dto.AuthenticateDto;
 import com.ayoub.aftas.aftas.dto.RefreshTokenRequestDTO;
 import com.ayoub.aftas.aftas.dto.RequestRegisterDto;
 import com.ayoub.aftas.aftas.dto.AuthResponse;
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
@RequestMapping("api/v1/auth/")
@RequiredArgsConstructor
public class authController {

    private final UserService userService;

    @PostMapping("register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RequestRegisterDto requestRegisterDto) throws ValidationException{
        return ResponseEntity.ok(userService.register(requestRegisterDto));
    }


    @PostMapping("login")
    public ResponseEntity<AuthResponse> authenticate(@Valid @RequestBody AuthenticateDto authenticateDto){
        return ResponseEntity.ok(userService.authenticate(authenticateDto));
    }

    @PostMapping("/refreshToken")
    public AuthResponse refreshToken(@RequestBody String refreshTokenRequestDTO){
        return userService.getRefreshToken(refreshTokenRequestDTO);
    }
}
