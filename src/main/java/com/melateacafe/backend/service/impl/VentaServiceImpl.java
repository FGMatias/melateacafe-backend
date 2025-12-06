package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.venta.CreateVentaRequestDTO;
import com.melateacafe.backend.dto.response.venta.MetodoPagoResponseDTO;
import com.melateacafe.backend.dto.response.venta.TipoComprobanteResponseDTO;
import com.melateacafe.backend.dto.response.venta.VentaResponseDTO;
import com.melateacafe.backend.entity.*;
import com.melateacafe.backend.repository.*;
import com.melateacafe.backend.service.VentaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    private static final BigDecimal IGV_RATE = new BigDecimal("0.18"); // 18% IGV

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findAll() {
        return ventaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponseDTO findById(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " + id));
        return convertToResponse(venta);
    }

    @Override
    @Transactional
    public VentaResponseDTO create(CreateVentaRequestDTO request) {
        MetodoPago metodoPago = metodoPagoRepository.findById(request.getIdMetodoPago())
                .orElseThrow(() -> new EntityNotFoundException("Método de pago no encontrado con id: " + request.getIdMetodoPago()));

        TipoComprobante tipoComprobante = tipoComprobanteRepository.findById(request.getIdTipoComprobante())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de comprobante no encontrado con id: " + request.getIdTipoComprobante()));

        Pedido pedido = pedidoRepository.findById(request.getIdPedido())
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + request.getIdPedido()));

        Usuario usuario = usuarioRepository.findById(request.getIdUsuario())
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con id: " + request.getIdUsuario()));

        if (ventaRepository.existsByPedido_IdPedido(request.getIdPedido())) {
            throw new IllegalStateException("El pedido ya tiene una venta asociada");
        }

        Integer estadoPedido = pedido.getEstadoPedido().getIdEstadoPedido();
        if (estadoPedido == 7 || estadoPedido == 8) {
            throw new IllegalStateException("No se puede crear venta para un pedido cancelado o rechazado");
        }

        String numeroComprobante = generarNumeroComprobante(tipoComprobante);

        BigDecimal subtotal = pedido.getTotal().divide(BigDecimal.ONE.add(IGV_RATE), 2, RoundingMode.HALF_UP);
        BigDecimal igv = pedido.getTotal().subtract(subtotal);

        Venta venta = new Venta();
        venta.setMetodoPago(metodoPago);
        venta.setTipoComprobante(tipoComprobante);
        venta.setPedido(pedido);
        venta.setUsuario(usuario);
        venta.setNumeroComprobante(numeroComprobante);
        venta.setSubtotal(subtotal);
        venta.setIgv(igv);
        venta.setTotal(pedido.getTotal());
        venta.setFecha(LocalDate.now());
        venta.setHora(LocalTime.now());

        Venta saved = ventaRepository.save(venta);

        tipoComprobante.setCorrelativo(tipoComprobante.getCorrelativo() + 1);
        tipoComprobanteRepository.save(tipoComprobante);

        EstadoPedido estadoEntregado = estadoPedidoRepository.findById(6)
                .orElseThrow(() -> new EntityNotFoundException("Estado 'Entregado' no encontrado"));
        pedido.setEstadoPedido(estadoEntregado);
        pedidoRepository.save(pedido);

        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venta no encontrada con id: " + id));

        if (!venta.getFecha().equals(LocalDate.now())) {
            throw new IllegalStateException("Solo se pueden eliminar ventas del día actual");
        }

        ventaRepository.delete(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findByFecha(LocalDate fecha) {
        return ventaRepository.findByFecha(fecha).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findByFechaRange(LocalDate inicio, LocalDate fin) {
        return ventaRepository.findByFechaBetween(inicio, fin).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findByMetodoPago(Integer idMetodoPago) {
        return ventaRepository.findByMetodoPago_IdMetodoPago(idMetodoPago).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<VentaResponseDTO> findByTipoComprobante(Integer idTipoComprobante) {
        return ventaRepository.findByTipoComprobante_IdTipoComprobante(idTipoComprobante).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public VentaResponseDTO findByNumeroComprobante(String numeroComprobante) {
        Venta venta = ventaRepository.findByNumeroComprobante(numeroComprobante);
        if (venta == null) {
            throw new EntityNotFoundException("Venta no encontrada con número de comprobante: " + numeroComprobante);
        }
        return convertToResponse(venta);
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalVentasByFecha(LocalDate fecha) {
        BigDecimal total = ventaRepository.sumTotalByFecha(fecha);
        return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    @Transactional(readOnly = true)
    public BigDecimal getTotalVentasByFechaRange(LocalDate inicio, LocalDate fin) {
        BigDecimal total = ventaRepository.sumTotalByFechaRange(inicio, fin);
        return total != null ? total : BigDecimal.ZERO;
    }

    private String generarNumeroComprobante(TipoComprobante tipoComprobante) {
        String serie = tipoComprobante.getSerie();
        Integer correlativo = tipoComprobante.getCorrelativo() + 1;
        String correlativoFormateado = String.format("%08d", correlativo);
        return serie + "-" + correlativoFormateado;
    }

    private VentaResponseDTO convertToResponse(Venta venta) {
        VentaResponseDTO response = new VentaResponseDTO();
        response.setIdVenta(venta.getIdVenta());
        response.setMetodoPago(convertMetodoPagoToResponse(venta.getMetodoPago()));
        response.setTipoComprobante(convertTipoComprobanteToResponse(venta.getTipoComprobante()));
        response.setIdPedido(venta.getPedido().getIdPedido());
        response.setIdUsuario(venta.getUsuario().getIdUsuario());
        response.setNumeroComprobante(venta.getNumeroComprobante());
        response.setSubtotal(venta.getSubtotal());
        response.setIgv(venta.getIgv());
        response.setTotal(venta.getTotal());
        response.setFecha(venta.getFecha());
        response.setHora(venta.getHora());
        return response;
    }

    private MetodoPagoResponseDTO convertMetodoPagoToResponse(MetodoPago metodoPago) {
        MetodoPagoResponseDTO response = new MetodoPagoResponseDTO();
        response.setIdMetodoPago(metodoPago.getIdMetodoPago());
        response.setNombre(metodoPago.getNombre());
        return response;
    }

    private TipoComprobanteResponseDTO convertTipoComprobanteToResponse(TipoComprobante tipoComprobante) {
        TipoComprobanteResponseDTO response = new TipoComprobanteResponseDTO();
        response.setIdTipoComprobante(tipoComprobante.getIdTipoComprobante());
        response.setNombre(tipoComprobante.getNombre());
        response.setCorrelativo(tipoComprobante.getCorrelativo());
        response.setSerie(tipoComprobante.getSerie());
        response.setFormatoSerie(tipoComprobante.getFormatoSerie());
        response.setCodigo(tipoComprobante.getCodigo());
        return response;
    }
}