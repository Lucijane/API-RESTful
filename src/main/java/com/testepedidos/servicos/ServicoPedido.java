package com.testepedidos.servicos;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.function.Function;

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
}
