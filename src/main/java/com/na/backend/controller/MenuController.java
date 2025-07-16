package com.na.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.na.backend.message.MenuMessage;
import com.na.backend.message.SeguridadMessage;
import com.na.backend.model.Menu;
import com.na.backend.model.Rol;
import com.na.backend.repository.RolRepository;
import com.na.backend.service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RolRepository rolRepository; // Inyectamos el repositorio de Rol

    @GetMapping("/listaRolesCodigo/{rolCodigoPrimero}")
    public ResponseEntity<?> obtenerMenusPorRol(@PathVariable String rolCodigoPrimero) {
        try {
            // Buscar el rol en la base de datos
            Rol rol = rolRepository.findByCodigo(rolCodigoPrimero);

            // Validar si el rol existe
            if (rol == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MenuMessage.ROL_NO_ENCONTRADO.getMensaje() + rolCodigoPrimero);
            }

            // Obtener menús para el rol
            List<Menu> menus = menuService.obtenerMenusPorRol(rol);

            // Validar si hay menús
            if (menus.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(MenuMessage.MENU_ROLES.getMensaje() + rolCodigoPrimero);
            }

            // Retornar menús encontrados
            return ResponseEntity.ok(menus);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(SeguridadMessage.ERROR_INTERNO.getMensaje());
        }
    }

    @GetMapping("/listar/PrimerNivel")
    public ResponseEntity<List<Menu>> menuPrimero() {
        List<Menu> menuPrimero = menuService.menuPrimero();
        return ResponseEntity.ok(menuPrimero);
    }

    @GetMapping("/listar/SegundoNivel")
    public ResponseEntity<List<Menu>> menuSegundo() {
        List<Menu> menuSegundo = menuService.menuSegundo();
        return ResponseEntity.ok(menuSegundo);
    }

    @GetMapping("/listar/TercerNivel")
    public ResponseEntity<List<Menu>> menuTercero() {
        List<Menu> menuSegundo = menuService.menuTercero();
        return ResponseEntity.ok(menuSegundo);
    }
}
