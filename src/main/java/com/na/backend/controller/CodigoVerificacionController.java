package com.na.backend.controller;

import com.na.backend.dto.CodigoVerificacioDTO;
import com.na.backend.dto.VerificacionDTO;
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
    public ResponseEntity<?> guardarAdmin(@RequestBody VerificacionDTO verificacionDTO) throws Exception {
        try {

            return ResponseEntity.ok(codigoVerificacionService.generarYGuardarCodigo(verificacionDTO));

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }


    @PostMapping("/verificar-codigo")
    public ResponseEntity<?> verificar(@RequestBody CodigoVerificacioDTO codigoVerificacioDTO) {
        try {
            return ResponseEntity.ok(codigoVerificacionService.verificar(codigoVerificacioDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en la verificación");
        }
    }
}
