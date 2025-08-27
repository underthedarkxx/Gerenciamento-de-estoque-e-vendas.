package com.sdias.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.ClienteDTO;
import com.sdias.dto.HistoricoCompraClienteDTO;
import com.sdias.model.Cliente;
import com.sdias.repository.ClienteRepository;
import com.sdias.repository.PedidoRepository;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PedidoRepository pedidoRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, PedidoRepository pedidoRepository){
        this.clienteRepository = clienteRepository;
        this.pedidoRepository = pedidoRepository;
    }

    // SALVAR CLIENTE
    public Cliente salvar(Cliente cliente){
        if(cliente.getTelefone() != null && !cliente.getTelefone().matches("^\\d{10,11}$")) {
            throw new IllegalArgumentException("Formato de telefone inválido.");
        }
        return clienteRepository.save(cliente);
    }

    // ATUALIZAR CLIENTE
    public Cliente atualizar(Long id, Cliente clienteAtualizado){
        Cliente clienteExistente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setTelefone(clienteAtualizado.getTelefone());

        if(clienteExistente.getTelefone() != null && !clienteExistente.getTelefone().matches("^\\d{10,11}$")) {
            throw new IllegalArgumentException("Formato de telefone inválido.");
        }

        return clienteRepository.save(clienteExistente);
    }

    // BUSCAR POR ID
    public Cliente buscarPorId(Long id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado."));
    }

    // LISTAR TODOS
    public List<ClienteDTO> listarTodosDTO(){
        return clienteRepository.findAll().stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }

    // CONSULTAR POR NOME
    public List<ClienteDTO> consultarPorNomeDTO(String nome){
        List<Cliente> clientes = clienteRepository.findByNomeContainingIgnoreCase(nome);
        if(clientes.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Nenhum cliente encontrado com o nome: " + nome);
        }
        return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    // DELETAR CLIENTE
    public void deletar(Long id){
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));
        clienteRepository.delete(cliente);
    }

    // HISTÓRICO DE COMPRAS
    public List<HistoricoCompraClienteDTO> buscarHistoricoCompras(Long clienteId){
        if(!clienteRepository.existsById(clienteId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        return pedidoRepository.findHistoricoByClienteId(clienteId);
    }
}
