package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.mapper.EnemigoMapper;
import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.service.EnemigoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/enemigos")
public class EnemigoController {
    @Autowired
    private EnemigoService enemigoService;

    @GetMapping
    public List<EnemigoDTO> getAllEnemigos() {
        return enemigoService.getAllEnemigos().stream()
                .map(EnemigoMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnemigoDTO> getEnemigoById(@PathVariable Long id) {
        Optional<Enemigo> enemigo = enemigoService.getEnemigoById(id);
        return enemigo.map(value -> ResponseEntity.ok(EnemigoMapper.toDTO(value)))
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public EnemigoDTO createEnemigo(@RequestBody EnemigoDTO enemigoDTO) {
        Enemigo enemigo = EnemigoMapper.toEntity(enemigoDTO);
        return EnemigoMapper.toDTO(enemigoService.saveEnemigo(enemigo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EnemigoDTO> updateEnemigo(@PathVariable Long id, @RequestBody EnemigoDTO enemigoDTO) {
        enemigoDTO.setId(id);
        Enemigo enemigo = EnemigoMapper.toEntity(enemigoDTO);
        return ResponseEntity.ok(EnemigoMapper.toDTO(enemigoService.saveEnemigo(enemigo)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnemigo(@PathVariable Long id) {
        enemigoService.deleteEnemigo(id);
        return ResponseEntity.noContent().build();
    }
}
