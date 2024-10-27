package com.ejemplo.residentevil.mapper;

import org.springframework.stereotype.Component;

import com.ejemplo.residentevil.dto.EnemigoCreateDTO;
import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.model.Enemigo;


import java.util.stream.Collectors;

@Component
public class EnemigoMapper {

    private final ArmaMapper armaMapper;

    public EnemigoMapper(PersonajeMapper personajeMapper, ArmaMapper armaMapper) {
        this.armaMapper = armaMapper;
    }

    public EnemigoDTO toDTOEnemigo(Enemigo enemigo, boolean includeRelations) {
        if (enemigo == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        EnemigoDTO.EnemigoDTOBuilder builder = EnemigoDTO.builder()
                .id(enemigo.getId())
                .nombre(enemigo.getNombre());

        if (includeRelations) {
            builder.armas(enemigo.getArmas().stream()
                    .map(armaMapper::toDTOArma)
                    .collect(Collectors.toSet()));
        }

        return builder.build();
    }

    public Enemigo toEntity(EnemigoCreateDTO enemigoCreateDTO) {
        if (enemigoCreateDTO == null) {
            throw new IllegalArgumentException("El enemigoCreateDTO no puede ser nulo");
        }

        Enemigo enemigo = new Enemigo();
        enemigo.setId(enemigoCreateDTO.getId());
        enemigo.setNombre(enemigoCreateDTO.getNombre());
        return enemigo;
    }
}