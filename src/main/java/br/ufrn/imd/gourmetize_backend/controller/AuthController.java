package br.ufrn.imd.gourmetize_backend.controller;

import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.model.dto.LoginRequest;
import br.ufrn.imd.gourmetize_backend.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private AuthController(@Autowired AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginRequest body) {
        try {
            return ResponseEntity.ok(authService.login(body.getEmail(), body.getPassword()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).body(null);
        }
    }
}
