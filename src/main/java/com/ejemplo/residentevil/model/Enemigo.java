package com.ejemplo.residentevil.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Enemigo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int salud;
    private String tipo; // Puede ser "zombie", "mutante", etc.

    @ManyToMany(mappedBy = "enemigos", fetch = FetchType.LAZY)
    private Set<Arma> armas = new HashSet<>(); // Inicializa el set

    public Enemigo() {
    }

    public Enemigo(Long id, String nombre, int salud, String tipo, Set<Arma> armas) {
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.tipo = tipo;
        this.armas = armas != null ? armas : new HashSet<>(); // Manejo de nulos
    }

    public Set<Arma> getArmas() {
        return armas;
    }

    public void setArmas(Set<Arma> armas) {
        this.armas = armas != null ? armas : new HashSet<>(); // Manejo de nulos
    }
}
