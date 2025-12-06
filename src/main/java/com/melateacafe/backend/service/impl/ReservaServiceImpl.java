package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.reserva.CreateReservaRequestDTO;
import com.melateacafe.backend.dto.request.reserva.UpdateReservaRequestDTO;
import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;
import com.melateacafe.backend.dto.response.reserva.ReservaResponseDTO;
import com.melateacafe.backend.entity.Cliente;
import com.melateacafe.backend.entity.EstadoMesa;
import com.melateacafe.backend.entity.Mesa;
import com.melateacafe.backend.entity.Reserva;
import com.melateacafe.backend.repository.ClienteRepository;
import com.melateacafe.backend.repository.MesaRepository;
import com.melateacafe.backend.repository.ReservaRepository;
import com.melateacafe.backend.service.ReservaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservaServiceImpl implements ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findAll() {
        return reservaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ReservaResponseDTO findById(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + id));
        return convertToResponse(reserva);
    }

    @Override
    @Transactional
    public ReservaResponseDTO create(CreateReservaRequestDTO request) {
        Mesa mesa = mesaRepository.findById(request.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + request.getIdMesa()));

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + request.getIdCliente()));

        if (mesa.getEstadoMesa().getIdEstadoMesa() != 1) {
            throw new IllegalStateException("La mesa no está disponible para reservas");
        }

        if (request.getFechaReserva().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de reserva no puede ser en el pasado");
        }

        if (reservaRepository.existsByMesaAndFechaReservaAndEstado(
                mesa, request.getFechaReserva(), true)) {
            throw new IllegalStateException("La mesa ya tiene una reserva activa para esta fecha");
        }

        if (request.getNumeroPersonas() > mesa.getCapacidad()) {
            throw new IllegalArgumentException(
                    String.format("El número de personas (%d) excede la capacidad de la mesa (%d)",
                            request.getNumeroPersonas(), mesa.getCapacidad())
            );
        }

        Reserva reserva = new Reserva();
        reserva.setMesa(mesa);
        reserva.setCliente(cliente);
        reserva.setNumeroPersonas(request.getNumeroPersonas());
        reserva.setObservaciones(request.getObservaciones());
        reserva.setFechaReserva(request.getFechaReserva());
        reserva.setEstado(request.getEstado() != null ? request.getEstado() : true);

        Reserva saved = reservaRepository.save(reserva);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public ReservaResponseDTO update(Integer id, UpdateReservaRequestDTO request) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + id));

        Mesa mesa = mesaRepository.findById(request.getIdMesa())
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + request.getIdMesa()));

        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + request.getIdCliente()));

        if (!reserva.getFechaReserva().equals(request.getFechaReserva())) {
            if (request.getFechaReserva().isBefore(LocalDate.now())) {
                throw new IllegalArgumentException("La fecha de reserva no puede ser en el pasado");
            }
        }

        if (reserva.getMesa().getIdMesa() != request.getIdMesa() ||
                !reserva.getFechaReserva().equals(request.getFechaReserva())) {
            if (reservaRepository.existsByMesaAndFechaReservaAndEstadoAndIdReservaNot(
                    mesa, request.getFechaReserva(), true, id)) {
                throw new IllegalStateException("La mesa ya tiene una reserva activa para esta fecha");
            }
        }

        if (request.getNumeroPersonas() > mesa.getCapacidad()) {
            throw new IllegalArgumentException(
                    String.format("El número de personas (%d) excede la capacidad de la mesa (%d)",
                            request.getNumeroPersonas(), mesa.getCapacidad())
            );
        }

        reserva.setMesa(mesa);
        reserva.setCliente(cliente);
        reserva.setNumeroPersonas(request.getNumeroPersonas());
        reserva.setObservaciones(request.getObservaciones());
        reserva.setFechaReserva(request.getFechaReserva());
        reserva.setEstado(request.getEstado() != null ? request.getEstado() : reserva.isEstado());

        Reserva updated = reservaRepository.save(reserva);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + id));
        reservaRepository.delete(reserva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findByEstado(Boolean estado) {
        return reservaRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findByFecha(LocalDate fecha) {
        return reservaRepository.findByFechaReserva(fecha).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findByMesa(Integer idMesa) {
        return reservaRepository.findByMesa_IdMesa(idMesa).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findByCliente(Integer idCliente) {
        return reservaRepository.findByCliente_IdCliente(idCliente).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReservaResponseDTO> findReservasActivas() {
        return reservaRepository.findByEstadoAndFechaReservaGreaterThanEqual(true, LocalDate.now()).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancelarReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + id));
        reserva.setEstado(false);
        reservaRepository.save(reserva);
    }

    @Override
    @Transactional
    public void confirmarReserva(Integer id) {
        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reserva no encontrada con id: " + id));

        Mesa mesa = reserva.getMesa();
        EstadoMesa estadoReservada = new EstadoMesa();
        estadoReservada.setIdEstadoMesa(3);
        mesa.setEstadoMesa(estadoReservada);
        mesaRepository.save(mesa);

        reserva.setEstado(true);
        reservaRepository.save(reserva);
    }

    private ReservaResponseDTO convertToResponse(Reserva reserva) {
        ReservaResponseDTO response = new ReservaResponseDTO();
        response.setIdReserva(reserva.getIdReserva());
        response.setMesa(convertMesaToResponse(reserva.getMesa()));
        response.setCliente(convertClienteToResponse(reserva.getCliente()));
        response.setNumeroPersonas(reserva.getNumeroPersonas());
        response.setObservaciones(reserva.getObservaciones());
        response.setEstado(reserva.isEstado());
        response.setFechaReserva(reserva.getFechaReserva());
        response.setFechaCreacion(reserva.getFechaCreacion());
        return response;
    }

    private MesaResponseDTO convertMesaToResponse(Mesa mesa) {
        MesaResponseDTO response = new MesaResponseDTO();
        response.setIdMesa(mesa.getIdMesa());
        response.setEstadoMesa(convertEstadoMesaToResponse(mesa.getEstadoMesa()));
        response.setNumero(mesa.getNumero());
        response.setCapacidad(mesa.getCapacidad());
        response.setEstado(mesa.isEstado());
        return response;
    }

    private EstadoMesaResponseDTO convertEstadoMesaToResponse(EstadoMesa estadoMesa) {
        EstadoMesaResponseDTO response = new EstadoMesaResponseDTO();
        response.setIdEstadoMesa(estadoMesa.getIdEstadoMesa());
        response.setNombre(estadoMesa.getNombre());
        return response;
    }

    private ClienteResponseDTO convertClienteToResponse(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setIdCliente(cliente.getIdCliente());
        response.setNombres(cliente.getNombres());
        response.setApellidoPaterno(cliente.getApellidoPaterno());
        response.setApellidoMaterno(cliente.getApellidoMaterno());
        response.setNumeroDocumento(cliente.getNumeroDocumento());
        response.setTelefono(cliente.getTelefono());
        response.setEmail(cliente.getEmail());
        return response;
    }
}
