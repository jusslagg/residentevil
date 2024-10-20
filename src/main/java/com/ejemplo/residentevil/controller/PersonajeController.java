package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.mapper.PersonajeMapper;
import com.ejemplo.residentevil.model.Personaje;
import com.ejemplo.residentevil.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/personajes")
public class PersonajeController {
    @Autowired
    private PersonajeService personajeService;

    @GetMapping
    public List<PersonajeDTO> getAllPersonajes() {
        return personajeService.getAllPersonajes().stream()
                .map(PersonajeMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> getPersonajeById(@PathVariable Long id) {
        Optional<Personaje> personaje = personajeService.getPersonajeById(id);
        return personaje.map(value -> ResponseEntity.ok(PersonajeMapper.toDTO(value)))
                        .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public PersonajeDTO createPersonaje(@RequestBody PersonajeDTO personajeDTO) {
        Personaje personaje = PersonajeMapper.toEntity(personajeDTO);
        return PersonajeMapper.toDTO(personajeService.savePersonaje(personaje));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> updatePersonaje(@PathVariable Long id, @RequestBody PersonajeDTO personajeDTO) {
        personajeDTO.setId(id);
        Personaje personaje = PersonajeMapper.toEntity(personajeDTO);
        return ResponseEntity.ok(PersonajeMapper.toDTO(personajeService.savePersonaje(personaje)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePersonaje(@PathVariable Long id) {
        personajeService.deletePersonaje(id);
        return ResponseEntity.noContent().build();
    }
}
