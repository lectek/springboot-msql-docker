package com.embalando.service;

import com.embalando.model.Produto;
import com.embalando.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    // Cria um novo produto com validação
    public Produto salvarProduto(Produto produto) {
        validarProduto(produto);
        return produtoRepository.save(produto);
    }

    // Lista todos os produtos disponíveis
    public List<Produto> listarTodos() {
        return produtoRepository.findAll();
    }

    // Busca produto por ID (ou lança erro)
    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Produto com ID " + id + " não encontrado."));
    }

    // Atualiza os dados de um produto existente
    public Produto atualizarProduto(Long id, Produto atualizado) {
        Produto existente = buscarPorId(id);
        existente.setNome(atualizado.getNome());
        existente.setPrecoUnitario(atualizado.getPrecoUnitario());
        existente.setQuantidade(atualizado.getQuantidade());
        return produtoRepository.save(existente);
    }

    // Deleta um produto
    public void deletarProduto(Long id) {
        if (!produtoRepository.existsById(id)) {
            throw new EntityNotFoundException("Produto com ID " + id + " não encontrado.");
        }
        produtoRepository.deleteById(id);
    }

    // Valida os campos obrigatórios
    private void validarProduto(Produto produto) {
        if (produto.getNome() == null || produto.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome do produto é obrigatório.");
        }
        if (produto.getPrecoUnitario() <= 0) {
            throw new IllegalArgumentException("Preço deve ser maior que zero.");
        }
        if (produto.getQuantidade() < 0) {
            throw new IllegalArgumentException("Quantidade não pode ser negativa.");
        }
    }
}
