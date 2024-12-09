package br.ufrn.imd.gourmetize_backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

import br.ufrn.imd.gourmetize_backend.model.Carrinho;
import br.ufrn.imd.gourmetize_backend.model.Usuario;
import br.ufrn.imd.gourmetize_backend.repository.CarrinhoRepository;

@Service
public class CarrinhoService {

    @Autowired
    private CarrinhoRepository carrinhoRepository;

    // Buscar a lista de ingredientes por usuário
    public List<String> buscarIngredientesPorUsuario(Usuario usuario) {
        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
        if (carrinho != null) {
            return carrinho.getIngredientes();
        }
        return null; // Retorna null caso o carrinho não exista
    }

    public Carrinho atualizarCarrinho(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    // Adicionar um ingrediente ao carrinho
    public Carrinho adicionarIngrediente(Usuario usuario, String ingrediente) {
        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
        if (carrinho == null) {
            carrinho = new Carrinho();
            carrinho.setUsuario(usuario);
        }
        carrinho.getIngredientes().add(ingrediente);
        return carrinhoRepository.save(carrinho);
    }

    // Limpar o carrinho (remover todos os ingredientes)
    public Carrinho limparCarrinho(Usuario usuario) {
        Carrinho carrinho = carrinhoRepository.findByUsuario(usuario);
        if (carrinho != null) {
            carrinho.getIngredientes().clear();
            return carrinhoRepository.save(carrinho);
        }
        return null; // Retorna null caso o carrinho não exista
    }
}
