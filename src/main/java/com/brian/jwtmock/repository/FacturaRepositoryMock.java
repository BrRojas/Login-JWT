package com.brian.jwtmock.repository;

import com.brian.jwtmock.model.Factura;
import com.brian.jwtmock.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class FacturaRepositoryMock {
    private final List<Factura> facturas = new ArrayList<>();

    public List<Factura> findAll() {
        return new ArrayList<>(facturas);
    }

    public List<Factura> findByUsuario(User usuario) {
        return facturas.stream()
                .filter(f -> f.getUsuario().getUsername().equals(usuario.getUsername()))
                .collect(Collectors.toList());
    }

    public Optional<Factura> findById(Long id) {
        return facturas.stream()
                .filter(f -> f.getId().equals(id))
                .findFirst();
    }

    public Factura save(Factura factura) {
        if (factura.getId() == null) {
            factura.setId((long) (facturas.size() + 1));
        }
        facturas.add(factura);
        return factura;
    }

    public void delete(Factura factura) {
        facturas.removeIf(f -> f.getId().equals(factura.getId()));
    }

    public boolean existsById(Long id) {
        return facturas.stream().anyMatch(f -> f.getId().equals(id));
    }
} 