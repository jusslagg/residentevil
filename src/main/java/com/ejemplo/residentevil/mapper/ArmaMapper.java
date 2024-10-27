package com.ejemplo.residentevil.mapper;

import java.util.Set;
import org.springframework.stereotype.Component;
import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.model.Arma;
import java.util.stream.Collectors;

@Component
public class ArmaMapper {

    public ArmaDTO toDTOArma(Arma arma) {
        if (arma == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        Set<Long> enemigoIds = arma.getEnemigos().stream()
                .map(Enemigo::getId)
                .collect(Collectors.toSet());

        return ArmaDTO.builder()
                .id(arma.getId())
                .nombre(arma.getNombre())
                .stock(arma.getStock())
                .enemigoIds(enemigoIds)
                .build();
    }

    public Arma toEntity(ArmaDTO armaDTO) {
        if (armaDTO == null) {
            throw new IllegalArgumentException("El armaDTO no puede ser nulo");
        }

        Arma arma = new Arma();
        arma.setId(armaDTO.getId());
        arma.setNombre(armaDTO.getNombre());
        arma.setStock(armaDTO.getStock());
        return arma;
    }
}
