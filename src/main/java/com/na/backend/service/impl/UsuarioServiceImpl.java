package com.na.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.model.*;
import com.na.backend.service.*;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoginRepository loginRepository;
    private final NormalService normalService;
    private final AdminService adminService;
    private final ModeradorService moderadorService;
    private final EmailService emailService;
    private final RevisionSuspensionService revisionSuspensionService;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder,
            LoginRepository loginRepository, AdminService adminService, NormalService normalService,
            ModeradorService moderadorService, EmailService emailService,
            RevisionSuspensionService revisionSuspensionService) {
        this.usuarioRepository = usuarioRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loginRepository = loginRepository;
        this.adminService = adminService;
        this.normalService = normalService;
        this.moderadorService = moderadorService;
        this.emailService = emailService;
        this.revisionSuspensionService = revisionSuspensionService;
    }

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
    public Login validacionBloqueo(String username) {

        Optional<Usuario> optionalUsuario = usuarioRepository.findByUsername(username).stream().findFirst();

        if (optionalUsuario.isEmpty()) {
            throw new EntityNotFoundException("Usuario no encontrado");
        }
        Usuario usuario = optionalUsuario.get();
        String userRole = usuario.getRol().getNombre().toUpperCase();

        switch (userRole) {
            case "ADMIN":
                return adminService.BloquearUsuario(username);
            case "MODERADOR":
                return null;
            case "NORMAL":
                return normalService.BloquearUsuario(username);
            default:
                throw new IllegalArgumentException("Rol no reconocido: " + userRole);
        }
    }

    @Override
    public Object validacionSuspender(String codigo, String rol) throws MessagingException {

        String asunto = "Tu cuenta ha sido suspendida temporalmente";
        String motivo = "Hemos detectado que tu cuenta ha incumplido una o más normas establecidas...";

        Moderador moderador = moderadorService.obtenerModeradorAleatorioActivo();

        RevisionSuspencionRequestDTO revisionSuspencionDTO = new RevisionSuspencionRequestDTO();
        revisionSuspencionDTO.setCodigo(codigo);
        revisionSuspencionDTO.setAsunto(asunto);
        revisionSuspencionDTO.setModerador(moderador.getCodigo());
        revisionSuspencionDTO.setMotivo(motivo);
        revisionSuspencionDTO.setFechaSuspension(LocalDate.now());

        if ("ADMIN".equalsIgnoreCase(rol)) {
            Optional<Admin> adminOptional = adminService.findById(codigo);
            Admin admin = adminOptional.get();
            String correo = admin.getCorreo();
            String usuario = admin.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            revisionSuspencionDTO.setCodigo(admin.getUsuario().getCodigo());
            revisionSuspensionService.registar(revisionSuspencionDTO);
            emailService.enviarCorreoSuspencion(revisionSuspencionDTO);
            return normalService.SuspenderUsuario(codigo);

        } else if ("MODERADOR".equalsIgnoreCase(rol)) {
            Optional<Moderador> moderadorOptional = moderadorService.findById(codigo);
            Moderador moderadorEntity = moderadorOptional.get();
            String correo = moderadorEntity.getCorreo();
            String usuario = moderadorEntity.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            revisionSuspencionDTO.setCodigo(moderadorEntity.getUsuario().getCodigo());

            revisionSuspensionService.registar(revisionSuspencionDTO);
            emailService.enviarCorreoSuspencion(revisionSuspencionDTO);
            return normalService.SuspenderUsuario(codigo);

        } else if ("NORMAL".equalsIgnoreCase(rol)) {
            Optional<Normal> normalOptional = normalService.findById(codigo);
            Normal normalEntity = normalOptional.get();
            String correo = normalEntity.getCorreo();
            String usuario = normalEntity.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            revisionSuspencionDTO.setCodigo(normalEntity.getUsuario().getCodigo());

            revisionSuspensionService.registar(revisionSuspencionDTO);
            emailService.enviarCorreoSuspencion(revisionSuspencionDTO);
            return normalService.SuspenderUsuario(codigo);
        } else {
            // Rol no permitido
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permisos para suspender usuarios.");
        }

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
