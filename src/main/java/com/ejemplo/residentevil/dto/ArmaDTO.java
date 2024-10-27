package com.ejemplo.residentevil.dto;

import java.util.Set;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArmaDTO {
    @Schema(description = "Id del arma", example = "1")
    private Long id;

    @Schema(description = "Nombre del arma", example = "Escopeta")
    private String nombre;

    @Schema(description = "Precio del arma", example = "350.0")
    private Double precio;

    @Schema(description = "Stock del arma", example = "15")
    private int stock;

    @Schema(description = "Categoría del arma", example = "escopetas")
    private String categoria;

    private Set<Long> enemigoIds;

    public ArmaDTO() {
    }

    public ArmaDTO(Long id, String nombre, Double precio, int stock, String categoria, Set<Long> enemigoIds) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.enemigoIds = enemigoIds;
    }

    public Set<Long> getEnemigoIds() {
        return enemigoIds;
    }

    public void setEnemigoIds(Set<Long> enemigoIds) {
        this.enemigoIds = enemigoIds;
    }
}
