package br.ufrn.imd.gourmetize_backend.controller;

import br.ufrn.imd.gourmetize_backend.model.Receita;
import br.ufrn.imd.gourmetize_backend.model.dto.ReceitaDTO;
import br.ufrn.imd.gourmetize_backend.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receitas")
public class ReceitaController {

    private final ReceitaService receitaService;

    private ReceitaController(@Autowired ReceitaService receitaService) {
        this.receitaService = receitaService;
    }

    @GetMapping
    public List<ReceitaDTO> getAllReceitas() {
        return receitaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        Receita receita = receitaService.findById(id);
        return receita != null ? ResponseEntity.ok(receita) : ResponseEntity.notFound().build();
    }
    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<ReceitaDTO>> getReceitaByUser(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(receitaService.findByUsuarioId(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReceitaDTO createReceita(@RequestBody Receita receita) {
        return receitaService.save(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReceitaDTO> updateReceita(@PathVariable Long id, @RequestBody Receita receitaDetails) {
        Receita existingReceita = receitaService.findById(id);

        if (existingReceita == null) {
            return ResponseEntity.notFound().build();
        }
        existingReceita.setTitulo(receitaDetails.getTitulo());
        existingReceita.setImageUrl(receitaDetails.getImageUrl());
        existingReceita.setDescricao(receitaDetails.getDescricao());
        existingReceita.setIngredientes(receitaDetails.getIngredientes());
        existingReceita.setPreparo(receitaDetails.getPreparo());

        ReceitaDTO updatedReceita = receitaService.save(existingReceita);
        return ResponseEntity.ok(updatedReceita);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReceita(@PathVariable Long id) {
        Receita receita = receitaService.findById(id);

        if (receita == null) {
            return ResponseEntity.notFound().build();
        }

        receitaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/favoritas/{usuarioId}")
    public ResponseEntity<List<ReceitaDTO>> findFavoritasByUsuarioId(@PathVariable Long usuarioId) {
        try {
            return ResponseEntity.ok(receitaService.findFavoritasByUsuarioId(usuarioId));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/favoritas/{usuarioId}/{receitaId}")
    public ResponseEntity<Void> addFavorita(@PathVariable Long usuarioId, @PathVariable Long receitaId) {
        try {
            receitaService.addFavorita(usuarioId, receitaId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/favoritas/{usuarioId}/{receitaId}")
    public ResponseEntity<Void> removeFavorita(@PathVariable Long usuarioId, @PathVariable Long receitaId) {
        try {
            receitaService.removeFavorita(usuarioId, receitaId);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
