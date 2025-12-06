package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedido_IdPedido(Integer idPedido);

    List<DetallePedido> findByProducto_IdProducto(Integer idProducto);
}
