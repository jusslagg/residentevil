package com.ejemplo.residentevil.repository;

import com.ejemplo.residentevil.model.Personaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PersonajeRepository extends JpaRepository<Personaje, Long> {
    List<Personaje> findByNombre(Personaje personaje);
    List<Personaje> findByHabilidad(String habilidad);
}
