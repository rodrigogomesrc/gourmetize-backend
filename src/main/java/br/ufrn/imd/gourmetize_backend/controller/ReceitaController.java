package br.ufrn.imd.gourmetize_backend.controller;

import br.ufrn.imd.gourmetize_backend.model.Receita;
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
    public List<Receita> getAllReceitas() {
        return receitaService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Receita> getReceitaById(@PathVariable Long id) {
        Receita receita = receitaService.findById(id);
        return receita != null ? ResponseEntity.ok(receita) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public Receita createReceita(@RequestBody Receita receita) {
        return receitaService.save(receita);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Receita> updateReceita(@PathVariable Long id, @RequestBody Receita receitaDetails) {
        Receita existingReceita = receitaService.findById(id);

        if (existingReceita == null) {
            return ResponseEntity.notFound().build();
        }

        existingReceita.setNome(receitaDetails.getNome());
        existingReceita.setDescricao(receitaDetails.getDescricao());
        existingReceita.setIngredientes(receitaDetails.getIngredientes());
        existingReceita.setPreparo(receitaDetails.getPreparo());

        Receita updatedReceita = receitaService.save(existingReceita);
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
    public ResponseEntity<List<Receita>> findFavoritasByUsuarioId(@PathVariable Long usuarioId) {
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