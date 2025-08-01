package com.na.backend.controller;

import com.na.backend.dto.request.CodigoVerificacioRequestDTO;
import com.na.backend.dto.request.VerificacionRequestDTO;
import com.na.backend.service.CodigoVerificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/codigo-verificacion")
public class CodigoVerificacionController {

    @Autowired
    private CodigoVerificacionService codigoVerificacionService;

    @PostMapping("/enviar-codigo")
    public ResponseEntity<?> guardarAdmin(@RequestBody VerificacionRequestDTO verificacionDTO) throws Exception {
        try {
            return ResponseEntity.ok(codigoVerificacionService.generarYGuardarCodigo(verificacionDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificar(@RequestBody CodigoVerificacioRequestDTO codigoVerificacioDTO) {
        try {
            return ResponseEntity.ok(codigoVerificacionService.verificar(codigoVerificacioDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la verificación");
        }
    }

}
