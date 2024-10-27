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

    private int stock; // Agregar atributo stock

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "arma_enemigo",
        joinColumns = @JoinColumn(name = "arma_id"),
        inverseJoinColumns = @JoinColumn(name = "enemigo_id")
    )
    private Set<Enemigo> enemigos; // Inicializa el set

    public Arma() {
    }

    public Arma(Long id, String nombre, Double daño, String tipo, int stock, Set<Enemigo> enemigos) {
        this.id = id;
        this.nombre = nombre;
        this.daño = daño;
        this.tipo = tipo;
        this.stock = stock; // Manejo de stock
        this.enemigos = enemigos; // Manejo de nulos
    }

    public int getStock() {
        return stock; // Método getter para stock
    }

    public void setStock(int stock) {
        this.stock = stock; // Método setter para stock
    }

    public Set<Enemigo> getEnemigos() {
        return enemigos;
    }

    public void setEnemigos(Set<Enemigo> enemigos) {
        this.enemigos = enemigos; // Manejo de nulos
    }
}
