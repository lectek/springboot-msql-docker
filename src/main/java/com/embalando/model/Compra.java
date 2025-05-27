package com.embalando.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidade que representa uma Compra realizada por um Cliente.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "compras")
public class Compra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String descricao;

    private double valor;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "compra", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Produto> produtos = new ArrayList<>();

    /**
     * Adiciona um produto à compra e atualiza o valor total.
     */
    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
        produto.setCompra(this);
        calcularValorTotal();
    }

    /**
     * Calcula o valor total com base nos produtos.
     */
    public void calcularValorTotal() {
        this.valor = produtos.stream()
                .mapToDouble(p -> p.getQuantidade() * p.getPrecoUnitario())
                .sum();
    }
}
