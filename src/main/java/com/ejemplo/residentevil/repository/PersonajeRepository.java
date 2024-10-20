package com.ejemplo.residentevil.repository;

import com.ejemplo.residentevil.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    List<Personaje> findByNombre(String nombre);
    List<Personaje> findByHabilidad(String habilidad);
}
