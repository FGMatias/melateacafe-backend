package com.melateacafe.backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "orden_cocina")
public class OrdenCocina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_orden_cocina")
    private int idOrdenCocina;

    @ManyToOne
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado_orden_cocina")
    private EstadoOrdenCocina estadoOrdenCocina;

    @ManyToOne
    @JoinColumn(name = "id_cocinero", referencedColumnName = "id_trabajador")
    private Trabajador trabajador;

    @ManyToOne
    @JoinColumn(name = "id_detalle_pedido", referencedColumnName = "id_detalle_pedido")
    private DetallePedido detallePedido;

    @Column(name = "observaciones")
    private String observaciones;

    @Column(name = "hora_inicio")
    private LocalTime horaInicio;

    @Column(name = "hora_fin")
    private LocalTime horaFin;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public int getIdOrdenCocina() {
        return idOrdenCocina;
    }

    public void setIdOrdenCocina(int idOrdenCocina) {
        this.idOrdenCocina = idOrdenCocina;
    }

    public EstadoOrdenCocina getEstadoOrdenCocina() {
        return estadoOrdenCocina;
    }

    public void setEstadoOrdenCocina(EstadoOrdenCocina estadoOrdenCocina) {
        this.estadoOrdenCocina = estadoOrdenCocina;
    }

    public Trabajador getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(Trabajador trabajador) {
        this.trabajador = trabajador;
    }

    public DetallePedido getDetallePedido() {
        return detallePedido;
    }

    public void setDetallePedido(DetallePedido detallePedido) {
        this.detallePedido = detallePedido;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalTime horaFin) {
        this.horaFin = horaFin;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
