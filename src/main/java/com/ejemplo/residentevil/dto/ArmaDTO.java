package com.ejemplo.residentevil.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArmaDTO {
    @Schema(description = "Unique identifier of the weapon", example = "1")
    private Long id;

    @Schema(description = "Name of the weapon", example = "Handgun")
    private String nombre;

    @Schema(description = "Type of the weapon", example = "Firearm")
    private String tipo;

    @Schema(description = "Damage caused by the weapon", example = "50.0")
    private Double daño;

    @Schema(description = "Stock of the weapon", example = "10")
    private int stock;

    // Constructor vacío
    public ArmaDTO() {}

    // Constructor con parámetros
    public ArmaDTO(Long id, String nombre, String tipo, Double daño, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.daño = daño;
        this.stock = stock;
    }
}
