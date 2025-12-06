package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.TipoPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoPedidoRepository extends JpaRepository<TipoPedido, Integer> {
}
