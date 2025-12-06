package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.cliente.CreateClienteRequestDTO;
import com.melateacafe.backend.dto.request.cliente.UpdateClienteRequestDTO;
import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.entity.Cliente;
import com.melateacafe.backend.entity.TipoDocumento;
import com.melateacafe.backend.repository.ClienteRepository;
import com.melateacafe.backend.repository.TipoDocumentoRepository;
import com.melateacafe.backend.service.ClienteService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findAll() {
        return clienteRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findById(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));
        return convertToResponse(cliente);
    }

    @Override
    @Transactional
    public ClienteResponseDTO create(CreateClienteRequestDTO request) {
        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(request.getIdTipoDocumento())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado con id: " + request.getIdTipoDocumento()));

        if (clienteRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe un cliente con el número de documento: " + request.getNumeroDocumento());
        }

        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un cliente con el email: " + request.getEmail());
        }

        validateDocumentoLength(tipoDocumento.getIdTipoDocumento(), request.getNumeroDocumento());

        Cliente cliente = new Cliente();
        cliente.setTipoDocumento(tipoDocumento);
        cliente.setNombres(request.getNombres());
        cliente.setApellidoPaterno(request.getApellidoPaterno());
        cliente.setApellidoMaterno(request.getApellidoMaterno());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        cliente.setRazonSocial(request.getRazonSocial());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setDireccion(request.getDireccion());
        cliente.setEstado(request.getEstado() != null ? request.getEstado() : true);

        Cliente saved = clienteRepository.save(cliente);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public ClienteResponseDTO update(Integer id, UpdateClienteRequestDTO request) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        TipoDocumento tipoDocumento = tipoDocumentoRepository.findById(request.getIdTipoDocumento())
                .orElseThrow(() -> new EntityNotFoundException("Tipo de documento no encontrado con id: " + request.getIdTipoDocumento()));

        if (!cliente.getNumeroDocumento().equals(request.getNumeroDocumento())) {
            if (clienteRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
                throw new IllegalArgumentException("Ya existe un cliente con el número de documento: " + request.getNumeroDocumento());
            }
        }

        if (!cliente.getEmail().equals(request.getEmail())) {
            if (clienteRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("Ya existe un cliente con el email: " + request.getEmail());
            }
        }

        validateDocumentoLength(tipoDocumento.getIdTipoDocumento(), request.getNumeroDocumento());

        cliente.setTipoDocumento(tipoDocumento);
        cliente.setNombres(request.getNombres());
        cliente.setApellidoPaterno(request.getApellidoPaterno());
        cliente.setApellidoMaterno(request.getApellidoMaterno());
        cliente.setNumeroDocumento(request.getNumeroDocumento());
        cliente.setRazonSocial(request.getRazonSocial());
        cliente.setTelefono(request.getTelefono());
        cliente.setEmail(request.getEmail());
        cliente.setDireccion(request.getDireccion());
        cliente.setEstado(request.getEstado() != null ? request.getEstado() : cliente.getEstado());

        Cliente updated = clienteRepository.save(cliente);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado con id: " + id));

        if (clienteRepository.hasReservasActivas(id)) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene reservas activas");
        }

        if (clienteRepository.hasPedidosPendientes(id)) {
            throw new IllegalStateException("No se puede eliminar el cliente porque tiene pedidos pendientes");
        }

        clienteRepository.delete(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findByEstado(Boolean estado) {
        return clienteRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findByNumeroDocumento(String numeroDocumento) {
        Cliente cliente = clienteRepository.findByNumeroDocumento(numeroDocumento);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente no encontrado con número de documento: " + numeroDocumento);
        }
        return convertToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public ClienteResponseDTO findByEmail(String email) {
        Cliente cliente = clienteRepository.findByEmail(email);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente no encontrado con email: " + email);
        }
        return convertToResponse(cliente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findByNombre(String nombre) {
        return clienteRepository.findByNombresContainingIgnoreCaseOrApellidoPaternoContainingIgnoreCase(nombre, nombre).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findClientesConReservas() {
        return clienteRepository.findClientesConReservas().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> findClientesConPedidos() {
        return clienteRepository.findClientesConPedidos().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private void validateDocumentoLength(Integer idTipoDocumento, String numeroDocumento) {
        switch (idTipoDocumento) {
            case 1:
                if (numeroDocumento.length() != 8) {
                    throw new IllegalArgumentException("El DNI debe tener exactamente 8 dígitos");
                }
                if (!numeroDocumento.matches("\\d+")) {
                    throw new IllegalArgumentException("El DNI debe contener solo números");
                }
                break;
            case 2:
                if (numeroDocumento.length() != 11) {
                    throw new IllegalArgumentException("El RUC debe tener exactamente 11 dígitos");
                }
                if (!numeroDocumento.matches("\\d+")) {
                    throw new IllegalArgumentException("El RUC debe contener solo números");
                }
                break;
            case 3:
                if (numeroDocumento.length() < 9 || numeroDocumento.length() > 12) {
                    throw new IllegalArgumentException("El Carnet de Extranjería debe tener entre 9 y 12 caracteres");
                }
                break;
            case 4:
                if (numeroDocumento.length() < 7 || numeroDocumento.length() > 15) {
                    throw new IllegalArgumentException("El Pasaporte debe tener entre 7 y 15 caracteres");
                }
                break;
        }
    }

    private ClienteResponseDTO convertToResponse(Cliente cliente) {
        ClienteResponseDTO response = new ClienteResponseDTO();
        response.setIdCliente(cliente.getIdCliente());
        response.setTipoDocumento(convertTipoDocumentoToResponse(cliente.getTipoDocumento()));
        response.setNombres(cliente.getNombres());
        response.setApellidoPaterno(cliente.getApellidoPaterno());
        response.setApellidoMaterno(cliente.getApellidoMaterno());
        response.setNumeroDocumento(cliente.getNumeroDocumento());
        response.setRazonSocial(cliente.getRazonSocial());
        response.setTelefono(cliente.getTelefono());
        response.setEmail(cliente.getEmail());
        response.setDireccion(cliente.getDireccion());
        response.setFechaCreacion(cliente.getFechaCreacion());
        response.setEstado(cliente.getEstado());
        return response;
    }

    private TipoDocumentoResponseDTO convertTipoDocumentoToResponse(TipoDocumento tipoDocumento) {
        TipoDocumentoResponseDTO response = new TipoDocumentoResponseDTO();
        response.setIdTipoDocumento(tipoDocumento.getIdTipoDocumento());
        response.setNombre(tipoDocumento.getNombre());
        return response;
    }
}