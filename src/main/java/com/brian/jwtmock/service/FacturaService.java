package com.brian.jwtmock.service;

import com.brian.jwtmock.model.Factura;
import com.brian.jwtmock.model.FacturaDTO;
import com.brian.jwtmock.model.User;
import com.brian.jwtmock.model.EstadoFactura;
import com.brian.jwtmock.repository.FacturaRepositoryMock;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacturaService {
    private final FacturaRepositoryMock facturaRepository;

    public FacturaService(FacturaRepositoryMock facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public List<FacturaDTO> findAll(UserDetails userDetails) {
        User user = (User) userDetails;
        List<Factura> facturas;
        
        // Si es admin, ve todas las facturas
        if (user.getUsername().equals("admin")) {
            facturas = facturaRepository.findAll();
        } else {
            // Si es usuario normal, solo ve las suyas
            facturas = facturaRepository.findByUsuario(user);
        }

        return facturas.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public FacturaDTO findById(Long id, UserDetails userDetails) {
        User user = (User) userDetails;
        return facturaRepository.findById(id)
                .filter(factura -> user.getUsername().equals("admin") || 
                        factura.getUsuario().getUsername().equals(user.getUsername()))
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Factura no encontrada"));
    }

    public FacturaDTO create(FacturaDTO facturaDTO, UserDetails userDetails) {
        User user = (User) userDetails;
        Factura factura = new Factura();
        factura.setNumero(facturaDTO.getNumero());
        factura.setDescripcion(facturaDTO.getDescripcion());
        factura.setMonto(facturaDTO.getMonto());
        factura.setUsuario(user);
        factura.setEstado(EstadoFactura.PENDIENTE);
        
        return convertToDTO(facturaRepository.save(factura));
    }

    public FacturaDTO update(Long id, FacturaDTO facturaDTO, UserDetails userDetails) {
        User user = (User) userDetails;
        return facturaRepository.findById(id)
                .filter(factura -> user.getUsername().equals("admin") || 
                        factura.getUsuario().getUsername().equals(user.getUsername()))
                .map(factura -> {
                    factura.setNumero(facturaDTO.getNumero());
                    factura.setDescripcion(facturaDTO.getDescripcion());
                    factura.setMonto(facturaDTO.getMonto());
                    if (facturaDTO.getEstado() != null) {
                        factura.setEstado(facturaDTO.getEstado());
                    }
                    return convertToDTO(facturaRepository.save(factura));
                })
                .orElseThrow(() -> new RuntimeException("Factura no encontrada o no tienes permiso para editarla"));
    }

    public void delete(Long id, UserDetails userDetails) {
        User user = (User) userDetails;
        facturaRepository.findById(id)
                .filter(factura -> user.getUsername().equals("admin") || 
                        factura.getUsuario().getUsername().equals(user.getUsername()))
                .ifPresentOrElse(
                    facturaRepository::delete,
                    () -> { throw new RuntimeException("Factura no encontrada o no tienes permiso para eliminarla"); }
                );
    }

    private FacturaDTO convertToDTO(Factura factura) {
        return new FacturaDTO(
            factura.getId(),
            factura.getNumero(),
            factura.getDescripcion(),
            factura.getMonto(),
            factura.getFechaCreacion(),
            factura.getUsuario().getUsername(),
            factura.getEstado()
        );
    }
} 