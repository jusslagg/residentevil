package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.ArmaCreateDTO;
import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.services.ArmaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/armas")
public class ArmaController {

    @Autowired
    private ArmaService armaService;

    @GetMapping("/all")
    public ResponseEntity<?> getAllArmas() {
        try {
            List<ArmaDTO> armas = armaService.getAllArmas();
            return ResponseEntity.ok().body(new ApiResponseMsg("Lista de armas", armas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("NO HAY ARMAS", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getArmaById(@PathVariable("id") Long id) {
        try {
            Optional<ArmaDTO> arma = armaService.getArmaById(id);
            return arma.map(ResponseEntity::ok)
                       .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseMsg("Arma no encontrada", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al obtener el arma", e.getMessage()));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ArmaDTO> createArma(@RequestBody ArmaCreateDTO armaCreateDTO) {
        try {
            ArmaDTO createdArma = armaService.saveArma(armaCreateDTO);
            return new ResponseEntity<>(createdArma, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear el arma", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteArma(@PathVariable Long id) {
        try {
            armaService.deleteArma(id);
            return ResponseEntity.ok().body(new ApiResponseMsg("Arma eliminada", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el arma", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            armaService.updateStockArma(id, nuevoStock);
            return ResponseEntity.ok().body(new ApiResponseMsg("Stock actualizado", id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Arma no encontrada con id: " + id);
        }
    }
}
