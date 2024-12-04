package br.ufrn.imd.gourmetize_backend.repository;

import br.ufrn.imd.gourmetize_backend.model.ReceitaFavorita;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReceitaFavoritaRepository extends JpaRepository<ReceitaFavorita, Long> {

    Optional<ReceitaFavorita> findByUsuarioIdAndReceitaId(Long usuarioId, Long receitaId);

}
