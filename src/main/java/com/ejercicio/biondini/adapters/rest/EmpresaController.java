package com.ejercicio.biondini.adapters.rest;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ejercicio.biondini.application.EmpresaService;
import com.ejercicio.biondini.domain.Empresa;

import java.util.List;

@RestController
@RequestMapping("/empresas")
@RequiredArgsConstructor
public class EmpresaController {

    private final EmpresaService empresaService;

    /**
     * Endpoint: GET /empresas/transferencias-ultimo-mes
     * Retorna las empresas que han realizado transferencias en el último mes.
     */
    @GetMapping("/transferencias-ultimo-mes")
    public ResponseEntity<List<Empresa>> getEmpresasConTransferenciasUltimoMes() {
        List<Empresa> empresas = empresaService.obtenerEmpresasConTransferenciasUltimoMes();
        return ResponseEntity.ok(empresas);
    }

    /**
     * Endpoint: GET /empresas/adhesion-ultimo-mes
     * Retorna las empresas que se adhirieron en el último mes.
     */
    @GetMapping("/adhesion-ultimo-mes")
    public ResponseEntity<List<Empresa>> getEmpresasConAdhesionUltimoMes() {
        List<Empresa> empresas = empresaService.obtenerEmpresasConAdhesionUltimoMes();
        return ResponseEntity.ok(empresas);
    }

    /**
     * Endpoint: POST /empresas/adhesion
     * Registra (adhiere) una nueva empresa.
     */
    @PostMapping("/adhesion")
    public ResponseEntity<Empresa> registrarEmpresa(@Valid @RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.registrarEmpresa(empresa);
        return ResponseEntity.ok(nuevaEmpresa);
    }
}