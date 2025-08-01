package com.na.backend.service;

import com.na.backend.dto.request.HistorialRequestDTO;
import com.na.backend.dto.response.HistorialResponseDTO;
import com.na.backend.model.Historial;

import java.util.List;

public interface HistorialService {

     Historial guardar(HistorialRequestDTO historialRequestDTO);

    // List<HistorialDTO> obtenerHistorial(String username, String estado);

    List<HistorialResponseDTO> listarHistorialPorUsuario(String username, String estado);

    String UltimoCodigo();

}
