	package com.testepedidos.controles;
	
	import java.sql.Timestamp;
	import java.time.LocalDateTime;
	import java.util.List;
	import java.util.Optional;
	import java.util.UUID;
	import java.util.stream.Collectors;
	
	import org.slf4j.Logger;
	import org.slf4j.LoggerFactory;
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.http.ResponseEntity;
	import org.springframework.validation.annotation.Validated;
	import org.springframework.web.bind.annotation.GetMapping;
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
		@GetMapping("/{id}")
		public ResponseEntity<PedidosDTO> consultarPedidoPorId(@PathVariable UUID id) {
		    logger.info("Requisição para consultar o pedido com o ID: {} recebida", id);
	
		    Optional<Pedido> pedidoOptional = repositorioPedidos.findById(id);
		    
		    if (pedidoOptional.isPresent()) {
		        Pedido pedido = pedidoOptional.get();
		        PedidosDTO dtoPedido = new PedidosDTO(pedido);
		        logger.info("Pedido encontrado: {}", dtoPedido);
		        return ResponseEntity.ok(dtoPedido);
		    } else {
		        logger.warn("Pedido com o ID {} não encontrado", id);
		        return ResponseEntity.notFound().build();
		    }
		}
		@GetMapping
		public ResponseEntity<List<PedidosDTO>> consultarPedidosPorStatus(@RequestParam(name = "status", required = false) String status) {
		    logger.info("Requisição para listar pedidos com filtro por status recebida");
	
		    List<Pedido> pedidos;
		    if (status != null) {
		        logger.info("Filtrando pedidos pelo status: {}", status);
		        pedidos = repositorioPedidos.findByStatus(status);
		    } else {
		        logger.info("Listando todos os pedidos, sem filtro de status");
		        pedidos = repositorioPedidos.findAll();
		    }
	
		    List<PedidosDTO> dtoPedidos = pedidos.stream().map(PedidosDTO::new).collect(Collectors.toList());
		    logger.info("Total de pedidos encontrados: {}", dtoPedidos.size());
	
		    return ResponseEntity.ok(dtoPedidos);
		}
		
	}
