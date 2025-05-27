package com.embalando.repository;

import com.embalando.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Pode adicionar depois: List<Produto> findByNomeContaining(String nome);
}
