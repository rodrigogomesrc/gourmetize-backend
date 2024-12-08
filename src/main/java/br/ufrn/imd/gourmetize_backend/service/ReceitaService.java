package br.ufrn.imd.gourmetize_backend.service;

import br.ufrn.imd.gourmetize_backend.model.Receita;
import br.ufrn.imd.gourmetize_backend.model.ReceitaFavorita;
import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.model.dto.ReceitaDTO;
import br.ufrn.imd.gourmetize_backend.repository.ReceitaFavoritaRepository;
import br.ufrn.imd.gourmetize_backend.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;
    private final UsuarioService usuarioService;
    private final ReceitaFavoritaRepository receitaFavoritaRepository;

    private ReceitaService(@Autowired ReceitaRepository receitaRepository,
            @Autowired UsuarioService usuarioService,
            @Autowired ReceitaFavoritaRepository receitaFavoritaRepository) {
        this.receitaRepository = receitaRepository;
        this.usuarioService = usuarioService;
        this.receitaFavoritaRepository = receitaFavoritaRepository;
    }

    public List<ReceitaDTO> findAll() {
        return receitaRepository.findAllReceitasWithAverageRating();
    }

    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public List<Receita> findByUsuarioId(Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return receitaRepository.findByUsuarioId(id);

    }

    public Receita save(Receita receita) {
        return receitaRepository.save(receita);
    }

    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<Receita> findFavoritasByUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return receitaRepository.findFavoritasByUsuarioId(usuarioId);
    }

    public void addFavorita(Long usuarioId, Long receitaId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Receita receita = findById(receitaId);
        if (receita == null) {
            throw new IllegalArgumentException("Receita não encontrada");
        }

        ReceitaFavorita favorita = receitaFavoritaRepository.findByUsuarioIdAndReceitaId(usuarioId, receitaId)
                .orElse(null);
        if (favorita != null) {
            throw new IllegalArgumentException("Receita já favoritada");
        }

        ReceitaFavorita receitaFavorita = new ReceitaFavorita(usuario, receita);
        receitaFavoritaRepository.save(receitaFavorita);

    }

    public void removeFavorita(Long usuarioId, Long receitaId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        Receita receita = findById(receitaId);
        if (receita == null) {
            throw new IllegalArgumentException("Receita não encontrada");
        }
        Optional<ReceitaFavorita> receitaFavorita = receitaFavoritaRepository.findByUsuarioIdAndReceitaId(usuarioId,
                receitaId);
        if (receitaFavorita.isPresent()) {
            receitaFavoritaRepository.delete(receitaFavorita.get());
        } else {
            throw new IllegalArgumentException("Receita não é favorita do usuário");
        }
    }
}
