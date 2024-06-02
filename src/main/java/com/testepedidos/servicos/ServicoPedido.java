package com.testepedidos.servicos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery;
import org.springframework.stereotype.Service;

import com.testepedidos.dados.RepositorioPedidos;
import com.testepedidos.dto.PedidosDTO;
import com.testepedidos.modelos.Pedido;


@Service
public class ServicoPedido {
	@Autowired
	private RepositorioPedidos repositorioPedidos;
	
	private static final Logger logger = LoggerFactory.getLogger(ServicoPedido.class);


	public ServicoPedido(RepositorioPedidos repositorioPedidos) {
		this.repositorioPedidos = repositorioPedidos;
	}
	
	public PedidosDTO criarPedido(PedidosDTO pedido) {
		Pedido pedidoEntidade = new Pedido(pedido); // Configura UUID, status e data de criação no Pedido
		pedidoEntidade.setStatus("CRIADO");
		pedidoEntidade.setDataCriacao(new Timestamp(System.currentTimeMillis()));
		Pedido pedidoSalvo = repositorioPedidos.save(pedidoEntidade);
		return new PedidosDTO(pedidoSalvo);
	}
	public PedidosDTO atualizarPedido(UUID id, PedidosDTO pedidoDTO) {
        Optional<Pedido> optionalPedido = repositorioPedidos.findById(id);

        if (!optionalPedido.isPresent()) {
            // Lançar exceção ou retornar um valor indicando que o pedido não foi encontrado
            System.out.println("Pedido não encontrado com o id: " + id);
        }

        Pedido pedidoExistente = optionalPedido.get();
        
        // Atualizar os campos do pedidoExistente com os valores do pedidoDTO
        if (pedidoDTO.getDescricao() != null) {
            pedidoExistente.setDescricao(pedidoDTO.getDescricao());
        }
        if (pedidoDTO.getStatus() != null) {
            pedidoExistente.setStatus(pedidoDTO.getStatus());
        }
        if (pedidoDTO.getDataCriacao() != null) {
            pedidoExistente.setDataCriacao(pedidoDTO.getDataCriacao());
        }

        Pedido atualizadoPedido = repositorioPedidos.save(pedidoExistente);
        return new PedidosDTO(atualizadoPedido);
    }
	

	public List<PedidosDTO> consultarPedidos(){
		logger.info("Método consultarPedidos() chamado.");
		
		List<Pedido> pedido =repositorioPedidos.findAll();
		logger.info("Total de usuários encontrados: {}", pedido.size());
		
		List<PedidosDTO> dtoPedidos = pedido.stream().map(PedidosDTO::new).collect(Collectors.toList());
		
		logger.info("Total de RequestUsuario mapeados: {}", dtoPedidos.size());
		
		return dtoPedidos;

		
		
	}
	
}
