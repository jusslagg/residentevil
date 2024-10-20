package com.ejemplo.residentevil.mapper;

import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.model.Personaje;

public class PersonajeMapper {
    public static PersonajeDTO toDTO(Personaje personaje) {
        return new PersonajeDTO(personaje.getId(), personaje.getNombre(), personaje.getHabilidad());
    }

    public static Personaje toEntity(PersonajeDTO personajeDTO) {
        Personaje personaje = new Personaje();
        personaje.setId(personajeDTO.getId());
        personaje.setNombre(personajeDTO.getNombre());
        personaje.setHabilidad(personajeDTO.getHabilidad());
        return personaje;
    }
}
