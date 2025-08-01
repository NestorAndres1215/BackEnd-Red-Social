package com.na.backend.service;

import com.na.backend.model.Suspensiones;
import com.na.backend.model.Usuario;

import java.util.List;

public interface SuspencionesService {

    String obtenerUltimoCodigo();

    List<Suspensiones> listar();

    List<Suspensiones> listarPorUsuario(Usuario usuario);

}
