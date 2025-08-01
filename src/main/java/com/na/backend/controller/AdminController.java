package com.na.backend.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.na.backend.dto.request.AdminRequestDTO;
import com.na.backend.dto.response.AdminResponseDTO;
import com.na.backend.model.Admin;
import com.na.backend.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/usuario/{usuarioCodigo}")
    public List<Admin> getAdminsByUsuario(@PathVariable String usuarioCodigo) {
        return adminService.getAdminsByUsuarioCodigo(usuarioCodigo);
    }

    @PostMapping("/guardar-admin")
    public ResponseEntity<?> guardarAdmin(@RequestBody AdminRequestDTO admin) throws Exception {
        try {
            return ResponseEntity.ok(adminService.Registro(admin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/actualizar-admin")
    public ResponseEntity<?> actualizarAdmin(@RequestBody AdminRequestDTO request) throws Exception {
        try {
            return ResponseEntity.ok(adminService.Actualizar(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar/activo")
    public List<Admin> getAdminsByEstadoTrue() {
        return adminService.listarAdmins("ACTIVO");
    }

    @GetMapping("/listar/desactivado")
    public List<Admin> getAdminsByEstadoFalse() {
        return adminService.listarAdmins("INACTIVO");
    }

    @DeleteMapping("/desactivar/{codigo}")
    public ResponseEntity<?> desactivarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(adminService.DesactivarUsuario(codigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/usuario/activar/{codigo}")
    public ResponseEntity<?> activarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(adminService.ActivarUsuario(codigo));

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @DeleteMapping("/usuario/bloquear/{codigo}")
    public ResponseEntity<?> bloquear(@PathVariable String codigo) {
        try {
            System.out.print(codigo);
            return ResponseEntity.ok(adminService.BloquearUsuario(codigo));

        } catch (Exception e) {
            System.out.print(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar/usuario/activo")
    public ResponseEntity<?> listarAdminsExcluyendoUsuario(@RequestParam String username) {
        try {
            List<AdminResponseDTO> admins = adminService.obtenerAdminsExcluyendoUsuario(username);

            if (admins.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            return ResponseEntity.ok(admins);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
