package com.na.backend.service.impl;

import com.na.backend.model.Admin;
import com.na.backend.model.Login;
import com.na.backend.model.Moderador;
import com.na.backend.model.Usuario;
import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.ModeradorRepository;
import com.na.backend.repository.NormalRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.ModeradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ModeradorServiceImpl implements ModeradorService {

    @Autowired
    private ModeradorRepository moderadorRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Moderador obtenerModeradorAleatorioActivo() {
        return moderadorRepository.obtenerModeradorActivoAleatorio();
    }

    @Override
    public Optional<Moderador> findById(String codigo) {
        return moderadorRepository.findById(codigo);
    }

    @Override
    public Moderador SuspenderUsuario(String usuarioCodigo) {
        Optional<Moderador> moderadorOptional = moderadorRepository.findById(usuarioCodigo);
        System.out.print(moderadorOptional);

        if (moderadorOptional.isPresent()) {
            Moderador moderador = moderadorOptional.get();

            Optional<Usuario> usuario = usuarioRepository.findById(moderador.getUsuario().getCodigo());
            if (usuario.isPresent()) {
                Usuario usuarioEntity = usuario.get();
                usuarioEntity.setEstado("SUSPENDIDO");
                usuarioRepository.save(usuarioEntity);
            }

            Optional<Login> login = loginRepository.findById(moderador.getUsuario().getCodigo());
            if (login.isPresent()) {
                Login loginEntity = login.get();
                loginEntity.setEstado("SUSPENDIDO");
                loginRepository.save(loginEntity);
            }

            moderador.setEstado("SUSPENDIDO");
            return moderadorRepository.save(moderador);
        } else {
            throw new IllegalArgumentException("El código de usuario no existe: " + usuarioCodigo);
        }

    }
}
