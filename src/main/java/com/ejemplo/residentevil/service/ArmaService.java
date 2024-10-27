package com.ejemplo.residentevil.service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.mapper.ArmaMapper;
import com.ejemplo.residentevil.model.Enemigo;
import com.ejemplo.residentevil.model.Arma;
import com.ejemplo.residentevil.repository.EnemigoRepository;
import com.ejemplo.residentevil.repository.ArmaRepository;

@Service
public class ArmaService {
    @Autowired
    private final ArmaRepository armaRepository;
    @Autowired
    private final ArmaMapper armaMapper;
    @Autowired
    private EnemigoRepository enemigoRepository;

    public ArmaService(ArmaRepository armaRepository, ArmaMapper armaMapper) {
        this.armaRepository = armaRepository;
        this.armaMapper = armaMapper;
    }

    public List<ArmaDTO> getAllArmas() {
        if (armaRepository.findAll().isEmpty()) {
            throw new RuntimeException("No se encontraron armas");
        }

        return armaRepository.findAll()
                .stream()
                .map(armaMapper::toDTOArma)
                .collect(Collectors.toList());
    }

    public Optional<ArmaDTO> getArmaById(Long id) {
        if (armaRepository.findById(id).isEmpty()) {
            throw new RuntimeException("No se encontraron armas");
        }
        return armaRepository.findById(id).map(armaMapper::toDTOArma);
    }

    public ArmaDTO saveArma(ArmaDTO armaDTO) {
        Arma arma = armaMapper.toEntity(armaDTO);

        if (armaDTO.getEnemigoIds() != null && !armaDTO.getEnemigoIds().isEmpty()) {
            Set<Enemigo> enemigos = new HashSet<>();

            for (Long enemigoId : armaDTO.getEnemigoIds()) {
                Optional<Enemigo> optionalEnemigo = enemigoRepository.findById(enemigoId);
                optionalEnemigo.ifPresent(enemigos::add);
            }
            arma.setEnemigos(enemigos);
        }
        Arma savedArma = armaRepository.save(arma);
        return armaMapper.toDTOArma(savedArma);
    }

    public void deleteArma(Long id) {
        if (armaRepository.existsById(id)) {
            armaRepository.deleteById(id);
        } else {
            throw new RuntimeException("El arma no existe");
        }
    }

    public ArmaDTO updateStockArma(Long armaId, int nuevoStock) {
        return armaRepository.findById(armaId)
            .map(arma -> {
                int stockActual = arma.getStock();
                int stockFinal = stockActual + nuevoStock;
                if (stockFinal < 0) {
                    throw new IllegalArgumentException("El stock no puede ser negativo.");
                }

                arma.setStock(stockFinal);
                return armaMapper.toDTOArma(armaRepository.save(arma));
            })
            .orElseThrow(() -> new RuntimeException("Arma no encontrada con id: " + armaId));
    }
}
