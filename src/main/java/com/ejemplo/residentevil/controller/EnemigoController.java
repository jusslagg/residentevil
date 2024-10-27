package com.ejemplo.residentevil.controller;

import com.ejemplo.residentevil.dto.EnemigoCreateDTO;
import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.service.EnemigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/enemigos")
public class EnemigoController {

    private final EnemigoService enemigoService;

    @Autowired
    public EnemigoController(EnemigoService enemigoService) {
        this.enemigoService = enemigoService;
    }

    @GetMapping("/all")
    @Operation(summary = "Obtener todos los enemigos", 
               description = "Retorna todos los enemigos",
               responses = {
                   @ApiResponse(responseCode = "200", 
                                description = "Operación exitosa", 
                                content = @Content(schema = @Schema(implementation = EnemigoDTO.class))),
                   @ApiResponse(responseCode = "500", 
                                description = "Error interno del servidor")
               })
    public ResponseEntity<List<EnemigoDTO>> getAllEnemigos() {
        List<EnemigoDTO> enemigos = enemigoService.getAllEnemigos(true);
        return ResponseEntity.ok(enemigos);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener enemigo por ID", 
               description = "Retorna un enemigo específico",
               responses = {
                   @ApiResponse(responseCode = "200", 
                                description = "Enemigo encontrado", 
                                content = @Content(schema = @Schema(implementation = EnemigoDTO.class))),
                   @ApiResponse(responseCode = "404", 
                                description = "Enemigo no encontrado"),
                   @ApiResponse(responseCode = "400", 
                                description = "Error en la solicitud")
               })
    public ResponseEntity<?> getEnemigoById(@PathVariable("id") Long id) {
        try {
            Optional<EnemigoDTO> enemigo = enemigoService.getEnemigoById(id, true);
            return enemigo.map(ResponseEntity::ok)
                          .orElseThrow();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @PostMapping("/create")
    @Operation(summary = "Crear un nuevo enemigo", 
               description = "Crea un nuevo enemigo a partir de un DTO",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                   description = "DTO del enemigo a crear",
                   required = true,
                   content = @Content(schema = @Schema(implementation = EnemigoCreateDTO.class))),
               responses = {
                   @ApiResponse(responseCode = "201", 
                                description = "Enemigo creado", 
                                content = @Content(schema = @Schema(implementation = EnemigoDTO.class))),
                   @ApiResponse(responseCode = "400", 
                                description = "Error en la solicitud")
               })
    public ResponseEntity<?> createEnemigo(@RequestBody EnemigoCreateDTO enemigoCreateDTO) {
        try {
            EnemigoDTO createdEnemigo = enemigoService.saveEnemigo(enemigoCreateDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdEnemigo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar enemigo", 
               description = "Elimina un enemigo por ID",
               responses = {
                   @ApiResponse(responseCode = "200", 
                                description = "Enemigo eliminado"),
                   @ApiResponse(responseCode = "404", 
                                description = "Enemigo no encontrado"),
                   @ApiResponse(responseCode = "400", 
                                description = "Error en la solicitud")
               })
    public ResponseEntity<?> deleteEnemigo(@PathVariable("id") Long id) {
        try {
            enemigoService.deleteEnemigo(id);
            return ResponseEntity.ok("Enemigo eliminado: " + id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: No se pudo eliminar el enemigo, " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar enemigo", 
               description = "Actualiza un enemigo existente",
               requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                   description = "DTO del enemigo a actualizar",
                   required = true,
                   content = @Content(schema = @Schema(implementation = EnemigoCreateDTO.class))),
               responses = {
                   @ApiResponse(responseCode = "200", 
                                description = "Enemigo actualizado", 
                                content = @Content(schema = @Schema(implementation = EnemigoDTO.class))),
                   @ApiResponse(responseCode = "404", 
                                description = "Enemigo no encontrado"),
                   @ApiResponse(responseCode = "400", 
                                description = "Error en la solicitud")
               })
    public ResponseEntity<?> updateEnemigo(@PathVariable Long id, @RequestBody EnemigoCreateDTO enemigoCreateDTO) {
        try {
            EnemigoDTO updatedEnemigo = enemigoService.updateEnemigo(id, enemigoCreateDTO);
            return ResponseEntity.ok(updatedEnemigo);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}
