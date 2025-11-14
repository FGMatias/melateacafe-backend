package com.melateacafe.backend.repository;

import com.melateacafe.backend.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByCategoriaProducto_IdCategoriaProducto(Integer idCategoria);

    List<Producto> findByEstadoTrue();

    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    List<Producto> findByPrecioBetween(BigDecimal precioMin, BigDecimal precioMax);

    @Query(
        "SELECT a FROM Producto a " +
        "WHERE a.stockActual <= a.stockMinimo " +
        "AND a.estado = true"
    )
    List<Producto> findProductosConStockBajo();

    @Query(
        "SELECT a FROM Producto a " +
        "WHERE a.estado = true " +
        "ORDER BY a.stockActual DESC"
    )
    List<Producto> findProductosDestacados();

    @Query(
        "SELECT a FROM Producto a " +
        "WHERE (:categoria IS NULL OR a.categoriaProducto.idCategoriaProducto = :categoria) " +
        "AND (:precioMin IS NULL OR a.precio >= :precioMin) " +
        "AND (:precioMax IS NULL OR a.precio <= :precioMax) " +
        "AND (:nombre IS NULL OR LOWER(a.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))) " +
        "AND a.estado = true"
    )
    List<Producto> buscarProductos(
        @Param("categoria") Integer categoria,
        @Param("precioMin") BigDecimal precioMin,
        @Param("precioMax") BigDecimal precioMax,
        @Param("nombre") String nombre
    );

    boolean existsByNombre(String nombre);
}
