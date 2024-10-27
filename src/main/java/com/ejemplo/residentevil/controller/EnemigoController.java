package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.EnemigoCreateDTO;
import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.services.EnemigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enemigos")
public class EnemigoController {

    @Autowired
    private final EnemigoService enemigoService;

    public EnemigoController(EnemigoService enemigoService) {
        this.enemigoService = enemigoService;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los enemigos", description = "Retorna todos los enemigos")
    @ApiResponse(responseCode = "200", description = "Operación exitosa", content = @Content(schema = @Schema(implementation = EnemigoDTO.class)))
    public ResponseEntity<List<EnemigoDTO>> getAllEnemigos() {
        List<EnemigoDTO> enemigos = enemigoService.getAllEnemigos();
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener enemigo por ID", description = "Retorna un enemigo específico")
    public ResponseEntity<?> getEnemigoById(@PathVariable("id") Long id) {
        try {
            Optional<EnemigoDTO> enemigo = enemigoService.getEnemigoById(id);
            return enemigo.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo enemigo", description = "Crea un nuevo enemigo a partir de un DTO")
    public ResponseEntity<EnemigoDTO> createEnemigo(@RequestBody EnemigoCreateDTO enemigoCreateDTO) {
        try {
            EnemigoDTO createdEnemigo = enemigoService.saveEnemigo(enemigoCreateDTO);
            return ResponseEntity.status(201).body(createdEnemigo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Eliminar enemigo", description = "Elimina un enemigo por ID")
    public ResponseEntity<?> deleteEnemigo(@PathVariable("id") Long id) {
        try {
            enemigoService.deleteEnemigo(id);
            return ResponseEntity.ok().body("Enemigo eliminado: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: No se pudo eliminar el enemigo, " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar enemigo", description = "Actualiza un enemigo existente")
    public ResponseEntity<EnemigoDTO> updateEnemigo(@PathVariable Long id, @RequestBody EnemigoCreateDTO enemigoCreateDTO) {
        try {
            EnemigoDTO updatedEnemigo = enemigoService.updateEnemigo(id, enemigoCreateDTO);
            return ResponseEntity.ok(updatedEnemigo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
