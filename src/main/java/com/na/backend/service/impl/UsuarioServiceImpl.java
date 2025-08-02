package com.na.backend.service.impl;

import java.util.List;
import java.util.Optional;

import com.na.backend.model.*;
import com.na.backend.service.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private LoginRepository loginRepository;

    @Override
    public String obtenerUltimoCodigoUsuario() {
        return usuarioRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public Optional<Usuario> listarCodigo(String codigo) {
        return usuarioRepository.findById(codigo);
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
    public Usuario regUsuario(String codigo, String username, String password, String correo, String telefono,
            String rol) {

        Usuario usuario = new Usuario();
        usuario.setCodigo(codigo);
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEstado("ACTIVO");
        usuario.setTelefono(telefono);
        usuario.setRol(rol);
        usuario.setCorreo(correo);

        return usuarioRepository.save(usuario);

    }

    @Override
    public Login regLogin(String codigo, String username, String password, String correo, String telefono, String rol) {

        Login login = new Login();
        login.setCodigo(codigo);
        login.setUsername(username);
        login.setPassword(bCryptPasswordEncoder.encode(password));
        login.setEstado("ACTIVO");
        login.setRol(rol);
        login.setCorreo(correo);
        login.setTelefono(telefono);

        return loginRepository.save(login);

    }

    @Override
    public Usuario actUsuario(String codigo, String username, String password, String correo, String telefono,
            String rol) {

        Usuario usuario = usuarioRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException(
                        "El usuario con código " + codigo + " no existe."));

        usuario.setUsername(username);
        usuario.setPassword(bCryptPasswordEncoder.encode(password));
        usuario.setCorreo(correo);
        usuario.setEstado("ACTIVO");
        usuario.setTelefono(telefono);
        usuario.setRol(rol);

        return usuarioRepository.save(usuario);

    }

    @Override
    public Login actLogin(String codigo, String username, String password, String correo, String telefono, String rol) {

        Login login = loginRepository.findById(codigo)
                .orElseThrow(() -> new RuntimeException(
                        "El login con código " + codigo + " no existe."));

        login.setUsername(username);
        login.setPassword(bCryptPasswordEncoder.encode(password));
        login.setCorreo(correo);
        login.setTelefono(telefono);
        login.setEstado(rol);
        login.setEstado("ACTIVO");

        return loginRepository.save(login);
    }

}
