package com.na.backend.service.impl;

import com.na.backend.dto.request.HistorialRequestDTO;
import com.na.backend.dto.response.HistorialResponseDTO;
import com.na.backend.mapper.HistorialMapper;
import com.na.backend.model.Historial;
import com.na.backend.model.Usuario;
import com.na.backend.repository.HistorialRepository;

import com.na.backend.service.HistorialService;
import com.na.backend.service.UsuarioService;
import com.na.backend.util.SecuenciaUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class HistorialServiceImpl implements HistorialService {

    private final HistorialRepository historialRepository;
    private final HistorialMapper historialMapper;
    private final UsuarioService usuarioService;

    public HistorialServiceImpl(HistorialRepository historialRepository, HistorialMapper historialMapper, UsuarioService usuarioService) {
        this.historialRepository = historialRepository;
        this.historialMapper = historialMapper;
        this.usuarioService = usuarioService;
    }

    @Override
    public String UltimoCodigo() {
        return historialRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<HistorialResponseDTO> listarHistorialPorUsuario(String username, String estado) {
        List<Object[]> resultados = historialRepository.listarHistorialPorUsuario(username, estado);

        List<HistorialResponseDTO> listaDTO = new ArrayList<>();
        for (Object[] fila : resultados) {
            HistorialResponseDTO dto = historialMapper.listarHistorial(fila);
            listaDTO.add(dto);
        }

        return listaDTO;
    }

    @Override
    public Historial guardar(HistorialRequestDTO historialRequestDTO) {
        String ultimoCodigo = UltimoCodigo();
        String nuevoCodigo = SecuenciaUtil.generarSiguienteCodigo(ultimoCodigo);
        Usuario usuario = usuarioService.findByUsername(historialRequestDTO.getUsuarioHistorial())
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Historial historial = new Historial();
        historial.setCodigo(nuevoCodigo);
        historial.setHora(LocalTime.now());
        historial.setFecha(LocalDate.now());
        historial.setDetalle(historialRequestDTO.getDetalleHistorial());
        historial.setUsuario(usuario.getCodigo());
        historial.setEstado("ACTIVADO");

        return historialRepository.save(historial);
    }
}
