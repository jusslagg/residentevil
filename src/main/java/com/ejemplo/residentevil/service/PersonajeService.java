package com.ejemplo.residentevil.service;

import java.util.List;

import com.ejemplo.residentevil.dto.PersonajeDTO;

public interface PersonajeService {
    PersonajeDTO getPersonajeById(Long id);

    List<PersonajeDTO> getAllPersonajes();

    PersonajeDTO savePersonajeDTO(PersonajeDTO personajeDTO);

    PersonajeDTO savePersonajeFromApi(PersonajeDTO personajeDTO);

    void deletePersonaje(Long id);
}
