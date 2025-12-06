package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.pedido.CreatePedidoRequestDTO;
import com.melateacafe.backend.dto.request.pedido.DetallePedidoRequestDTO;
import com.melateacafe.backend.dto.request.pedido.UpdatePedidoRequestDTO;
import com.melateacafe.backend.dto.response.pedido.*;
import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;
import com.melateacafe.backend.dto.response.producto.ProductoResponseDTO;
import com.melateacafe.backend.entity.*;
import com.melateacafe.backend.repository.*;
import com.melateacafe.backend.service.PedidoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @Autowired
    private TipoPedidoRepository tipoPedidoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findAll() {
        return pedidoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PedidoResponseDTO findById(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + id));
        return convertToResponse(pedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO create(CreatePedidoRequestDTO request) {
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + request.getIdCliente()));

        EstadoPedido estado = estadoPedidoRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new EntityNotFoundException("Estado de pedido no encontrado con id: " + request.getIdEstado()));

        TipoPedido tipoPedido = tipoPedidoRepository.findById(request.getIdTipoPedido())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de pedido no encontrado con id: " + request.getIdTipoPedido()));

        Mesa mesa = null;
        if (request.getIdMesa() != null) {
            mesa = mesaRepository.findById(request.getIdMesa())
                    .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + request.getIdMesa()));

            if (mesa.getEstadoMesa().getIdEstadoMesa() == 4 || mesa.getEstadoMesa().getIdEstadoMesa() == 5) {
                throw new IllegalStateException("La mesa no está disponible para pedidos");
            }
        }

        Integer tipoPedidoId = request.getIdTipoPedido();
        if (tipoPedidoId == 2) {
            if (request.getDireccionEntrega() == null || request.getDireccionEntrega().trim().isEmpty()) {
                throw new IllegalArgumentException("La dirección de entrega es obligatoria para pedidos de delivery");
            }
            if (request.getDeliveryCosto() == null || request.getDeliveryCosto().compareTo(BigDecimal.ZERO) < 0) {
                throw new IllegalArgumentException("El costo de delivery debe ser mayor o igual a 0");
            }
        }

        if (tipoPedidoId == 1 && request.getIdMesa() == null) {
            throw new IllegalArgumentException("Los pedidos locales requieren una mesa asignada");
        }

        if (request.getDetalles() == null || request.getDetalles().isEmpty()) {
            throw new IllegalArgumentException("El pedido debe contener al menos un producto");
        }

        Pedido pedido = new Pedido();
        pedido.setCliente(cliente);
        pedido.setMesa(mesa);
        pedido.setEstadoPedido(estado);
        pedido.setTipoPedido(tipoPedido);
        pedido.setDireccionEntrega(request.getDireccionEntrega());
        pedido.setObservaciones(request.getObservaciones());
        pedido.setDeliveryCosto(request.getDeliveryCosto() != null ? request.getDeliveryCosto() : BigDecimal.ZERO);

        BigDecimal subtotal = BigDecimal.ZERO;

        Pedido savedPedido = pedidoRepository.save(pedido);

        for (DetallePedidoRequestDTO detalleDTO : request.getDetalles()) {
            Producto producto = productoRepository.findById(detalleDTO.getIdProducto())
                    .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + detalleDTO.getIdProducto()));

            if (!producto.isEstado()) {
                throw new IllegalStateException("El producto '" + producto.getNombre() + "' no está disponible");
            }

            DetallePedido detalle = new DetallePedido();
            detalle.setPedido(savedPedido);
            detalle.setProducto(producto);
            detalle.setCantidad(detalleDTO.getCantidad());
            detalle.setPrecioUnitario(producto.getPrecio());
            detalle.setSubtotal(producto.getPrecio().multiply(new BigDecimal(detalleDTO.getCantidad())));
            detalle.setObservaciones(detalleDTO.getObservaciones());

            detallePedidoRepository.save(detalle);

            subtotal = subtotal.add(detalle.getSubtotal());
        }

        savedPedido.setSubtotal(subtotal);
        savedPedido.setTotal(subtotal.add(savedPedido.getDeliveryCosto()));

        if (mesa != null && tipoPedidoId == 1) {
            EstadoMesa estadoOcupada = new EstadoMesa();
            estadoOcupada.setIdEstadoMesa(2);
            mesa.setEstadoMesa(estadoOcupada);
            mesaRepository.save(mesa);
        }

        Pedido finalPedido = pedidoRepository.save(savedPedido);
        return convertToResponse(finalPedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(Integer id, UpdatePedidoRequestDTO request) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + id));

        EstadoPedido estado = estadoPedidoRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new EntityNotFoundException("Estado de pedido no encontrado con id: " + request.getIdEstado()));

        Integer estadoActual = pedido.getEstadoPedido().getIdEstadoPedido();
        Integer estadoNuevo = request.getIdEstado();

        if (estadoActual == 7 || estadoActual == 8) {
            throw new IllegalStateException("No se puede modificar un pedido cancelado o rechazado");
        }

        if (estadoNuevo < estadoActual && !(estadoActual == 2 && estadoNuevo == 1)) {
            throw new IllegalArgumentException("No se puede regresar a estados anteriores");
        }

        pedido.setEstadoPedido(estado);
        pedido.setObservaciones(request.getObservaciones());

        if (estadoNuevo == 6 && pedido.getMesa() != null) {
            Mesa mesa = pedido.getMesa();
            EstadoMesa estadoDisponible = new EstadoMesa();
            estadoDisponible.setIdEstadoMesa(1);
            mesa.setEstadoMesa(estadoDisponible);
            mesaRepository.save(mesa);
        }

        Pedido updated = pedidoRepository.save(pedido);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + id));

        Integer estado = pedido.getEstadoPedido().getIdEstadoPedido();
        if (estado != 1 && estado != 7 && estado != 8) {
            throw new IllegalStateException("Solo se pueden eliminar pedidos pendientes o cancelados");
        }

        if (pedidoRepository.hasVenta(id)) {
            throw new IllegalStateException("No se puede eliminar el pedido porque tiene una venta asociada");
        }

        pedidoRepository.delete(pedido);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findByEstado(Integer idEstado) {
        return pedidoRepository.findByEstado_IdEstadoPedido(idEstado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findByTipoPedido(Integer idTipoPedido) {
        return pedidoRepository.findByTipoPedido_IdTipoPedido(idTipoPedido).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findByCliente(Integer idCliente) {
        return pedidoRepository.findByCliente_IdCliente(idCliente).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<PedidoResponseDTO> findByMesa(Integer idMesa) {
        return pedidoRepository.findByMesa_IdMesa(idMesa).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelarPedido(Integer id) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Pedido no encontrado con id: " + id));

        Integer estado = pedido.getEstadoPedido().getIdEstadoPedido();
        if (estado != 1 && estado != 2) {
            throw new IllegalStateException("Solo se pueden cancelar pedidos pendientes o confirmados");
        }

        EstadoPedido estadoCancelado = new EstadoPedido();
        estadoCancelado.setIdEstadoPedido(7);
        pedido.setEstadoPedido(estadoCancelado);

        if (pedido.getMesa() != null) {
            Mesa mesa = pedido.getMesa();
            EstadoMesa estadoDisponible = new EstadoMesa();
            estadoDisponible.setIdEstadoMesa(1);
            mesa.setEstadoMesa(estadoDisponible);
            mesaRepository.save(mesa);
        }

        pedidoRepository.save(pedido);
    }

    private PedidoResponseDTO convertToResponse(Pedido pedido) {
        PedidoResponseDTO response = new PedidoResponseDTO();
        response.setIdPedido(pedido.getIdPedido());
        response.setMesa(pedido.getMesa() != null ? convertMesaToResponse(pedido.getMesa()) : null);
        response.setCliente(convertClienteToResponse(pedido.getCliente()));
        response.setEstado(convertEstadoPedidoToResponse(pedido.getEstadoPedido()));
        response.setTipoPedido(convertTipoPedidoToResponse(pedido.getTipoPedido()));
        response.setSubtotal(pedido.getSubtotal());
        response.setDeliveryCosto(pedido.getDeliveryCosto());
        response.setTotal(pedido.getTotal());
        response.setDireccionEntrega(pedido.getDireccionEntrega());
        response.setObservaciones(pedido.getObservaciones());
        response.setFechaCreacion(pedido.getFechaCreacion());

        List<DetallePedido> detalles = detallePedidoRepository.findByPedido_IdPedido(pedido.getIdPedido());
        response.setDetalles(detalles.stream()
                .map(this::convertDetallePedidoToResponse)
                .collect(Collectors.toList()));

        return response;
    }

    private DetallePedidoResponseDTO convertDetallePedidoToResponse(DetallePedido detalle) {
        DetallePedidoResponseDTO response = new DetallePedidoResponseDTO();
        response.setIdDetallePedido(detalle.getIdDetallePedido());
        response.setProducto(convertProductoToResponse(detalle.getProducto()));
        response.setCantidad(detalle.getCantidad());
        response.setPrecioUnitario(detalle.getPrecioUnitario());
        response.setSubtotal(detalle.getSubtotal());
        response.setObservaciones(detalle.getObservaciones());
        return response;
    }

    private ProductoResponseDTO convertProductoToResponse(Producto producto) {
        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setIdProducto(producto.getIdProducto());
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setImagenUrl(producto.getImagenUrl());
        return response;
    }

    private ClienteResponseDTO convertClienteToResponse(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setIdCliente(cliente.getIdCliente());
        response.setNombres(cliente.getNombres());
        response.setApellidoPaterno(cliente.getApellidoPaterno());
        response.setEmail(cliente.getEmail());
        response.setTelefono(cliente.getTelefono());
        return response;
    }

    private MesaResponseDTO convertMesaToResponse(Mesa mesa) {
        MesaResponseDTO response = new MesaResponseDTO();
        response.setIdMesa(mesa.getIdMesa());
        response.setNumero(mesa.getNumero());
        response.setCapacidad(mesa.getCapacidad());
        return response;
    }

    private EstadoPedidoResponseDTO convertEstadoPedidoToResponse(EstadoPedido estado) {
        EstadoPedidoResponseDTO response = new EstadoPedidoResponseDTO();
        response.setIdEstadoPedido(estado.getIdEstadoPedido());
        response.setNombre(estado.getNombre());
        return response;
    }

    private TipoPedidoResponseDTO convertTipoPedidoToResponse(TipoPedido tipo) {
        TipoPedidoResponseDTO response = new TipoPedidoResponseDTO();
        response.setIdTipoPedido(tipo.getIdTipoPedido());
        response.setNombre(tipo.getNombre());
        return response;
    }
}