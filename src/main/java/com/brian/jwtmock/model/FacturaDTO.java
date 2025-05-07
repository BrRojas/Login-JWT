package com.brian.jwtmock.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class FacturaDTO {
    private Long id;
    private String numero;
    private String descripcion;
    private BigDecimal monto;
    private LocalDateTime fechaCreacion;
    private String usuarioUsername; // Solo para mostrar el username del usuario
    private EstadoFactura estado;

    // Constructor vacío
    public FacturaDTO() {}

    // Constructor con todos los campos
    public FacturaDTO(Long id, String numero, String descripcion, BigDecimal monto, 
                     LocalDateTime fechaCreacion, String usuarioUsername, EstadoFactura estado) {
        this.id = id;
        this.numero = numero;
        this.descripcion = descripcion;
        this.monto = monto;
        this.fechaCreacion = fechaCreacion;
        this.usuarioUsername = usuarioUsername;
        this.estado = estado;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getUsuarioUsername() {
        return usuarioUsername;
    }

    public void setUsuarioUsername(String usuarioUsername) {
        this.usuarioUsername = usuarioUsername;
    }

    public EstadoFactura getEstado() {
        return estado;
    }

    public void setEstado(EstadoFactura estado) {
        this.estado = estado;
    }
}
