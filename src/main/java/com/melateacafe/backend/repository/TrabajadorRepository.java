package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Trabajador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrabajadorRepository extends JpaRepository<Trabajador, Integer> {
    List<Trabajador> findByCargo_IdCargo(int idCargo);
    boolean existsByNumeroDocumento(String numeroDocumento);
    boolean existsByEmail(String email);
    List<Trabajador> findByEstado(boolean estado);
}
