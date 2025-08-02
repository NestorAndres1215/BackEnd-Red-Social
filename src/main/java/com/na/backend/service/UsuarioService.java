package com.na.backend.service;

import java.util.List;
import java.util.Optional;
import com.na.backend.model.Login;
import com.na.backend.model.Usuario;


public interface UsuarioService {

    String obtenerUltimoCodigoUsuario();

    List<Usuario> findByUsername(String username);

    Optional<Usuario> listarCodigo(String codigo);

    boolean usuarioExistePorUsername(String username);

    boolean existsByUsernameAndPassword(String username, String password);

 

    Usuario regUsuario(String codigo, String username, String password, String correo, String telefono, String rol);

    Login regLogin(String codigo, String username, String password, String correo, String telefono, String rol);

    Usuario actUsuario(String codigo, String username, String password, String correo, String telefono, String rol);

    Login actLogin(String codigo, String username, String password, String correo, String telefono, String rol);
}