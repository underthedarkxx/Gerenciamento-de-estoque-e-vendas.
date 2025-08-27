package com.sdias.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.sdias.dto.RelatorioVendasClienteDTO;
import com.sdias.model.Cliente;
import com.sdias.model.Pedido;
import com.sdias.model.Produto;
import com.sdias.repository.ClienteRepository;
import com.sdias.repository.PedidoRepository;
import com.sdias.repository.ProdutoRepository;

@Service
public class RelatorioService {

    private final ProdutoRepository produtoRepository;
    private final PedidoRepository pedidoRepository;
    private final ClienteRepository clienteRepository;

    @Autowired
    public RelatorioService(
        ProdutoRepository produtoRepository, 
        PedidoRepository pedidoRepository, 
        ClienteRepository clienteRepository
    ) {
        this.produtoRepository = produtoRepository;
        this.pedidoRepository = pedidoRepository;
        this.clienteRepository = clienteRepository;
    }

    /**
     * Busca produtos cuja quantidade em estoque é menor ou igual ao limite informado.
     * @param limite A quantidade máxima para considerar o estoque como baixo.
     * @return Uma lista de produtos com estoque baixo.
     */
    public List<Produto> findEstoqueBaixo(Integer limite) {
        return produtoRepository.findByQuantidadeLessThanEqual(limite);
    }

    /**
     * Gera um relatório de vendas consolidado para um cliente específico.
     * @param clienteId O ID do cliente para o qual o relatório será gerado.
     * @return Um DTO com os dados do relatório.
     */
    public RelatorioVendasClienteDTO findVendasByCliente(Long clienteId) {
        // 1. Verifica se o cliente existe
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, 
                        "Cliente com ID " + clienteId + " não encontrado."));

        // 2. Busca todos os pedidos do cliente
        List<Pedido> pedidos = pedidoRepository.findByCliente(cliente);

        // 3. Calcula o valor total vendido somando o total de cada pedido
        //    (Esta lógica assume que sua entidade Pedido tem um método getValorTotal())
        BigDecimal valorTotal = pedidos.stream()
                .map(Pedido::getValorTotal) // Supondo que Pedido.getValorTotal() retorna BigDecimal
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // 4. Monta e retorna o DTO com os resultados
        return new RelatorioVendasClienteDTO(
            cliente.getId(),
            cliente.getNome(),
            pedidos.size(),
            valorTotal
        );
    }
}
