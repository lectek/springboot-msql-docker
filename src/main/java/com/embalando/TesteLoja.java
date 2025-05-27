package com.embalando;

import com.embalando.model.Cliente;
import com.embalando.model.Compra;
import com.embalando.model.Produto;

import java.time.LocalDate;

public class TesteLoja {

    public static void main(String[] args) {

        // Criando um cliente usando builder (se sua classe tiver @Builder)
        Cliente cliente = Cliente.builder()
                .nome("Alex Morais")
                .telefone("(83) 99999-9999")
                .email("alex@email.com") // necessário se houver @Email e @NotBlank
                .build();
Compra compra = Compra.builder()
    .descricao("Compra de teste")
    .data(LocalDate.now())
    .cliente(cliente)
    .build();

Produto p1 = Produto.builder()
    .nome("Caixa de papelão")
    .quantidade(5)
    .precoUnitario(2.5)
    .compra(compra)
    .build();

        // Adicionar produtos à compra
        compra.adicionarProduto(p1);
        compra.adicionarProduto(p1);

        // Imprimir resultados
        System.out.println("Cliente: " + cliente.getNome());
        System.out.println("Descrição da compra: " + compra.getDescricao());
        System.out.println("Produtos:");

        for (Produto p : compra.getProdutos()) {
            System.out.printf("- %s x%d = R$ %.2f%n", p.getNome(), p.getQuantidade(), p.calcularTotal());
        }

        System.out.printf("Valor total calculado: R$ %.2f%n", compra.getValor());
    }
}
