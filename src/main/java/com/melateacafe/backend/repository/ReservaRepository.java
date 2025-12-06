package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Mesa;
import com.melateacafe.backend.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservaRepository extends JpaRepository<Reserva, Integer> {
    List<Reserva> findByEstado(Boolean estado);

    List<Reserva> findByFechaReserva(LocalDate fechaReserva);

    List<Reserva> findByMesa_IdMesa(Integer idMesa);

    List<Reserva> findByCliente_IdCliente(Integer idCliente);

    List<Reserva> findByEstadoAndFechaReservaGreaterThanEqual(Boolean estado, LocalDate fecha);

    boolean existsByMesaAndFechaReservaAndEstado(Mesa mesa, LocalDate fechaReserva, Boolean estado);

    boolean existsByMesaAndFechaReservaAndEstadoAndIdReservaNot(
            Mesa mesa,
            LocalDate fechaReserva,
            Boolean estado,
            Integer idReserva
    );
}
