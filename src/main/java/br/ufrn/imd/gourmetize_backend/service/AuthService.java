package br.ufrn.imd.gourmetize_backend.service;

import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;

    private AuthService(@Autowired UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario login(String email, String password) {
        Usuario usuario = usuarioRepository.findByEmail(email);

        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if (!passwordEncoder.matches(password, usuario.getSenha())) {
            throw new IllegalArgumentException("Credenciais inválidas");
        }

        return usuario;
    }
}
