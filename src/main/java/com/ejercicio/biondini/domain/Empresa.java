package com.ejercicio.biondini.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "EMPRESA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "CUIT")
    private String cuit;
    @Column(name = "RAZON_SOCIAL")
    private String razonSocial;
    // La fecha de adhesión se puede proporcionar o generarse automáticamente.
    @Column(name = "FECHA_ADHESION", updatable = false)
    @CreationTimestamp
    private LocalDate fechaAdhesion;
}