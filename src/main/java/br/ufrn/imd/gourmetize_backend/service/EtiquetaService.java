package br.ufrn.imd.gourmetize_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufrn.imd.gourmetize_backend.model.Etiqueta;
import br.ufrn.imd.gourmetize_backend.repository.EtiquetaRepository;

@Service
public class EtiquetaService {
    @Autowired
    private EtiquetaRepository etiquetaRepository;

    public List<Etiqueta> findAll() {
        return etiquetaRepository.findAll();
    }

    public Etiqueta save(Etiqueta etiqueta) {
        return etiquetaRepository.save(etiqueta);
    }

    public Optional<Etiqueta> findById(Long id) {
        return etiquetaRepository.findById(id);
    }

    public Optional<Etiqueta> findByName(String name) {
        return etiquetaRepository.findByNome(name);
    }

    public List<Etiqueta> findByUsuarioId(Long usuarioId) {
        return etiquetaRepository.findByUsuarioId(usuarioId);
    }
}
