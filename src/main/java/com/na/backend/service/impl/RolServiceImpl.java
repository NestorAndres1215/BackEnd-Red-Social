package com.na.backend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.na.backend.model.Rol;
import com.na.backend.repository.RolRepository;
import com.na.backend.service.RolService;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public Rol registrar(Rol roles) {
        return rolRepository.save(roles);
    }

    @Override
    public List<Rol> listarTodos() {
        return rolRepository.findAll();
    }

}
