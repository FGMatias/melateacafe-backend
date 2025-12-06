package com.melateacafe.backend.dto.response.pedido;

import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PedidoResponseDTO {
    private Integer idPedido;
    private MesaResponseDTO mesa;
    private ClienteResponseDTO cliente;
    private EstadoPedidoResponseDTO estado;
    private TipoPedidoResponseDTO tipoPedido;
    private BigDecimal subtotal;
    private BigDecimal deliveryCosto;
    private BigDecimal total;
    private String direccionEntrega;
    private String observaciones;
    private LocalDateTime fechaCreacion;
    private List<DetallePedidoResponseDTO> detalles;

    public PedidoResponseDTO() {}

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public MesaResponseDTO getMesa() { return mesa; }
    public void setMesa(MesaResponseDTO mesa) { this.mesa = mesa; }

    public ClienteResponseDTO getCliente() { return cliente; }
    public void setCliente(ClienteResponseDTO cliente) { this.cliente = cliente; }

    public EstadoPedidoResponseDTO getEstado() { return estado; }
    public void setEstado(EstadoPedidoResponseDTO estado) { this.estado = estado; }

    public TipoPedidoResponseDTO getTipoPedido() { return tipoPedido; }
    public void setTipoPedido(TipoPedidoResponseDTO tipoPedido) { this.tipoPedido = tipoPedido; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getDeliveryCosto() { return deliveryCosto; }
    public void setDeliveryCosto(BigDecimal deliveryCosto) { this.deliveryCosto = deliveryCosto; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) { this.direccionEntrega = direccionEntrega; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public List<DetallePedidoResponseDTO> getDetalles() { return detalles; }
    public void setDetalles(List<DetallePedidoResponseDTO> detalles) { this.detalles = detalles; }
}