package com.embalando.service;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j // Lombok: para usar log sem precisar instanciar manualmente
@Component // Permite que o Spring gerencie esse serviço automaticamente
public class InicializadorServico {

    @PostConstruct // Executa automaticamente quando a aplicação Spring sobe
    public void inicializar() {
        log.info("🔧 Inicializando serviços da loja Embalando...");
        
        // Exemplo de ações futuras:
        // carregarEstoque();
        // verificarConexaoBanco();
        // carregarCacheProdutos();

        log.info("✅ Serviços prontos para uso.");
    }

    // Aqui você pode adicionar métodos específicos futuramente, como:
    /*
    private void carregarEstoque() {
        log.info("📦 Estoque carregado com sucesso.");
    }

    private void verificarConexaoBanco() {
        log.info("🔌 Banco de dados conectado com sucesso.");
    }
    */
}
