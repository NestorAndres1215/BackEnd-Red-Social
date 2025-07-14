package com.na.backend.service.impl;

import java.util.List;

import com.na.backend.service.AdminService;
import com.na.backend.service.NormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.na.backend.model.Usuario;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Lazy
    @Autowired
    private AdminService adminService;

    @Lazy
    @Autowired
    private NormalService normalService;

    @Override
    public String obtenerUltimoCodigoUsuario() {
        return usuarioRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public boolean usuarioExistePorUsername(String username) {
        return usuarioRepository.existsByUsername(username);
    }

    @Override
    public boolean existsByUsernameAndPassword(String username, String password) {
        return usuarioRepository.existsByUsernameAndPassword(username, password);
    }

    @Override
    public ResponseEntity<?> validacionBloqueo(String username) {
        List<Usuario> usuarios = usuarioRepository.findByUsername(username);

        if (usuarios.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }

        Usuario usuario = usuarios.get(0); // suponiendo que username es único
        String userRole = usuario.getRol().getNombre();

        if ("ADMIN".equalsIgnoreCase(userRole)) {
            return ResponseEntity.ok(adminService.BloquearUsuario(username));
        } else if ("MODERADOR".equalsIgnoreCase(userRole)) {

            return ResponseEntity.ok("Usuario MODERADOR habilitado");
        } else if ("NORMAL".equalsIgnoreCase(userRole)) {

            return ResponseEntity.ok(normalService.BloquearUsuario(username));
        } else {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Rol de usuario no reconocido: " + userRole);
        }
    }


}