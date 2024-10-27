package com.ejemplo.residentevil.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.HashSet;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import jakarta.transaction.Transactional;

import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.mapper.PersonajeMapper;
import com.ejemplo.residentevil.model.Personaje;
import com.ejemplo.residentevil.model.Arma; // Si es necesario
import com.ejemplo.residentevil.repository.PersonajeRepository;
import com.ejemplo.residentevil.repository.ArmaRepository;

@Service
public class PersonajeServiceRest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    private final PersonajeRepository personajeRepository;
    private final PersonajeMapper personajeMapper;
    private final RestTemplate restTemplate;
    private final ArmaRepository armaRepository;

    @Autowired
    public PersonajeServiceRest(PersonajeRepository personajeRepository, 
                                PersonajeMapper personajeMapper, 
                                RestTemplate restTemplate, 
                                ArmaRepository armaRepository) {
        this.personajeRepository = personajeRepository;
        this.personajeMapper = personajeMapper;
        this.restTemplate = restTemplate;
        this.armaRepository = armaRepository;
    }

    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeDTO> personajesDB = personajeRepository.findAll().stream()
                .map(personajeMapper::toDTO)
                .collect(Collectors.toList());

        Personaje[] personajesAPI = restTemplate.getForObject(BASE_URL, Personaje[].class);

        if (personajesAPI != null) {
            for (Personaje personaje : personajesAPI) {
                personajesDB.add(personajeMapper.toDTO(personaje));
            }
        }

        return personajesDB;
    }

    public PersonajeDTO getPersonajeById(Long id) {
        Optional<Personaje> optionalPersonaje = personajeRepository.findById(id);

        if (optionalPersonaje.isPresent()) {
            return personajeMapper.toDTO(optionalPersonaje.get());
        } else {
            PersonajeDTO personajeDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonajeDTO.class, id);
            if (personajeDTO != null) {
                return personajeDTO;
            } else {
                throw new RuntimeException("Personaje no encontrado ni en la base de datos ni en la API externa");
            }
        }
    }

    @Transactional
    public PersonajeDTO savePersonajeFromApi(PersonajeDTO personajeDTO2) {
        PersonajeDTO personajeDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonajeDTO.class, personajeDTO2);

        if (personajeDTO != null) {
            Personaje personaje = personajeMapper.toEntity(personajeDTO);
            Personaje savedPersonaje = personajeRepository.save(personaje);
            return personajeMapper.toDTO(savedPersonaje);
        } else {
            throw new RuntimeException("Personaje no encontrado en la API con ID: " + personajeDTO2);
        }
    }

    public PersonajeDTO savePersonajeDTO(PersonajeDTO personajeDTO) {
        Personaje personaje = personajeMapper.toEntity(personajeDTO);

        // Si el DTO contiene IDs de armas, busca las armas correspondientes
        if (personajeDTO.getArmaIds() != null && !personajeDTO.getArmaIds().isEmpty()) {
            Set<Arma> armas = new HashSet<>();

            for (Long armaId : personajeDTO.getArmaIds()) {
                Optional<Arma> optionalArma = armaRepository.findById(armaId);
                optionalArma.ifPresent(armas::add);
            }

            personaje.setArmas(armas);
        }

        Personaje savedPersonaje = personajeRepository.save(personaje);
        return personajeMapper.toDTO(savedPersonaje);
    }

    public void deletePersonaje(Long id) {
        if (personajeRepository.existsById(id)) {
            personajeRepository.deleteById(id);
            restTemplate.delete(BASE_URL + "/{id}", id);
        } else {
            throw new RuntimeException("Personaje no encontrado con ID: " + id);
        }
    }

    public PersonajeDTO updatePersonajeDTO(Long id, PersonajeDTO personajeDTO) {
        return personajeRepository.findById(id)
            .map(personaje -> {
                personaje.setNombre(personajeDTO.getNombre());
                personaje.setHabilidad(personajeDTO.getHabilidad());
                return personajeMapper.toDTO(personajeRepository.save(personaje));
            })
            .orElseThrow(() -> new RuntimeException("Personaje no encontrado con ID: " + id));
    }
}
