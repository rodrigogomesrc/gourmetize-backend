package br.ufrn.imd.gourmetize_backend.repository;

import br.ufrn.imd.gourmetize_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

}
