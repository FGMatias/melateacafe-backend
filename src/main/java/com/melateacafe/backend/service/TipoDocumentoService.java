package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.entity.TipoDocumento;

import java.util.List;

public interface TipoDocumentoService {
    List<TipoDocumentoResponseDTO> findAll();
}
