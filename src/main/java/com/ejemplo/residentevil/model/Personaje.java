package com.ejemplo.residentevil.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String habilidad;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "personaje_arma",
        joinColumns = @JoinColumn(name = "personaje_id"),
        inverseJoinColumns = @JoinColumn(name = "arma_id")
    )
    private Set<Arma> armas = new HashSet<>(); // Aquí debería funcionar si Arma está bien definida

    // Constructor vacío
    public Personaje() {
    }

    // Constructor con parámetros
    public Personaje(Long id, String nombre, String habilidad, Set<Arma> armas) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
        this.armas = armas;
    }
}
