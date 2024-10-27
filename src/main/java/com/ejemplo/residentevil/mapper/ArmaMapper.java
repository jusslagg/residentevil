package com.ejemplo.residentevil.mapper;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.model.Arma;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ArmaMapper {

    public ArmaDTO toDTO(Arma arma) {
        if (arma == null) {
            throw new IllegalArgumentException("La entidad no puede ser nula");
        }

        return ArmaDTO.builder()
                .id(arma.getId())
                .nombre(arma.getNombre())
                .daño(arma.getDaño())
                .tipo(arma.getTipo())
                .stock(arma.getStock())
                .build();
    }

    public Arma toEntity(ArmaDTO armaDTO) {
        if (armaDTO == null) {
            throw new IllegalArgumentException("El armaDTO no puede ser nulo");
        }

        Arma arma = new Arma();
        arma.setId(armaDTO.getId());
        arma.setNombre(armaDTO.getNombre());
        arma.setDaño(armaDTO.getDaño());
        arma.setTipo(armaDTO.getTipo());
        arma.setStock(armaDTO.getStock());
        return arma;
    }

    public Collection<Arma> getPersonajes() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPersonajes'");
    }
}
