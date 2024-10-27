package com.ejemplo.residentevil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnemigoCreateDTO {
    private Long id;

    @Schema(description = "Name of the enemy", example = "Zombie")
    private String nombre;

    @Schema(description = "Type of the enemy", example = "Undead")
    private String tipo;

    @Schema(description = "Description of the enemy's abilities", example = "Slow but deadly")
    private String descripcion;

    // Constructor vacío
    public EnemigoCreateDTO() {}

    // Constructor con parámetros
    public EnemigoCreateDTO(Long id, String nombre, String tipo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
    }
}
