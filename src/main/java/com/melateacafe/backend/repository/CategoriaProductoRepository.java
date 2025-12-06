package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.CategoriaProducto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaProductoRepository extends JpaRepository<CategoriaProducto, Integer> {
    boolean existsByNombre(String nombre);

    List<CategoriaProducto> findByEstado(Boolean estado);

    @Query("SELECT CASE WHEN COUNT(p) > 0 THEN true ELSE false END FROM Producto p WHERE p.categoriaProducto.idCategoriaProducto = :idCategoria")
    boolean hasProductos(@Param("idCategoria") Integer idCategoria);
}

