package com.na.backend.service;

import java.util.List;
import java.util.Optional;

import com.na.backend.model.Rol;

public interface RolService {

    Rol registrar(Rol roles);

    List<Rol> listarTodos();

    Optional<Rol> findByCodigo(String rol);
}
