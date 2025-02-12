package com.ejercicio.biondini.ports;

import java.time.LocalDateTime;
import java.util.List;
import com.ejercicio.biondini.domain.Transferencia;

public interface TransferenciaRepositoryPort {
    List<Transferencia> findByFechaTransferenciaAfter(LocalDateTime date);
    Transferencia save(Transferencia transferencia);
}