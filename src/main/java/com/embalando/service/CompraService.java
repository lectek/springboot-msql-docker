package com.embalando.service;

import com.embalando.model.Compra;
import com.embalando.model.Produto;
import com.embalando.model.Cliente;
import com.embalando.repository.CompraRepository;
import com.embalando.repository.ProdutoRepository;
import com.embalando.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * Serviço responsável pela lógica de negócios envolvendo compras.
 */
@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Registra uma nova compra com os produtos escolhidos e vincula ao cliente.
     *
     * @param clienteId ID do cliente que está realizando a compra
     * @param produtoIds Lista de IDs dos produtos comprados
     * @param descricao Descrição da compra (ex: "Compra de 3 produtos")
     * @return A compra registrada com produtos associados
     */
    public Compra registrarCompra(Long clienteId, List<Long> produtoIds, String descricao) {
        Cliente cliente = clienteRepository.findById(clienteId)
            .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + clienteId + " não encontrado."));

        List<Produto> produtos = produtoRepository.findAllById(produtoIds);

        if (produtos.isEmpty()) {
            throw new IllegalArgumentException("Lista de produtos está vazia ou contém IDs inválidos.");
        }

     Compra compra = Compra.builder()
    .descricao(descricao)
    .data(LocalDate.now())
    .cliente(cliente)
    .build();


        for (Produto produto : produtos) {
            compra.adicionarProduto(produto);
        }

        // O valor total é calculado internamente ao adicionar os produtos
        return compraRepository.save(compra);
    }

    /**
     * Retorna todas as compras registradas no sistema.
     */
    public List<Compra> listarTodas() {
        return compraRepository.findAll();
    }

    /**
     * Busca uma compra específica pelo ID.
     *
     * @param id ID da compra
     * @return Objeto Compra encontrado ou exceção se não existir
     */
    public Compra buscarPorId(Long id) {
        return compraRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Compra com ID " + id + " não encontrada."));
    }
}
