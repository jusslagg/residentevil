package com.ejemplo.residentevil.mapper;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.model.Arma;

public class ArmaMapper {
    public static ArmaDTO toDTO(Arma arma) {
        return new ArmaDTO(arma.getId(), arma.getNombre(), arma.getTipo());
    }

    public static Arma toEntity(ArmaDTO armaDTO) {
        Arma arma = new Arma();
        arma.setId(armaDTO.getId());
        arma.setNombre(armaDTO.getNombre());
        arma.setTipo(armaDTO.getTipo());
        return arma;
    }
}
