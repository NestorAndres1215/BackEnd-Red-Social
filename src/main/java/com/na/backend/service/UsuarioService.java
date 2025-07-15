package com.na.backend.service;

import java.util.List;

import com.na.backend.model.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioService {
    
    // ultimo codigo de usuario
     String obtenerUltimoCodigoUsuario();

    // Lista usuario por username
    List<Usuario> findByUsername(String username);

    // validaciones de usuario existe
    boolean usuarioExistePorUsername(String username);

    // validaciones de usuario y contraseña valida
    boolean existsByUsernameAndPassword(String username, String password);
    ResponseEntity<?> validacionBloqueo(String username);

    ResponseEntity<?> validacionSuspender(String codigo,String rol);
}