package com.testepedidos.modelos;

import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

import org.hibernate.annotations.Type;

import org.hibernate.annotations.GenericGenerator;

import com.testepedidos.dto.PedidosDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;


@Table(name = "Pedido")
@Entity(name = "Pedido")
@EqualsAndHashCode(of = "id")
public class Pedido {
	
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
	@Column
	private String descricao;

	@Column
	private String status;

	@Column
	private Timestamp dataCriacao;

	public Pedido() {
		//this.id = UUID.randomUUID();
		//this.dataCriacao = new Timestamp(System.currentTimeMillis());
	}

	public Pedido(UUID id, String descricao, String status, Timestamp dataCriacao) {
		this.id = id;
		this.descricao = descricao;
		this.status = status;
		this.dataCriacao = dataCriacao;
	}

	public Pedido(PedidosDTO pedido) {
		this.id = pedido.getId() != null ? pedido.getId() : UUID.randomUUID();
		this.setDataCriacao(pedido.getDataCriacao());
		this.setDescricao(pedido.getDescricao());
		this.setStatus(pedido.getStatus());
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

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	
}
