package com.na.backend.service;

import com.na.backend.dto.HistorialDTO;
import com.na.backend.model.Historial;

import java.util.List;
import java.util.Map;

public interface HistorialService {

    Historial guardar(HistorialDTO historialDTO);

     List<HistorialDTO> obtenerHistorial(String username, String estado);

    String UltimoCodigo();

}
