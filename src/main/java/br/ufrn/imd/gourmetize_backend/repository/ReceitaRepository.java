package br.ufrn.imd.gourmetize_backend.repository;

import br.ufrn.imd.gourmetize_backend.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReceitaRepository extends JpaRepository<Receita, Integer> {
}
