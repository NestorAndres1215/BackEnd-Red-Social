package com.na.backend.controller;

import com.na.backend.dto.AdminDTO;
import com.na.backend.dto.HistorialDTO;
import com.na.backend.service.HistorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/historial")
public class HistorialController {

    @Autowired
    private HistorialService historialService;


    @PostMapping("/registrar")
    public ResponseEntity<?> registrar(@RequestBody HistorialDTO historialDTO) throws Exception {
        try {
            return ResponseEntity.ok(historialService.guardar(historialDTO));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/listar")
    public ResponseEntity<?>listar(@RequestParam String username,@RequestParam String estado){
      try{
        List<HistorialDTO> historial = historialService.obtenerHistorial(username,estado);
        return ResponseEntity.ok(historial);
      } catch (Exception e) {
          return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
      }
    }

}
