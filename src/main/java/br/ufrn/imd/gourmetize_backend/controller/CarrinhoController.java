package br.ufrn.imd.gourmetize_backend.controller;

import java.util.List;

import br.ufrn.imd.gourmetize_backend.model.Carrinho;
import br.ufrn.imd.gourmetize_backend.model.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrn.imd.gourmetize_backend.service.CarrinhoService;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    @Autowired
    private CarrinhoService carrinhoService;

    // Buscar os ingredientes do carrinho de um usuário
    @GetMapping("/{usuarioId}")
    public ResponseEntity<List<String>> buscarIngredientesPorUsuario(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId); // Simulando a busca do usuário
        List<String> ingredientes = carrinhoService.buscarIngredientesPorUsuario(usuario);
        if (ingredientes != null) {
            return ResponseEntity.ok(ingredientes);
        }
        return ResponseEntity.notFound().build(); // Caso o carrinho não exista
    }

    // Atualizar o carrinho com novos ingredientes
    @PutMapping("/{usuarioId}")
    public ResponseEntity<Carrinho> atualizarCarrinho(
            @PathVariable Long usuarioId,
            @RequestBody Carrinho carrinho) {

        // Garantir que o ID do carrinho é consistente com o usuário na URL
        if (!usuarioId.equals(carrinho.getUsuario().getId())) {
            return ResponseEntity.badRequest().build(); // Erro de consistência
        }

        Carrinho atualizado = carrinhoService.atualizarCarrinho(carrinho);
        if (atualizado != null) {
            return ResponseEntity.ok(atualizado);
        }
        return ResponseEntity.notFound().build(); // Caso o carrinho não exista
    }

    // Limpar o carrinho
    @DeleteMapping("/limpar/{usuarioId}")
    public ResponseEntity<Carrinho> limparCarrinho(@PathVariable Long usuarioId) {
        Usuario usuario = new Usuario();
        usuario.setId(usuarioId); // Simulando a busca do usuário
        Carrinho carrinho = carrinhoService.limparCarrinho(usuario);
        if (carrinho != null) {
            return ResponseEntity.ok(carrinho);
        }
        return ResponseEntity.notFound().build(); // Caso o carrinho não exista
    }
}
