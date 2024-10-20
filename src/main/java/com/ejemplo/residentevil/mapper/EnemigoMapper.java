package com.ejemplo.residentevil.mapper;

import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.model.Enemigo;

public class EnemigoMapper {
    public static EnemigoDTO toDTO(Enemigo enemigo) {
        return new EnemigoDTO(enemigo.getId(), enemigo.getNombre(), enemigo.getTipo());
    }

    public static Enemigo toEntity(EnemigoDTO enemigoDTO) {
        Enemigo enemigo = new Enemigo();
        enemigo.setId(enemigoDTO.getId());
        enemigo.setNombre(enemigoDTO.getNombre());
        enemigo.setTipo(enemigoDTO.getTipo());
        return enemigo;
    }
}
