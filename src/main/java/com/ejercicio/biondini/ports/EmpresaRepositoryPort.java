package com.ejercicio.biondini.ports;


import java.time.LocalDate;
import java.util.List;

import com.ejercicio.biondini.domain.Empresa;

public interface EmpresaRepositoryPort {
    Empresa save(Empresa empresa);
    List<Empresa> findByFechaAdhesionAfter(LocalDate date);
    List<Empresa> findByIdIn(List<Long> ids);
}