package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.service.ArmaService;
import com.ejemplo.residentevil.utils.ApiResponseMsg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
