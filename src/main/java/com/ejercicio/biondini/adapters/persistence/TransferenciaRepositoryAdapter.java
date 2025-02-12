package com.ejercicio.biondini.adapters.persistence;

import org.springframework.stereotype.Component;
import com.ejercicio.biondini.domain.Transferencia;
import com.ejercicio.biondini.ports.TransferenciaRepositoryPort;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class TransferenciaRepositoryAdapter implements TransferenciaRepositoryPort {

    private final TransferenciaRepository transferenciaRepository;

    public TransferenciaRepositoryAdapter(TransferenciaRepository transferenciaRepository) {
        this.transferenciaRepository = transferenciaRepository;
    }

    @Override
    public List<Transferencia> findByFechaTransferenciaAfter(LocalDateTime date) {
        return transferenciaRepository.findByFechaTransferenciaAfter(date);
    }

    @Override
    public Transferencia save(Transferencia transferencia) {
        return transferenciaRepository.save(transferencia);
    }
}