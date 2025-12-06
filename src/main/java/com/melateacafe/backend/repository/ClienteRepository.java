package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    List<Cliente> findByEstado(Boolean estado);

    Cliente findByNumeroDocumento(String numeroDocumento);

    Cliente findByEmail(String email);
}
