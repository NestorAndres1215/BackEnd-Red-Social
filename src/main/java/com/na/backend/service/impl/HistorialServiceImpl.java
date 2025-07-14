package com.na.backend.service.impl;

import com.na.backend.dto.HistorialDTO;
import com.na.backend.model.Historial;
import com.na.backend.model.Usuario;
import com.na.backend.repository.HistorialRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.HistorialService;
import com.na.backend.validator.Secuencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class HistorialServiceImpl implements HistorialService {

    @Autowired
    private HistorialRepository historialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Historial guardar(HistorialDTO historialDTO) {

        String ultimoCodigo = UltimoCodigo();
        String nuevoCodigo = Secuencia.incrementarSecuencia(ultimoCodigo);
        List<Usuario> usuarioExistente = usuarioRepository.findByUsername(historialDTO.getUsuario());
        Usuario usuario = usuarioExistente.get(0);
        String codigoUsuario = usuario.getCodigo();

        Historial historial  =  new Historial();
        historial.setCodigo(nuevoCodigo);
        historial.setHora(LocalTime.now());
        historial.setFecha(LocalDate.now());
        historial.setDetalle(historialDTO.getDetalle());
        historial.setUsuario(codigoUsuario);
        historial.setEstado("ACTIVADO");


        return historialRepository.save(historial);
    }

    @Override
    public List<HistorialDTO> obtenerHistorial(String username, String estado) {
        List<Map<String, Object>> resultado = historialRepository.listarHistorialPorUsuario(username, estado);

        List<HistorialDTO> lista = new ArrayList<>();
        for (Map<String, Object> fila : resultado) {
            HistorialDTO dto = new HistorialDTO();
            dto.setCodigo((String) fila.get("codigo"));
            dto.setFecha(LocalDate.parse(fila.get("fecha").toString()));
            dto.setHora(LocalTime.parse(fila.get("hora").toString()));
            dto.setUsuario((String) fila.get("usuario"));
            dto.setEstado((String) fila.get("estado"));
            dto.setDetalle((String) fila.get("detalle"));
            lista.add(dto);
        }

        return lista;
    }

    @Override
    public String UltimoCodigo() {
        return historialRepository.obtenerUltimoCodigo();
    }
}
