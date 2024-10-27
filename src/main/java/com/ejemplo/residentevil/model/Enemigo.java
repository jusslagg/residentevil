package com.ejemplo.residentevil.model;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "enemigo_arma",
        joinColumns = @JoinColumn(name = "enemigo_id"),
        inverseJoinColumns = @JoinColumn(name = "arma_id")
    )
    private Set<Arma> armas; // Conjunto de armas que puede usar el enemigo

    public Enemigo() {
    }

    public Enemigo(Long id, String nombre, int salud, String tipo, Set<Arma> armas) {
        this.id = id;
        this.nombre = nombre;
        this.salud = salud;
        this.tipo = tipo;
        this.armas = armas;
    }

    public Set<Arma> getArmas() {
        return armas;
    }

    public void setArmas(Set<Arma> armas) {
        this.armas = armas;
    }
}
