package com.ejemplo.residentevil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.mapper.PersonajeMapper;
import com.ejemplo.residentevil.services.PersonajeServiceRest;
import utils.ApiResponseMsg;

import java.util.List;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {

    private final PersonajeServiceRest personajeService;

    @Autowired
    public PersonajeController(PersonajeServiceRest personajeService) {
        this.personajeService = personajeService;
    }

    @Autowired
    public PersonajeMapper personajeMapper;

    // Crear un nuevo personaje a partir de un DTO
    @PostMapping("/create")
    public ResponseEntity<PersonajeDTO> addPersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO createdPersonaje = personajeService.savePersonajeDTO(personajeDTO);
        return new ResponseEntity<>(createdPersonaje, HttpStatus.CREATED);
    }

    // Obtener todos los personajes
    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonajes() {
        try {
            List<PersonajeDTO> personajes = personajeService.getAllPersonajes();
            return ResponseEntity.ok().body(new ApiResponseMsg("Lista de Personajes", personajes));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("NO HAY PERSONAJES", e.getMessage()));
        }
    }

    // Obtener un personaje por ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getPersonajeById(@PathVariable Long id) {
        try {
            PersonajeDTO personaje = personajeService.getPersonajeById(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Personaje:", personaje));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: Personaje no encontrado " + e.getMessage());
        }
    }

    // Eliminar personaje por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersonaje(@PathVariable Long id) {
        try {
            personajeService.deletePersonaje(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Personaje Eliminado", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el personaje", e.getMessage()));
        }
    }

    // Actualizar un personaje
    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updatePersonaje(@PathVariable Long id, @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO updatedPersonaje = personajeService.updatePersonajeDTO(id, personajeDTO);
        return ResponseEntity.ok(updatedPersonaje);
    }
}
