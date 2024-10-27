package com.ejemplo.residentevil.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PersonajeDTO {
    @Schema(description = "Identificador único del personaje", example = "1")
    private Long id;
    
    @Schema(description = "Nombre del personaje", example = "Leon S. Kennedy")
    private String name;
    
    @Schema(description = "Email del personaje", example = "leon.kennedy@example.com")
    private String email;
    
    @Schema(description = "Número de teléfono del personaje", example = "1234567890")
    private String phone;
    
    private Set<Long> enemigoIds;  // Cambiado de panaderiaIds a enemigoIds

    public PersonajeDTO() {
    }

    public PersonajeDTO(Long id, String name, String email, String phone, Set<Long> enemigoIds) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.enemigoIds = enemigoIds;  // Cambiado de panaderiaIds a enemigoIds
    }

    public Set<Long> getEnemigoIds() {
        return enemigoIds;  // Cambiado de getPanaderiaIds a getEnemigoIds
    }

    public void setEnemigoIds(Set<Long> enemigoIds) {
        this.enemigoIds = enemigoIds;  // Cambiado de setPanaderiaIds a setEnemigoIds
    }
}
