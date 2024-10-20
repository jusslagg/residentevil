package com.ejemplo.residentevil.service;

import com.ejemplo.residentevil.model.Arma;
import com.ejemplo.residentevil.repository.ArmaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArmaService {
    @Autowired
    private ArmaRepository armaRepository;

    // Obtener todas las armas
    public List<Arma> getAllArmas() {
        return armaRepository.findAll();
    }

    // Obtener arma por ID
    public Optional<Arma> getArmaById(Long id) {
        return armaRepository.findById(id);
    }

    // Crear o actualizar arma
    public Arma saveArma(Arma arma) {
        return armaRepository.save(arma);
    }

    // Eliminar arma
    public void deleteArma(Long id) {
        armaRepository.deleteById(id);
    }

    // Buscar armas por tipo
    public List<Arma> getArmasByTipo(String tipo) {
        return armaRepository.findByTipo(tipo);
    }

    // Buscar armas por nombre (parcial)
    public List<Arma> getArmasByNombre(String nombre) {
        return armaRepository.findByNombreContaining(nombre);
    }
}
