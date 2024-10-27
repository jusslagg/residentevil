package com.ejemplo.residentevil.model;

import java.util.Set;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class Arma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private Double daño;
    private String tipo;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "arma_enemigo",
        joinColumns = @JoinColumn(name = "arma_id"),
        inverseJoinColumns = @JoinColumn(name = "enemigo_id")
    )
    private Set<Enemigo> enemigos;

    public Arma() {
    }

    public Arma(Long id, String nombre, Double daño, String tipo, Set<Enemigo> enemigos) {
        this.id = id;
        this.nombre = nombre;
        this.daño = daño;
        this.tipo = tipo;
        this.enemigos = enemigos;
    }

    public Set<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(Set<Enemigo> enemigos) {
        this.enemigos = enemigos;
    }
}
