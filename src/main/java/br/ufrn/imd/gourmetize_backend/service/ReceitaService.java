package br.ufrn.imd.gourmetize_backend.service;

import br.ufrn.imd.gourmetize_backend.model.Receita;
import br.ufrn.imd.gourmetize_backend.repository.ReceitaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceitaService {

    private final ReceitaRepository receitaRepository;

    private ReceitaService(ReceitaRepository receitaRepository) {
        this.receitaRepository = receitaRepository;
    }

    public List<Receita> findAll() {
        return receitaRepository.findAll();
    }

    public Receita findById(Long id) {
        return receitaRepository.findById(id).orElse(null);
    }

    public Receita save(Receita receita) {
        return receitaRepository.save(receita);
    }

    public void deleteById(Long id) {
        receitaRepository.deleteById(id);
    }
}