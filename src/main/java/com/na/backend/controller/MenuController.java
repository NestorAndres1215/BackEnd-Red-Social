package com.na.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.na.backend.model.Menu;
import com.na.backend.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/listaRolesCodigo/{rolCodigoPrimero}")
    public ResponseEntity<?> obtenerMenusPorRol(@PathVariable String rolCodigoPrimero) {
        List<Menu> menus = menuService.obtenerMenusPorRol(rolCodigoPrimero);
        return ResponseEntity.ok(menus);
    }

    @GetMapping("/listar/PrimerNivel")
    public ResponseEntity<List<Menu>> menuPrimero() {
        List<Menu> menuPrimero = menuService.listarMenuPorNiveles(1);
        return ResponseEntity.ok(menuPrimero);
    }

    @GetMapping("/listar/SegundoNivel")
    public ResponseEntity<List<Menu>> menuSegundo() {
        List<Menu> menuSegundo = menuService.listarMenuPorNiveles(2);
        return ResponseEntity.ok(menuSegundo);
    }

    @GetMapping("/listar/TercerNivel")
    public ResponseEntity<List<Menu>> menuTercero() {
        List<Menu> menuSegundo = menuService.listarMenuPorNiveles(3);
        return ResponseEntity.ok(menuSegundo);
    }
}
