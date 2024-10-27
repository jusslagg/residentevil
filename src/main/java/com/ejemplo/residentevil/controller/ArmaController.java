package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.service.ArmaService;
import com.ejemplo.residentevil.utils.ApiResponseMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional; // Importación correcta

@RestController
@RequestMapping("/api/armas")
public class ArmaController {

    @Autowired
    private ArmaService armaService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponseMsg> getAllArmas() {
        try {
            List<ArmaDTO> armas = armaService.getAllArmas();
            return ResponseEntity.ok(new ApiResponseMsg("Lista de armas", armas));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("No hay armas", e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> getArmaById(@PathVariable Long id) {
        try {
            Optional<ArmaDTO> armaOpt = armaService.getArmaById(id);
            return armaOpt.map(arma -> ResponseEntity.ok(new ApiResponseMsg("Arma encontrada", arma)))
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                                                          .body(new ApiResponseMsg("Error: Arma no encontrada", null)));
        } catch (Exception e) {
            return ResponseEntity.badRequest()
                                 .body(new ApiResponseMsg("Error: " + e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponseMsg> createArma(@RequestBody ArmaDTO armaDTO) {
        try {
            ArmaDTO createdArma = armaService.saveArma(armaDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponseMsg("Arma creada", createdArma));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error al crear arma", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseMsg> deleteArma(@PathVariable Long id) {
        try {
            armaService.deleteArma(id);
            return ResponseEntity.ok(new ApiResponseMsg("Arma eliminada", id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: No se pudo eliminar el arma", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponseMsg> updateStock(@PathVariable Long id, @RequestParam int nuevoStock) {
        try {
            ArmaDTO updatedArma = armaService.updateStockArma(id, nuevoStock);
            return ResponseEntity.ok(new ApiResponseMsg("Stock actualizado", updatedArma));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new ApiResponseMsg("Error: " + e.getMessage(), null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponseMsg("Arma no encontrada con id: " + id, null));
        }
    }
}
