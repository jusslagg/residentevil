package com.ejemplo.residentevil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EnemigoCreateDTO {
    private Long id;

    @Schema(description = "Nombre del enemigo", example = "Zombi")
    private String nombre;

    @Schema(description = "Dirección del enemigo", example = "Av 123")
    private String direccion;

    @Schema(description = "Teléfono del enemigo", example = "99887766")
    private String telefono;

    public EnemigoCreateDTO() {
    }

    public EnemigoCreateDTO(Long id, String nombre, String direccion, String telefono) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }
}
