package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEstado(Boolean estado);

    Cliente findByNumeroDocumento(String numeroDocumento);

    Cliente findByEmail(String email);

    List<Cliente> findByNombresContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCase(String nombres, String apellidoPaterno);

    boolean existsByNumeroDocumento(String numeroDocumento);

    boolean existsByEmail(String email);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reserva r " +
            "WHERE r.cliente.idCliente = :idCliente " +
            "AND r.estado = true " +
            "AND r.fechaReserva >= CURRENT_DATE")
    boolean hasReservasActivas(@Param("idCliente") Integer idCliente);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END " +
            "FROM Pedido p " +
            "WHERE p.cliente.idCliente = :idCliente " +
            "AND p.estadoPedido.idEstadoPedido IN (1, 2, 3)")
    boolean hasPedidosPendientes(@Param("idCliente") Integer idCliente);

    @Query("SELECT DISTINCT c FROM Cliente c " +
            "INNER JOIN Reserva r ON r.cliente.idCliente = c.idCliente " +
            "WHERE c.estado = true")
    List<Cliente> findClientesConReservas();

    @Query("SELECT DISTINCT c FROM Cliente c " +
            "INNER JOIN Pedido p ON p.cliente.idCliente = c.idCliente " +
            "WHERE c.estado = true")
    List<Cliente> findClientesConPedidos();
}
