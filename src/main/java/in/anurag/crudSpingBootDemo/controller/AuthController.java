package in.anurag.crudSpingBootDemo.controller;

import in.anurag.crudSpingBootDemo.dto.LoginRequestDto;
import in.anurag.crudSpingBootDemo.dto.LoginResponseDto;
import in.anurag.crudSpingBootDemo.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
        @Autowired
        private AuthenticationManager authenticationManager;

        @Autowired
        private JwtService jwtService;

    @PostMapping("/login")
    public LoginResponseDto login(@RequestBody LoginRequestDto loginRequestDto){


        Authentication authenticationRequest  = UsernamePasswordAuthenticationToken
                .unauthenticated(
                        loginRequestDto.getUsername(),
                        loginRequestDto.getPassword()
                );

        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        String token = jwtService.generateToken(authentication);

        return new LoginResponseDto(token);
    }
}
