package com.ejercicio.biondini.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "TRANSFERENCIA")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private Long id;
    @Column(name = "IMPORTE")
    private BigDecimal importe;
    @ManyToOne
    @JoinColumn(name = "EMPRESA_ID")
    private Empresa empresa;
    @Size(max = 255)
    @Column(name = "CUENTA_DEBITO")
    private String cuentaDebito;
    @Size(max = 255)
    @Column(name = "CUENTA_CREDITO")
    private String cuentaCredito;
    @Column(name = "FECHA_TRANSFERENCIA", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaTransferencia;
}