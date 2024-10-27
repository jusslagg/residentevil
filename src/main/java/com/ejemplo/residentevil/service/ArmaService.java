package com.ejemplo.residentevil.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ejemplo.residentevil.dto.ArmaDTO;
import com.ejemplo.residentevil.mapper.ArmaMapper;
import com.ejemplo.residentevil.model.Arma;
import com.ejemplo.residentevil.repository.ArmaRepository;

@Service
public class ArmaService {
    @Autowired
    private final ArmaRepository armaRepository;
    @Autowired
    private final ArmaMapper armaMapper;

    public ArmaService(ArmaRepository armaRepository, ArmaMapper armaMapper) {
        this.armaRepository = armaRepository;
        this.armaMapper = armaMapper;
    }

    public List<ArmaDTO> getAllArmas() {
        List<Arma> armas = armaRepository.findAll();
        if (armas.isEmpty()) {
            throw new RuntimeException("No se encontraron armas");
        }

        return armas.stream()
                .map(armaMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<ArmaDTO> getArmaById(Long id) {
        return armaRepository.findById(id)
                .map(armaMapper::toDTO)
                .or(() -> {
                    throw new RuntimeException("Arma no encontrada con id: " + id);
                });
    }

    public ArmaDTO saveArma(ArmaDTO armaDTO) {
        Arma arma = armaMapper.toEntity(armaDTO);
        Arma savedArma = armaRepository.save(arma);
        return armaMapper.toDTO(savedArma);
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
                return armaMapper.toDTO(armaRepository.save(arma));
            })
            .orElseThrow(() -> new RuntimeException("Arma no encontrada con id: " + armaId));
    }
}
