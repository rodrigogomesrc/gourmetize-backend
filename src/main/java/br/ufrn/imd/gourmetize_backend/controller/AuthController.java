package br.ufrn.imd.gourmetize_backend.controller;

import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/auth")
public class AuthController {

    private final AuthService authService;

    private AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public Usuario login(String email, String password) {
        return authService.login(email, password);
    }
}
