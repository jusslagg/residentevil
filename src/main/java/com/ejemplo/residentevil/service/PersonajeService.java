package com.ejemplo.residentevil.service;

import com.ejemplo.residentevil.model.Personaje;
import com.ejemplo.residentevil.repository.PersonajeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonajeService {
    @Autowired
    private PersonajeRepository personajeRepository;

    // Obtener todos los personajes
    public List<Personaje> getAllPersonajes() {
        return personajeRepository.findAll();
    }

    // Obtener personaje por ID
    public Optional<Personaje> getPersonajeById(Long id) {
        return personajeRepository.findById(id);
    }

    // Crear o actualizar personaje
    public Personaje savePersonaje(Personaje personaje) {
        return personajeRepository.save(personaje);
    }

    // Eliminar personaje
    public void deletePersonaje(Long id) {
        personajeRepository.deleteById(id);
    }

    // Buscar personajes por nombre
    public List<Personaje> getPersonajesByNombre(String nombre) {
        return personajeRepository.findByNombre(nombre);
    }

    // Buscar personajes por habilidad
    public List<Personaje> getPersonajesByHabilidad(String habilidad) {
        return personajeRepository.findByHabilidad(habilidad);
    }
}
