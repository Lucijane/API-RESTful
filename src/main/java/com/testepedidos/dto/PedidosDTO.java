package com.testepedidos.dto;

import java.sql.Timestamp;

import java.util.UUID;

import com.testepedidos.modelos.Pedido;

public class PedidosDTO {
	private UUID id;
	private String descricao;
	private String status;
	private Timestamp dataCriacao;

	public PedidosDTO() {
		
	}

	public PedidosDTO(Pedido pedido) {
		this.id = pedido.getId();
		this.descricao = pedido.getDescricao();
		this.status = pedido.getStatus();
		this.dataCriacao = pedido.getDataCriacao();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(Timestamp dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

}
