package com.ejemplo.residentevil.model;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Personaje {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String habilidad;

    // Constructor vacío
    public Personaje() {
    }

    // Constructor con parámetros
    public Personaje(Long id, String nombre, String habilidad) {
        this.id = id;
        this.nombre = nombre;
        this.habilidad = habilidad;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidad() {
        return habilidad;
    }

    public void setHabilidad(String habilidad) {
        this.habilidad = habilidad;
    }

    public void setArmas(Set<Arma> armas) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setArmas'");
    }
}
