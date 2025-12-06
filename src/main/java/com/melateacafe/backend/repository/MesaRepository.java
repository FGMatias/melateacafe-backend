package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    List<Mesa> findByEstado(Boolean estado);

    List<Mesa> findByEstadoMesa_IdEstadoMesa(Integer idEstadoMesa);

    List<Mesa> findByCapacidad(Integer capacidad);

    List<Mesa> findByEstadoMesa_IdEstadoMesaAndEstado(Integer idEstadoMesa, Boolean estado);

    boolean existsByNumero(String numero);

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END " +
            "FROM Reserva r " +
            "WHERE r.mesa.idMesa = :idMesa " +
            "AND r.estado = true " +
            "AND r.fechaReserva >= CURRENT_DATE")
    boolean hasReservasActivas(@Param("idMesa") Integer idMesa);
}
