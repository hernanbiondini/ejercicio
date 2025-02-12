package com.ejercicio.biondini.application;


import org.springframework.stereotype.Service;

import com.ejercicio.biondini.domain.Empresa;
import com.ejercicio.biondini.domain.Transferencia;
import com.ejercicio.biondini.ports.EmpresaRepositoryPort;
import com.ejercicio.biondini.ports.TransferenciaRepositoryPort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class EmpresaService {

    private final EmpresaRepositoryPort empresaRepository;
    private final TransferenciaRepositoryPort transferenciaRepository;

    public EmpresaService(EmpresaRepositoryPort empresaRepository, TransferenciaRepositoryPort transferenciaRepository) {
        this.empresaRepository = empresaRepository;
        this.transferenciaRepository = transferenciaRepository;
    }

    /**
     * Registra una nueva empresa. Si no se proporciona la fecha de adhesión,
     * se asigna la fecha actual.
     */
    public Empresa registrarEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    /**
     * Obtiene las empresas que se adhirieron en el último mes.
     */
    public List<Empresa> obtenerEmpresasConAdhesionUltimoMes() {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);
        return empresaRepository.findByFechaAdhesionAfter(oneMonthAgo);
    }

    /**
     * Obtiene las empresas que realizaron transferencias en el último mes.
     * Se filtran las transferencias posteriores a hace un mes y se extraen los IDs de las empresas.
     */
    public List<Empresa> obtenerEmpresasConTransferenciasUltimoMes() {
        LocalDateTime oneMonthAgo = LocalDateTime.now().minusMonths(1);
        List<Transferencia> transferencias = transferenciaRepository.findByFechaTransferenciaAfter(oneMonthAgo);
        List<Long> empresaIds = transferencias.stream()
                .map(t -> t.getEmpresa().getId())
                .distinct()
                .toList();
        if (empresaIds.isEmpty()) {
            return List.of();
        }
        return empresaRepository.findByIdIn(empresaIds);
    }
}