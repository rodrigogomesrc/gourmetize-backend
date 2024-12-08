package br.ufrn.imd.gourmetize_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.gourmetize_backend.model.Avaliacao;
import br.ufrn.imd.gourmetize_backend.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public List<Avaliacao> findAll() {
        return avaliacaoRepository.findAll();
    }

    public Avaliacao save(Avaliacao avaliacao) {

        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }

        return avaliacaoRepository.save(avaliacao);
    }

    public Avaliacao findById(Long id) {
        return avaliacaoRepository.findById(id).orElse(null);
    }

    public Avaliacao update(Avaliacao avaliacao) {
        if (avaliacao.getNota() < 0 || avaliacao.getNota() > 10) {
            throw new IllegalArgumentException("Nota deve estar entre 0 e 10");
        }

        return avaliacaoRepository.save(avaliacao);

    }

    public void delete(Long id) {
        Avaliacao avaliacao = findById(id);
        if (avaliacao == null) {
            throw new IllegalArgumentException("Avaliação não encontrada");

        }

        avaliacaoRepository.delete(avaliacao);

    }

}
