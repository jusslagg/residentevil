package com.ejemplo.residentevil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class PersonajeDTO {
    @Schema(description = "Unique identifier of the character", example = "1")
    private Long id;

    @Schema(description = "Name of the character", example = "Leon S. Kennedy")
    private String nombre;

    @Schema(description = "Ability of the character", example = "Master of firearms")
    private String habilidad;

    @Schema(description = "Set of weapons associated with the character")
    private Set<ArmaDTO> armas;

    // Constructor vacío
    public PersonajeDTO() {}

    // Constructor con parámetros
    public PersonajeDTO(Long id, String nombre, String habilidad, Set<ArmaDTO> armas) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.armas = armas;
    }
}
