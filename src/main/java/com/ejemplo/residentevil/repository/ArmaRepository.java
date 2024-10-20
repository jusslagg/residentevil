package com.ejemplo.residentevil.repository;

import com.ejemplo.residentevil.model.Arma;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArmaRepository extends JpaRepository<Arma, Long> {
    List<Arma> findByTipo(String tipo);
    List<Arma> findByNombreContaining(String nombre);
}
