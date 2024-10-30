package de.sp.trashNothing_backend.controllers;

import de.sp.trashNothing_backend.dtos.request.AuthRequestDto;
import de.sp.trashNothing_backend.dtos.response.AuthResponseDto;
import de.sp.trashNothing_backend.services.AuthentificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    AuthentificationService authentificationService;

    public AuthController(AuthentificationService authentificationService) {
        this.authentificationService = authentificationService;
    }


    @PostMapping("/signin")
    public String signin(Authentication authentication) {
        System.out.println(authentication.getAuthorities().toString());
        return authentificationService.token(authentication);
    }

    @PostMapping("/signup")
    public AuthResponseDto signup(@RequestBody AuthRequestDto dto) {
        System.out.println("Received signup request: " + dto);
        return authentificationService.signUp(dto);
    }
}