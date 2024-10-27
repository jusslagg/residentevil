package com.ejemplo.residentevil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnemigoDTO {
    @Schema(description = "Unique identifier of the enemy", example = "1")
    private Long id;

    @Schema(description = "Name of the enemy", example = "Zombie")
    private String nombre;

    @Schema(description = "Type of the enemy", example = "Undead")
    private String tipo;

    // Constructor vacío
    public EnemigoDTO() {}

    // Constructor con parámetros
    public EnemigoDTO(Long id, String nombre, String tipo) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
    }
}