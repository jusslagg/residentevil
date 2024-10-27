package com.ejemplo.residentevil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.residentevil.model.Enemigo;

@Repository
public interface EnemigoRepository extends JpaRepository<Enemigo, Long> {

    Optional<Enemigo> findById(Enemigo enemigoId);
}
