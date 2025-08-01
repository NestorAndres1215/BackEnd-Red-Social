package com.na.backend.service;

import com.na.backend.model.Moderador;

import java.util.Optional;

public interface ModeradorService {
    
    Moderador obtenerModeradorAleatorioActivo();

    Optional<Moderador> findById(String codigo);

    Moderador SuspenderUsuario(String usuarioCodigo);
}
