package com.ejemplo.residentevil.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ejemplo.residentevil.model.Arma;

@Repository
public interface ArmaRepository extends JpaRepository<Arma, Long> {

}
