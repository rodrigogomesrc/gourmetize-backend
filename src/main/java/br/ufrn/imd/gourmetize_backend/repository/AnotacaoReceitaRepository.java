package br.ufrn.imd.gourmetize_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.ufrn.imd.gourmetize_backend.model.AnotacaoReceita;

public interface AnotacaoReceitaRepository extends JpaRepository<AnotacaoReceita, Long> {
    @Query("SELECT ar FROM AnotacaoReceita ar WHERE ar.usuario.id = :usuarioId AND ar.receita.id = :receitaId")
    public AnotacaoReceita findByUsuarioIdAndReceitaId(Long usuarioId, Long receitaId);
}
