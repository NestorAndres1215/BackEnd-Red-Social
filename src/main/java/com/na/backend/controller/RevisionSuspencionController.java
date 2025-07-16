package com.na.backend.controller;

import com.na.backend.model.RevisionSuspension;
import com.na.backend.model.Suspensiones;
import com.na.backend.model.Usuario;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.RevisionSuspensionService;
import com.na.backend.service.SuspencionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/revision/suspension")
public class RevisionSuspencionController {

    @Autowired
    private SuspencionesService suspencionesService;
    @Autowired
    private RevisionSuspensionService revisionSuspensionService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/listar/suspensiones")
    public List<Suspensiones> getAdminsByEstadoTrue() {
        return suspencionesService.listar();
    }

    @GetMapping("/listar/supensiones/usuario/{codigo}")
    public ResponseEntity<?> listarPorUsuario(@PathVariable String codigo) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findById(codigo);

        if (usuarioOpt.isPresent()) {
            List<Suspensiones> revisiones = suspencionesService.listarPorUsuario(usuarioOpt.get());
            return ResponseEntity.ok(revisiones);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
        }
    }

    @GetMapping("/listar/revision")
    public List<RevisionSuspension> listarRevision() {
        return revisionSuspensionService.listarRevision();
    }
}


