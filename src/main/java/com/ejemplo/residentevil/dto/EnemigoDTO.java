package com.ejemplo.residentevil.dto;

import lombok.Builder;
import lombok.Data;
import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;

@Data
@Builder
public class EnemigoDTO {
    @Schema(description = "Id del enemigo", example = "1")
    private Long id;

    @Schema(description = "Nombre del enemigo", example = "Zombi")
    private String nombre;

    @Schema(description = "Dirección del enemigo", example = "AV 123")
    private String direccion;

    @Schema(description = "Teléfono del enemigo", example = "99887766")
    private String telefono;

    private Set<PersonajeDTO> personajes;
    private Set<ArmaDTO> armas;

    public EnemigoDTO() {
    }

    public EnemigoDTO(Long id, String nombre, String direccion, String telefono, Set<PersonajeDTO> personajes, Set<ArmaDTO> armas) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.personajes = personajes;
        this.armas = armas;
    }
}
