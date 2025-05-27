package com.embalando.service;

import com.embalando.model.Cliente;
import com.embalando.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    // Cadastra um cliente após validações
    public Cliente salvarCliente(Cliente cliente) {
        validarCliente(cliente);
        return clienteRepository.save(cliente);
    }

    // Retorna todos os clientes cadastrados
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    // Busca um cliente por ID ou lança erro se não existir
    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Cliente com ID " + id + " não encontrado."));
    }

    // Atualiza os dados de um cliente existente
    public Cliente atualizarCliente(Long id, Cliente clienteAtualizado) {
        Cliente existente = buscarPorId(id);
        existente.setNome(clienteAtualizado.getNome());
        existente.setTelefone(clienteAtualizado.getTelefone());
        existente.setEndereco(clienteAtualizado.getEndereco());
        return clienteRepository.save(existente);
    }

    // Deleta um cliente se existir
    public void deletarCliente(Long id) {
        if (!clienteRepository.existsById(id)) {
            throw new EntityNotFoundException("Cliente com ID " + id + " não encontrado.");
        }
        clienteRepository.deleteById(id);
    }

    // Valida os campos obrigatórios do cliente
    private void validarCliente(Cliente cliente) {
        if (cliente.getNome() == null || cliente.getNome().isBlank()) {
            throw new IllegalArgumentException("Nome é obrigatório.");
        }
        if (cliente.getTelefone() == null || cliente.getTelefone().isBlank()) {
            throw new IllegalArgumentException("Telefone é obrigatório.");
        }
        if (cliente.getEndereco() == null || cliente.getEndereco().isBlank()) {
            throw new IllegalArgumentException("Endereço é obrigatório.");
        }
    }
}
