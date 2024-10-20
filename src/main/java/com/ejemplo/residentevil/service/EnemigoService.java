package com.ejemplo.residentevil.service;

import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.repository.EnemigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnemigoService {
    @Autowired
    private EnemigoRepository enemigoRepository;

    // Obtener todos los enemigos
    public List<Enemigo> getAllEnemigos() {
        return enemigoRepository.findAll();
    }

    // Obtener enemigo por ID
    public Optional<Enemigo> getEnemigoById(Long id) {
        return enemigoRepository.findById(id);
    }

    // Crear o actualizar enemigo
    public Enemigo saveEnemigo(Enemigo enemigo) {
        return enemigoRepository.save(enemigo);
    }

    // Eliminar enemigo
    public void deleteEnemigo(Long id) {
        enemigoRepository.deleteById(id);
    }

    // Buscar enemigos por tipo
    public List<Enemigo> getEnemigosByTipo(String tipo) {
        return enemigoRepository.findByTipo(tipo);
    }

    // Contar enemigos por tipo
    public Long countEnemigosByTipo(String tipo) {
        return enemigoRepository.countByTipo(tipo);
    }
}
