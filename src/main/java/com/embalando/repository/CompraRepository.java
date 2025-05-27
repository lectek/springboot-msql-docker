package com.embalando.repository;

import com.embalando.model.Compra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    // Pode criar buscas como:
    // List<Compra> findByClienteId(Long clienteId);
}
