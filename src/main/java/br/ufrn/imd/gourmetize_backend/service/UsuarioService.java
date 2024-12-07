package br.ufrn.imd.gourmetize_backend.service;

import br.ufrn.imd.gourmetize_backend.model.Etiqueta;
import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EtiquetaService etiquetaService;

    private UsuarioService(@Autowired UsuarioRepository usuarioRepository, @Autowired EtiquetaService etiquetaService) {
        this.usuarioRepository = usuarioRepository;
        this.etiquetaService = etiquetaService;
    }

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public Usuario save(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public void deleteById(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Etiqueta> findEtiquetas(Long id) {
        return etiquetaService.findByUsuarioId(id);
    }
}
