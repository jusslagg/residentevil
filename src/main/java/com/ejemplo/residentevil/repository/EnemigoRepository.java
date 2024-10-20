package com.ejemplo.residentevil.repository;

import com.ejemplo.residentevil.model.Enemigo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {
    List<Enemigo> findByTipo(String tipo);
    Long countByTipo(String tipo);
}
