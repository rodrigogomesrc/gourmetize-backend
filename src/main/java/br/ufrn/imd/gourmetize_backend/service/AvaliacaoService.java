package br.ufrn.imd.gourmetize_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.gourmetize_backend.model.Avaliacao;
import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;
    @Autowired
    private UsuarioService usuarioService;

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao save(Avaliacao avaliacao) {

        Usuario user = usuarioService.findById(avaliacao.getUsuario().getId());
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }

        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }

        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    public Avaliacao update(Long id, String comentario, int nota, Long usuarioId) {
        Usuario user = usuarioService.findById(usuarioId);
        if (user == null) {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
        return avaliacaoRepository.save(new Avaliacao(id, comentario, nota, user));

    }

    public void delete(Long id) {
        Avaliacao avaliacao = findById(id);
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada");

        }

        avaliacaoRepository.delete(avaliacao);

    }

}
