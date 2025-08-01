package com.na.backend.service.impl;

import com.na.backend.model.Suspensiones;
import com.na.backend.model.Usuario;
import com.na.backend.repository.SuspensionesRepository;
import com.na.backend.service.SuspencionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SuspencionesServiceImpl implements SuspencionesService {

    @Autowired
    private SuspensionesRepository suspensionesRepository;

    @Override
    public String obtenerUltimoCodigo() {
        return suspensionesRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<Suspensiones> listar() {
        return suspensionesRepository.findAll();
    }

    @Override
    public List<Suspensiones> listarPorUsuario(Usuario usuario) {
        return suspensionesRepository.findByUsuario(usuario);
    }

}
