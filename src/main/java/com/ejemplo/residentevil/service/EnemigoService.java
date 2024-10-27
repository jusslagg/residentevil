package com.ejemplo.residentevil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.residentevil.dto.EnemigoCreateDTO;
import com.ejemplo.residentevil.dto.EnemigoDTO;
import com.ejemplo.residentevil.mapper.EnemigoMapper;
import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.repository.EnemigoRepository;

@Service
public class EnemigoService {
    @Autowired
    private final EnemigoRepository enemigoRepository;
    @Autowired
    private final EnemigoMapper enemigoMapper;

    public EnemigoService(EnemigoRepository enemigoRepository, EnemigoMapper enemigoMapper) {
        this.enemigoRepository = enemigoRepository;
        this.enemigoMapper = enemigoMapper;
    }

    public List<EnemigoDTO> getAllEnemigos(boolean includeRelations) {
        return enemigoRepository.findAll().stream()
                .map(enemigo -> enemigoMapper.toDTO(enemigo, includeRelations))
                .collect(Collectors.toList());
    }

    public Optional<EnemigoDTO> getEnemigoById(Long id, boolean includeRelations) {
        return enemigoRepository.findById(id)
                .map(enemigo -> enemigoMapper.toDTO(enemigo, includeRelations));
    }

    public EnemigoDTO saveEnemigo(EnemigoCreateDTO enemigoCreateDTO) {
        Enemigo enemigo = enemigoMapper.toEntity(enemigoCreateDTO);
        Enemigo savedEnemigo = enemigoRepository.save(enemigo);
        return enemigoMapper.toDTO(savedEnemigo, false);
    }

    public EnemigoDTO updateEnemigo(Long id, EnemigoCreateDTO enemigoCreateDTO) {
        return enemigoRepository.findById(id)
            .map(enemigo -> {
                enemigo.setNombre(enemigoCreateDTO.getNombre());
                enemigo.setTipo(enemigoCreateDTO.getTipo());
                enemigo.setDescripcion(enemigoCreateDTO.getDescripcion()); // Suponiendo que hay un campo descripción
                return enemigoMapper.toDTO(enemigoRepository.save(enemigo), false);
            })
            .orElseThrow(() -> new RuntimeException("Enemigo no encontrado con id: " + id));
    }

    public void deleteEnemigo(Long id) {
        if (enemigoRepository.existsById(id)) {
            enemigoRepository.deleteById(id);
        } else {
            throw new RuntimeException("El enemigo no existe");
        }
    }
}
