package br.ufrn.imd.gourmetize_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufrn.imd.gourmetize_backend.model.Carrinho;
import br.ufrn.imd.gourmetize_backend.model.Usuario;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Usuario> {
    // Aqui, o tipo da chave primária será Usuario (OneToOne)
    Carrinho findByUsuario(Usuario usuario);
}
