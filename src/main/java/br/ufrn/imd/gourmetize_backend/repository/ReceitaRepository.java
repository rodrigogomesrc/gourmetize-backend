package br.ufrn.imd.gourmetize_backend.repository;

import br.ufrn.imd.gourmetize_backend.model.Receita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReceitaRepository extends JpaRepository<Receita, Long> {

    @Query("SELECT r FROM Receita r JOIN ReceitaFavorita rf ON rf.receita = r WHERE rf.usuario.id = :usuarioId")
    List<Receita> findFavoritasByUsuarioId(@Param("usuarioId") Long usuarioId);
}
