package com.ejercicio.biondini.adapters.persistence;


import org.springframework.stereotype.Component;

import com.ejercicio.biondini.domain.Empresa;
import com.ejercicio.biondini.ports.EmpresaRepositoryPort;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmpresaRepositoryAdapter implements EmpresaRepositoryPort {

    private final EmpresaRepository empresaRepository;

    public EmpresaRepositoryAdapter(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Override
    public Empresa save(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    public List<Empresa> findByFechaAdhesionAfter(LocalDate date) {
        return empresaRepository.findByFechaAdhesionAfter(date);
    }

    @Override
    public List<Empresa> findByIdIn(List<Long> ids) {
        return empresaRepository.findAllById(ids);
    }
}