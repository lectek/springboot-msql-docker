package com.embalando.model;

import jakarta.persistence.*;
import lombok.*;

/**
 * Entidade que representa um Produto dentro de uma Compra.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private int quantidade;

    @Column(name = "preco_unitario")
    private double precoUnitario;

    @ManyToOne
    @JoinColumn(name = "compra_id", nullable = false)
    private Compra compra;

    /**
     * Calcula o valor total do produto (quantidade x preço unitário).
     */
    public double calcularTotal() {
        return quantidade * precoUnitario;
    }
}
