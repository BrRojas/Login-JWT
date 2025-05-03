package com.brian.jwtmock.model;

import lombok.Data;

@Data
public class FacturasDTO {
    private Integer factura;
    private float price;
    private EstadoFactura estado;
}
