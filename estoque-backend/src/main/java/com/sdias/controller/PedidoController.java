package com.sdias.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.sdias.dto.AtualizarStatusPedidoDTO;
import com.sdias.dto.ItemPedidoDTO;
import com.sdias.dto.PedidoCreateDTO;
import com.sdias.dto.PedidoDTO;
import com.sdias.model.Pedido;
import com.sdias.service.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService service;

    @Autowired
    public PedidoController(PedidoService service){
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO criarPedido(@RequestBody PedidoCreateDTO pedidoDTO){
        Pedido pedido = service.criar(pedidoDTO);
        return new PedidoDTO(pedido);
    }


    @GetMapping
    public List<PedidoDTO> listarTodos(){
        return service.listarTodos()
                    .stream()
                    .map(PedidoDTO::new)
                    .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable Long id){
        Pedido pedido = service.buscarPorId(id);
        return new PedidoDTO(pedido);
    }


    @PostMapping("/{id}/itens")
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO adicionarItem(@PathVariable Long id, @RequestBody ItemPedidoDTO itemDTO) {
        Pedido pedido = service.adicionarItem(id, itemDTO);
        return new PedidoDTO(pedido);
    }

    @PatchMapping("/{id}/status")
    public void atualizarStatus(@PathVariable Long id, @RequestBody AtualizarStatusPedidoDTO dto){
        service.atualizarStatus(id, dto.getStatus());
    }
}
