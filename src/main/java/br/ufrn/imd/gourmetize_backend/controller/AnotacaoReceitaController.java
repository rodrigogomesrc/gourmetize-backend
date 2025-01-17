package br.ufrn.imd.gourmetize_backend.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ufrn.imd.gourmetize_backend.model.AnotacaoReceita;
import br.ufrn.imd.gourmetize_backend.service.AnotacaoReceitaService;

@RestController
@RequestMapping("/anotacoes")
public class AnotacaoReceitaController {
    private AnotacaoReceitaService service;

    public AnotacaoReceitaController(AnotacaoReceitaService anotacaoReceitaService) {
        this.service = anotacaoReceitaService;
    }

    @PostMapping
    public AnotacaoReceita create(@RequestBody AnotacaoReceita anotacaoReceita) {
        return service.save(anotacaoReceita);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @PutMapping("/{id}")
    public AnotacaoReceita update(@RequestBody AnotacaoReceita anotacaoReceita) {
        return service.save(anotacaoReceita);
    }

    @GetMapping
    public AnotacaoReceita find(@RequestParam Long usuarioId, @RequestParam Long receitaId) {
        return service.getByUsuarioAndReceita(usuarioId, receitaId);
    }

}
