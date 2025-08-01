package com.na.backend.controller;


import com.na.backend.dto.request.NormalRequestDTO;
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
    public ResponseEntity<?> guardar(@RequestBody NormalRequestDTO normalDTO) throws Exception {
        try {
            return ResponseEntity.ok(normalService.Registro(normalDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PutMapping("/actualizar-normal")
    public ResponseEntity<?> actualizar(@RequestBody NormalRequestDTO normalDTO) throws Exception {
        try {
            return ResponseEntity.ok(normalService.Actualizar(normalDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar/activo")
    public List<Normal> listarActivados() {
        return normalService.obtenerNormalesPorEstado("ACTIVO");
    }

    @GetMapping("/listar/inactivo")
    public List<Normal> listarDesactivados() {
        return normalService.obtenerNormalesPorEstado("INACTIVO");
    }

    @GetMapping("/listar/suspendido")
    public List<Normal> listarSuspendidos() {
        return normalService.obtenerNormalesPorEstado("SUSPENDIDO");
    }

    @GetMapping("/listar/bloqueado")
    public List<Normal> listarBloqueado() {
        return normalService.obtenerNormalesPorEstado("BLOQUEADO");
    }

    @GetMapping("/listar/inhabilito")
    public List<Normal> listarInhabilitado() {
        return normalService.obtenerNormalesPorEstado("INHABILITADO");
    }

    @GetMapping("/listar/{username}")
    public List<Normal> obtenerPorUsuarioActivo(@PathVariable String username) {
        return normalService.obtenerNormalesPorUsuarioActivo(username);
    }

}
