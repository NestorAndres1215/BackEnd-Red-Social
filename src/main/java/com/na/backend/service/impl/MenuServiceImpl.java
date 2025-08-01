package com.na.backend.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.na.backend.model.Menu;
import com.na.backend.model.Rol;
import com.na.backend.repository.MenuRepository;
import com.na.backend.service.MenuService;
import com.na.backend.service.RolService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;
    private final RolService rolService;

    public MenuServiceImpl(MenuRepository menuRepository, RolService rolService) {
        this.menuRepository = menuRepository;
        this.rolService = rolService;
    }

    @Override
    public List<Menu> obtenerMenusPorRol(Rol rol) {
        return menuRepository.findByRol(rol);
    }

    @Override
    public List<Menu> listarMenuPorNiveles(Integer nivel) {
        return menuRepository.findByNivel(nivel);
    }

    @Override
    public List<Menu> obtenerMenusPorRol(String rolCodigo) {

        Rol rol = rolService.findByCodigo(rolCodigo)
                .orElseThrow(() -> new EntityNotFoundException("No se ha encontrado el rol: " + rolCodigo));

        List<Menu> menus = obtenerMenusPorRol(rol);

        if (menus.isEmpty()) {
            throw new EntityNotFoundException("No se encontraron menús para el rol: " + rolCodigo);
        }

        return menus;
    }
}