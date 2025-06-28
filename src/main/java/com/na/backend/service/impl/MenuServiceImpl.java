package com.na.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.na.backend.model.Menu;
import com.na.backend.model.Rol;
import com.na.backend.repository.MenuRepository;
import com.na.backend.service.MenuService;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Override
    public List<Menu> obtenerMenusPorRol(Rol rol) {
        return menuRepository.findByRol(rol);
    }

    @Override
    public List<Menu> menuPrimero() {
        return menuRepository.findByNivel(1);
    }

    @Override
    public List<Menu> menuSegundo() {
        return menuRepository.findByNivel(2);
    }

    @Override
    public List<Menu> menuTercero() {
        return menuRepository.findByNivel(3);
    }

}