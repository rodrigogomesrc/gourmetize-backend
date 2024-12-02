package br.ufrn.imd.gourmetize_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.gourmetize_backend.model.Etiqueta;

@Repository
public interface EtiquetaRepository extends JpaRepository<Etiqueta, Long> {
    Optional<Etiqueta> findByNome(String name);

}
