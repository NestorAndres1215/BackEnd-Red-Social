package com.na.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.na.backend.model.Rol;
import com.na.backend.repository.RolRepository;
import com.na.backend.service.RolService;


@Service
public class RolServiceImpl implements RolService {

    private final RolRepository rolRepository;

    public RolServiceImpl(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    @Override
    public Rol registrar(Rol roles) {
        return rolRepository.save(roles);
    }

    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

    @Override
    public Optional<Rol> findByCodigo(String rol) {
        return rolRepository.findByCodigo(rol);
    }


}
