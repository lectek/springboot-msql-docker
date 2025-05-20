package com.embalando.config;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    /**
     * Inicia o servidor TCP do H2 na porta 9092 e permite conexões externas.
     */
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2TcpServer() throws SQLException {
        return Server.createTcpServer(
            "-tcp",
            "-tcpAllowOthers",
            "-tcpPort", "9092"
        );
    }
}
