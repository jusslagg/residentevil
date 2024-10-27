package com.ejemplo.residentevil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.service.PersonajeServiceRest;
import com.ejemplo.residentevil.utils.ApiResponseMsg;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    private final PersonajeServiceRest personajeService;

    @Autowired
    public PersonajeController(PersonajeServiceRest personajeService) {
        this.personajeService = personajeService;
    }

    // Crear un nuevo personaje a partir de un DTO
    @PostMapping("/create")
    public ResponseEntity<?> addPersonaje(@RequestBody PersonajeDTO personajeDTO) {
        try {
            PersonajeDTO createdPersonaje = personajeService.savePersonajeDTO(personajeDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseMsg("Personaje creado", createdPersonaje));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear personaje", e.getMessage()));
        }
    }

    // Obtener todos los personajes
    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonajes() {
        try {
            List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
            return ResponseEntity.ok(new ApiResponseMsg("Lista de personajes", personajes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("No hay personajes", e.getMessage()));
        }
    }

    // Obtener un personaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonajeById(@PathVariable Long id) {
        try {
            PersonajeDTO personaje = personajeService.getPersonajeById(id);
            return ResponseEntity.ok(new ApiResponseMsg("Personaje encontrado", personaje));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseMsg("Error: Personaje no encontrado", e.getMessage()));
        }
    }

    // Eliminar personaje por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonaje(@PathVariable Long id) {
        try {
            personajeService.deletePersonaje(id);
            return ResponseEntity.ok(new ApiResponseMsg("Personaje eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el personaje", e.getMessage()));
        }
    }

    // Actualizar un personaje
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePersonaje(@PathVariable Long id, @RequestBody PersonajeDTO personajeDTO) {
        try {
            PersonajeDTO updatedPersonaje = personajeService.updatePersonajeDTO(id, personajeDTO);
            return ResponseEntity.ok(new ApiResponseMsg("Personaje actualizado", updatedPersonaje));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al actualizar personaje", e.getMessage()));
        }
    }
}
