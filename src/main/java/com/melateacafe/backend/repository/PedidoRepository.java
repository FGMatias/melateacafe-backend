package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByEstadoPedido_IdEstadoPedido(Integer idEstadoPedido);

    List<Pedido> findByTipoPedido_IdTipoPedido(Integer idTipoPedido);

    List<Pedido> findByCliente_IdCliente(Integer idCliente);

    List<Pedido> findByMesa_IdMesa(Integer idMesa);

    @Query("SELECT CASE WHEN COUNT(v) > 0 THEN true ELSE false END " +
            "FROM Venta v " +
            "WHERE v.pedido.idPedido = :idPedido")
    boolean hasVenta(@Param("idPedido") Integer idPedido);

    @Query("SELECT p FROM Pedido p " +
            "WHERE p.estadoPedido.idEstadoPedido = 1 " +
            "ORDER BY p.fechaCreacion ASC")
    List<Pedido> findPedidosPendientes();

    @Query("SELECT p FROM Pedido p " +
            "WHERE p.estadoPedido.idEstadoPedido IN (2, 3) " +
            "ORDER BY p.fechaCreacion ASC")
    List<Pedido> findPedidosEnPreparacion();

    @Query("SELECT p FROM Pedido p " +
            "WHERE p.mesa.idMesa = :idMesa " +
            "AND p.estadoPedido.idEstadoPedido NOT IN (6, 7, 8) " +
            "ORDER BY p.fechaCreacion DESC")
    List<Pedido> findPedidosActivosByMesa(@Param("idMesa") Integer idMesa);
}
