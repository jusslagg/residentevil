package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.mapper.ArmaMapper;
import com.ejemplo.residentevil.model.Arma;
import com.ejemplo.residentevil.service.ArmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/armas")
public class ArmaController {
    @Autowired
    private ArmaService armaService;

    @GetMapping
    public List<ArmaDTO> getAllArmas() {
        return armaService.getAllArmas().stream()
                .map(ArmaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArmaDTO> getArmaById(@PathVariable Long id) {
        Optional<Arma> arma = armaService.getArmaById(id);
        return arma.map(value -> ResponseEntity.ok(ArmaMapper.toDTO(value)))
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ArmaDTO createArma(@RequestBody ArmaDTO armaDTO) {
        Arma arma = ArmaMapper.toEntity(armaDTO);
        return ArmaMapper.toDTO(armaService.saveArma(arma));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArmaDTO> updateArma(@PathVariable Long id, @RequestBody ArmaDTO armaDTO) {
        armaDTO.setId(id);
        Arma arma = ArmaMapper.toEntity(armaDTO);
        return ResponseEntity.ok(ArmaMapper.toDTO(armaService.saveArma(arma)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArma(@PathVariable Long id) {
        armaService.deleteArma(id);
        return ResponseEntity.noContent().build();
    }
}
