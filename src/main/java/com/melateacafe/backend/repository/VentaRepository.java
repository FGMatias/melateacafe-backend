package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {
    List<Venta> findByFecha(LocalDate fecha);

    List<Venta> findByFechaBetween(LocalDate inicio, LocalDate fin);

    List<Venta> findByMetodoPago_IdMetodoPago(Integer idMetodoPago);

    List<Venta> findByTipoComprobante_IdTipoComprobante(Integer idTipoComprobante);

    Venta findByNumeroComprobante(String numeroComprobante);

    boolean existsByPedido_IdPedido(Integer idPedido);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fecha = :fecha")
    BigDecimal sumTotalByFecha(@Param("fecha") LocalDate fecha);

    @Query("SELECT SUM(v.total) FROM Venta v WHERE v.fecha BETWEEN :inicio AND :fin")
    BigDecimal sumTotalByFechaRange(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query("SELECT v FROM Venta v WHERE v.fecha = CURRENT_DATE ORDER BY v.hora DESC")
    List<Venta> findVentasDelDia();

    @Query("SELECT v FROM Venta v WHERE v.usuario.idUsuario = :idUsuario ORDER BY v.fecha DESC, v.hora DESC")
    List<Venta> findByUsuario(@Param("idUsuario") Integer idUsuario);

    @Query("SELECT COUNT(v) FROM Venta v WHERE v.fecha = :fecha")
    Long countByFecha(@Param("fecha") LocalDate fecha);
}
