package com.brian.jwtmock.model;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;


import com.fasterxml.jackson.annotation.JsonTypeId;
import lombok.Data;
import lombok.Generated;

@Data
public class Facturas {


    private Long id;
    private Double price;

    @Enumerated(EnumType.STRING)
    private EstadoFactura estado;
}
