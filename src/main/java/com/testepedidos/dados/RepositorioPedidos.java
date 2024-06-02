package com.testepedidos.dados;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.testepedidos.modelos.Pedido;

@Repository
public interface RepositorioPedidos extends JpaRepository<Pedido, UUID>{
    List<Pedido> findByStatus(String status);


}
