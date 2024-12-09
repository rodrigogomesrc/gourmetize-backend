package br.ufrn.imd.gourmetize_backend.service;

import br.ufrn.imd.gourmetize_backend.model.Avaliacao;
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
import java.util.stream.Collectors;

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

    private ReceitaDTO entityToDtO(Receita receita) {
        var avaliacoes = receita.getAvaliacoes();

        Double mediaAvaliacao = avaliacoes != null && avaliacoes.size() > 0 ? avaliacoes.stream()
            .mapToDouble(Avaliacao::getNota)
            .average()
            .orElse(0) : null;

        return new ReceitaDTO(
            receita.getId(),
            receita.getTitulo(),
            receita.getDescricao(),
            receita.getIngredientes(),
            receita.getPreparo(),
            receita.getUsuario(),
            receita.getEtiquetas(),
            mediaAvaliacao
        );
    }

    public List<ReceitaDTO> findAll() {
        List<Receita> receitas = receitaRepository.findAll();

        return receitas.stream()
            .map(this::entityToDtO)
            .collect(Collectors.toList());
    }

    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public List<ReceitaDTO> findByUsuarioId(Long id) {
        Usuario usuario = usuarioService.findById(id);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return receitaRepository
            .findByUsuarioId(id)
            .stream()
            .map(this::entityToDtO)
            .collect(Collectors.toList());

    }

    public ReceitaDTO save(Receita receita) {
        return entityToDtO(receitaRepository.save(receita));
    }

    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }

    public List<ReceitaDTO> findFavoritasByUsuarioId(Long usuarioId) {
        Usuario usuario = usuarioService.findById(usuarioId);
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return receitaRepository
            .findFavoritasByUsuarioId(usuarioId)
            .stream()
            .map(this::entityToDtO)
            .collect(Collectors.toList());
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
