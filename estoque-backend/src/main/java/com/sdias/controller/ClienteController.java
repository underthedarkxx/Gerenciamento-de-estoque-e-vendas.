package com.sdias.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.ClienteDTO;
import com.sdias.dto.HistoricoCompraClienteDTO;
import com.sdias.model.Cliente;
import com.sdias.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private final ClienteService service;

    @Autowired
    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDTO adicionar(@RequestBody Cliente novoCliente){
        return new ClienteDTO(service.salvar(novoCliente));
    }

    @GetMapping
    public List<ClienteDTO> listarTodos(){
        return service.listarTodosDTO();
    }

    @GetMapping("/buscar")
    public List<ClienteDTO> buscarPorNome(@RequestParam String nome){
        return service.consultarPorNomeDTO(nome);
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable Long id){
        return new ClienteDTO(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable Long id, @RequestBody Cliente cliente){
        return new ClienteDTO(service.atualizar(id, cliente));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }

    @GetMapping("/{id}/historico-compras")
    public List<HistoricoCompraClienteDTO> buscarHistoricoCompras(@PathVariable Long id){
        return service.buscarHistoricoCompras(id);
    }
}


