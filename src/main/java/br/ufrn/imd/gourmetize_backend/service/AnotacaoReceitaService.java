package br.ufrn.imd.gourmetize_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.gourmetize_backend.model.AnotacaoReceita;
import br.ufrn.imd.gourmetize_backend.repository.AnotacaoReceitaRepository;

@Service
public class AnotacaoReceitaService {
    private AnotacaoReceitaRepository repository;

    private AnotacaoReceitaService(@Autowired AnotacaoReceitaRepository anotacaoReceitaRepository) {
        this.repository = anotacaoReceitaRepository;
    }

    public AnotacaoReceita save(AnotacaoReceita anotacaoReceita) {
        return repository.save(anotacaoReceita);
    }

    public void delete(Long id) {
        repository.deleteById(id);;
    }

    public AnotacaoReceita getByUsuarioAndReceita(Long usuarioId, Long receitaId) {
        return repository.findByUsuarioIdAndReceitaId(usuarioId, receitaId);
    }
}
