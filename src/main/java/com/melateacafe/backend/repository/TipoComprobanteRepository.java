package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.TipoComprobante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoComprobanteRepository extends JpaRepository<TipoComprobante, Integer> {
}
