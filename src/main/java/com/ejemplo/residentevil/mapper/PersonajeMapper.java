package com.ejemplo.residentevil.mapper;

import org.springframework.stereotype.Component;
import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.model.Personaje;
import java.util.stream.Collectors;

@Component
public class PersonajeMapper {

    public PersonajeDTO toDTO(Personaje personaje) {
        if (personaje == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return PersonajeDTO.builder()
                .id(personaje.getId())
                .nombre(personaje.getNombre())
                .habilidad(personaje.getHabilidad())
                .build();
    }

    public Personaje toEntity(PersonajeDTO personajeDTO) {
        if (personajeDTO == null) {
            throw new IllegalArgumentException("El personajeDTO no puede ser nulo");
        }

        Personaje personaje = new Personaje();
        personaje.setId(personajeDTO.getId());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHabilidad(personajeDTO.getHabilidad());
        return personaje;
    }
}
