package br.ufrn.imd.gourmetize_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import br.ufrn.imd.gourmetize_backend.service.AvaliacaoService;

import br.ufrn.imd.gourmetize_backend.model.Avaliacao;
import br.ufrn.imd.gourmetize_backend.model.dto.ReceitaDTO;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoService avaliacaoService;

    @PostMapping
    public ResponseEntity<?> createAvaliacao(@RequestBody Avaliacao avaliacao) {
        try {
            return ResponseEntity.ok(avaliacaoService.save(avaliacao));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage()); // Retorna erro de validação
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao salvar avaliação: " + e.getMessage()); // Retorna erro
                                                                                                   // genérico
        }
    }

    // Buscar todas as avaliações
    @GetMapping
    public ResponseEntity<List<Avaliacao>> findAll() {
        try {
            List<Avaliacao> avaliacaoList = avaliacaoService.findAll();
            return ResponseEntity.ok(avaliacaoList); // Retorna lista de avaliações
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null); // Retorna erro genérico
        }
    }

    // Buscar uma avaliação por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Avaliacao avaliacao = avaliacaoService.findById(id);
        if (avaliacao != null) {
            return ResponseEntity.ok(avaliacao); // Retorna a avaliação se encontrada
        } else {
            return ResponseEntity.status(404).body("Avaliação não encontrada"); // Retorna erro se não encontrada
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAvaliacao(@PathVariable Long id, @RequestBody Avaliacao avaliacao) {
        try {

            return ResponseEntity.ok(avaliacaoService.update(avaliacao));

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao atualizar avaliação: " + e.getMessage()); // Retorna erro
                                                                                                      // genérico
        }
    }
@DeleteMapping("/{id}")
public ResponseEntity<?> delete(@PathVariable Long id) {
    try {
        avaliacaoService.delete(id); // Deleta a avaliação pelo ID
        return ResponseEntity.ok("Avaliação deletada com sucesso!"); // Retorna mensagem de sucesso
    } catch (Exception e) {
        return ResponseEntity.status(500).body("Erro ao deletar avaliação: " + e.getMessage()); // Retorna erro genérico
    }
}
}
