package com.na.backend.controller;


import com.na.backend.dto.AdminDTO;
import com.na.backend.dto.NormalDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Normal;
import com.na.backend.service.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/normal")
public class NormalController {

    @Autowired
    private NormalService normalService;

    @PostMapping("/guardar-normal")
    public ResponseEntity<?> guardar(@RequestBody NormalDTO normalDTO) throws Exception {
        try {
            return ResponseEntity.ok(normalService.Registro(normalDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/actualizar-normal")
    public ResponseEntity<?> actualizar(@RequestBody NormalDTO normalDTO) throws Exception {
        try {
            return ResponseEntity.ok(normalService.Actualizar(normalDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar/activo")
    public List<Normal> listarActivados() {
        return normalService.listarUsuarioNormalActivos();
    }

    @GetMapping("/listar/inactivo")
    public List<Normal> listarDesactivados() {
        return normalService.listarUsuarioNormalDesactivos();
    }

    @GetMapping("/listar/suspendido")
    public List<Normal> listarSuspendidos() {
        return normalService.listarUsuarioNormalSuspendidos();
    }

    @GetMapping("/listar/{username}")
    public List<Normal> obtenerPorUsuarioActivo(@PathVariable String username) {
        return normalService.obtenerNormalesPorUsuarioActivo(username);
    }

}
