package com.na.backend.delegator;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;
import com.na.backend.model.Moderador;
import com.na.backend.model.Normal;
import com.na.backend.model.Usuario;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.AdminService;
import com.na.backend.service.EmailService;
import com.na.backend.service.ModeradorService;
import com.na.backend.service.NormalService;
import com.na.backend.service.RevisionSuspensionService;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioDelegator {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private NormalService normalService;
    @Autowired
    private ModeradorService moderadorService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private RevisionSuspensionService revisionSuspensionService;

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

}
