package br.ufrn.imd.gourmetize_backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.ufrn.imd.gourmetize_backend.model.Etiqueta;
import br.ufrn.imd.gourmetize_backend.service.EtiquetaService;

import java.util.Optional;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    @Autowired
    private EtiquetaService etiquetaService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(etiquetaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar etiquetas: " + e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Etiqueta etiqueta) {
        try {
            return ResponseEntity.ok(etiquetaService.save(etiqueta));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao salvar etiqueta: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Optional<Etiqueta> etiqueta = etiquetaService.findById(id);
            if (etiqueta.isPresent()) {
                return ResponseEntity.ok(etiqueta.get());
            } else {
                return ResponseEntity.status(404).body("Etiqueta não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar etiqueta: " + e.getMessage());
        }
    }

    @GetMapping("/buscar")
    public ResponseEntity<?> findByName(@RequestParam String nome) {
        try {
            Optional<Etiqueta> etiqueta = etiquetaService.findByName(nome);
            if (etiqueta.isPresent()) {
                return ResponseEntity.ok(etiqueta.get());
            } else {
                return ResponseEntity.status(404).body("Etiqueta com nome '" + nome + "' não encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erro ao buscar etiqueta: " + e.getMessage());
        }
    }
}
