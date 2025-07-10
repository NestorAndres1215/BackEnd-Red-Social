package com.na.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.na.backend.dto.AdminDTO;
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
    public ResponseEntity<?> guardarAdmin(@RequestBody AdminDTO admin) throws Exception {
        try {
            return ResponseEntity.ok(adminService.Registro(admin));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/actualizar-admin")
    public ResponseEntity<?> actualizarAdmin(@RequestBody AdminDTO request) throws Exception {
        try {
            return ResponseEntity.ok(adminService.Actualizar(request));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar/activo")
    public List<Admin> getAdminsByEstadoTrue() {
        return adminService.listarAdminsActivados();
    }

    @GetMapping("/listar/desactivado")
    public List<Admin> getAdminsByEstadoFalse() {
        return adminService.listarAdminsDesactivado();
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

    @DeleteMapping("/activar/{codigo}")
    public ResponseEntity<?> activarPorCodigo(@PathVariable String codigo) {
        try {
            return ResponseEntity.ok(adminService.ActivarUsuario(codigo));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


    @GetMapping("/listar/usuario/activo")
    public ResponseEntity<?> listarAdminsExcluyendoUsuario(@RequestParam String username) {
        try {
            List<Map<String, Object>> admins = adminService.obtenerAdminsExcluyendoUsuario(username);

            if (admins.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }

            return ResponseEntity.ok(admins); // 200 OK
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

}
