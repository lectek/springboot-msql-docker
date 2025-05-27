package com.embalando.controller;

import com.embalando.model.Compra;
import com.embalando.service.CompraService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// DTO simples para receber dados no corpo do POST
class CompraRequest {
    public Long clienteId;
    public List<Long> produtoIds;
    public String descricao;
}

@RestController
@RequestMapping("/compras")
public class CompraController {

    @Autowired
    private CompraService compraService;

    /**
     * Cria uma nova compra vinculada a um cliente e lista de produtos.
     */
    @PostMapping
    public ResponseEntity<?> criarCompra(@RequestBody CompraRequest request) {
        try {
            Compra compra = compraService.registrarCompra(
                request.clienteId, request.produtoIds, request.descricao
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(compra);
        } catch (EntityNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    /**
     * Lista todas as compras registradas.
     */
    @GetMapping
    public ResponseEntity<List<Compra>> listarTodas() {
        return ResponseEntity.ok(compraService.listarTodas());
    }

    /**
     * Busca uma compra pelo ID.
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable Long id) {
        try {
            Compra compra = compraService.buscarPorId(id);
            return ResponseEntity.ok(compra);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
