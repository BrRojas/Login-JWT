package com.brian.jwtmock.controller;

import com.brian.jwtmock.model.FacturaDTO;
import com.brian.jwtmock.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/facturas")
public class FacturaController {
    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @GetMapping
    public ResponseEntity<List<FacturaDTO>> getAllFacturas(@AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(facturaService.findAll(userDetails));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacturaDTO> getFacturaById(@PathVariable Long id, 
                                                   @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(facturaService.findById(id, userDetails));
    }

    @PostMapping
    public ResponseEntity<FacturaDTO> createFactura(@RequestBody FacturaDTO facturaDTO,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(facturaService.create(facturaDTO, userDetails));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacturaDTO> updateFactura(@PathVariable Long id,
                                                  @RequestBody FacturaDTO facturaDTO,
                                                  @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.ok(facturaService.update(id, facturaDTO, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFactura(@PathVariable Long id,
                                            @AuthenticationPrincipal UserDetails userDetails) {
        facturaService.delete(id, userDetails);
        return ResponseEntity.ok().build();
    }
} 