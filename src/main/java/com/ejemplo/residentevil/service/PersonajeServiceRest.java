package com.ejemplo.residentevil.service;

import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.ejemplo.residentevil.dto.PersonajeDTO;
import com.ejemplo.residentevil.mapper.PersonajeMapper;
import com.ejemplo.residentevil.model.Personaje;
import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.repository.PersonajeRepository;
import com.ejemplo.residentevil.repository.EnemigoRepository;
import jakarta.transaction.Transactional;

@Service
public class PersonajeServiceRest {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    @Autowired
    private final PersonajeRepository personajeRepository;
    @Autowired
    private final PersonajeMapper personajeMapper;
    @Autowired
    private RestTemplate restTemplate;

    public PersonajeServiceRest(PersonajeRepository personajeRepository, EnemigoRepository enemigoRepository, PersonajeMapper personajeMapper) {
        this.personajeRepository = personajeRepository;
        this.personajeMapper = personajeMapper;
    }

    @Autowired
    private EnemigoRepository enemigoRepository;

    public List<PersonajeDTO> getAllPersonajes() {
        List<PersonajeDTO> personajesDB = personajeRepository.findAll().stream()
                .map(personajeMapper::toDTOPersonaje)
                .collect(Collectors.toList());

        Personaje[] personajesAPI = restTemplate.getForObject(BASE_URL, Personaje[].class);

        if (personajesAPI != null) {
            for (Personaje personaje : personajesAPI) {
                personajesDB.add(personajeMapper.toDTOPersonaje(personaje));
            }
        }

        return personajesDB;
    }

    public PersonajeDTO getPersonajeById(Long id) {
        Optional<Personaje> optionalPersonaje = personajeRepository.findById(id);

        if (optionalPersonaje.isPresent()) {
            return personajeMapper.toDTOPersonaje(optionalPersonaje.get());
        } else {
            // Si el personaje no está en la base de datos, buscar en la API externa
            PersonajeDTO personajeDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonajeDTO.class, id);
            if (personajeDTO != null) {
                return personajeDTO;
            } else {
                throw new RuntimeException("Personaje no encontrado ni en la base de datos ni en la API externa");
            }
        }
    }

    @Transactional
    public PersonajeDTO savePersonajeFromApi(Long id) {
        PersonajeDTO personajeDTO = restTemplate.getForObject(BASE_URL + "/{id}", PersonajeDTO.class, id);

        if (personajeDTO != null) {
            Personaje personaje = personajeMapper.toEntity(personajeDTO);
            Personaje savedPersonaje = personajeRepository.save(personaje);
            return personajeMapper.toDTOPersonaje(savedPersonaje);
        } else {
            throw new RuntimeException("Personaje no encontrado en la API con ID: " + id);
        }
    }

    public PersonajeDTO savePersonajeDTO(PersonajeDTO personajeDTO) {
        // Crear un nuevo personaje a partir del DTO
        Personaje personaje = personajeMapper.toEntity(personajeDTO);

        // Si el DTO contiene IDs de enemigos, busca los enemigos correspondientes
        if (personajeDTO.getEnemigoIds() != null && !personajeDTO.getEnemigoIds().isEmpty()) {
            Set<Enemigo> enemigos = new HashSet<>();

            for (Long enemigoId : personajeDTO.getEnemigoIds()) {
                // Busca cada enemigo por su ID
                Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(enemigoId);
                // Si el enemigo existe, lo añade al conjunto
                optionalEnemigo.ifPresent(enemigos::add);
            }
        }

        // Guarda el personaje en la base de datos
        Personaje savedPersonaje = personajeRepository.save(personaje);
        // Retorna el DTO del personaje guardado
        return personajeMapper.toDTOPersonaje(savedPersonaje);
    }

    // Eliminar un personaje en la base de datos y en la API externa
    public void deletePersonaje(Long id) {
        if (personajeRepository.existsById(id)) {
            personajeRepository.deleteById(id);
            // Eliminar el personaje en la API externa
            restTemplate.delete(BASE_URL + "/{id}", id);
        } else {
            throw new RuntimeException("Personaje no encontrado con ID: " + id);
        }
    }

    public PersonajeDTO updatePersonajeDTO(Long id, PersonajeDTO personajeDTO) {
        return personajeRepository.findById(id)
            .map(personaje -> {
                personaje.setNombre(personajeDTO.getName());;
                return personajeMapper.toDTOPersonaje(personajeRepository.save(personaje));
            })
            .orElse(null);
    }

    public PersonajeDTO savePersonajeFromApi(PersonajeDTO personajeDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'savePersonajeFromApi'");
    }
}
