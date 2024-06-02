package com.testepedidos.controles;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testepedidos.dados.RepositorioPedidos;
import com.testepedidos.dto.PedidosDTO;
import com.testepedidos.modelos.Pedido;
import com.testepedidos.servicos.ServicoPedido;

@RestController
@RequestMapping("/pedido")
public class ControlePedidos {

	private static final Logger logger = LoggerFactory.getLogger(ControlePedidos.class);

	@Autowired
	private final ServicoPedido servicoPedido;

	@Autowired
	private RepositorioPedidos repositorioPedidos;

	@Autowired
	public ControlePedidos(ServicoPedido servicoPedido) {
		this.servicoPedido = servicoPedido;
	}

	@PostMapping
	public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido) {
		pedido.setStatus("CRIADO");
		pedido.setDataCriacao(new Timestamp(System.currentTimeMillis()));
		Pedido novoPedido = repositorioPedidos.save(pedido);
		return ResponseEntity.ok(novoPedido);
	}

	@PatchMapping("/patch/{id}")
    public PedidosDTO atualizarPedido(@PathVariable UUID id, @RequestBody PedidosDTO pedidoDTO) {
        logger.info("Requisição para alterar pedido recebida: {}", pedidoDTO);
        return servicoPedido.atualizarPedido(id, pedidoDTO);
    }
}
