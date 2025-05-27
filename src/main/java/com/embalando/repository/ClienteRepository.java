package com.embalando.repository;

import com.embalando.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // Aqui você pode adicionar buscas personalizadas, ex:
    Cliente findByNome(String nome);
}
