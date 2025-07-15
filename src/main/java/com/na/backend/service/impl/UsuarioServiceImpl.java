package com.na.backend.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.na.backend.dto.RevisionSuspencionDTO;
import com.na.backend.model.*;
import com.na.backend.service.*;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.na.backend.repository.UsuarioRepository;

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

    @Lazy
    @Autowired
    private ModeradorService moderadorService;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private SuspencionesService suspencionesService;

    @Autowired
    private RevisionSuspensionService revisionSuspensionService;



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

    @Override
    public ResponseEntity<?> validacionSuspender(String codigo, String rol)  {
        String asunto = "Tu cuenta ha sido suspendida temporalmente";
        String motivo = "Hemos detectado que tu cuenta ha incumplido una o más normas establecidas en nuestros Términos y Condiciones. Por esta razón, se ha procedido con la suspensión temporal del acceso.";
        Moderador moderador =moderadorService.obtenerModeradorAleatorioActivo();

        RevisionSuspencionDTO revisionSuspencionDTO = new RevisionSuspencionDTO();
        revisionSuspencionDTO.setCodigo(codigo);
        revisionSuspencionDTO.setAsunto(asunto);
        revisionSuspencionDTO.setModerador(moderador.getCodigo());
        revisionSuspencionDTO.setMotivo(motivo);
revisionSuspencionDTO.setFechaSuspension(LocalDate.now());

        if ("ADMIN".equalsIgnoreCase(rol)) {
            Optional<Admin> adminOptional = adminService.findById(codigo);
            Admin admin = adminOptional.get();
            String correo=admin.getCorreo();
            String usuario=admin.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            enviarCorreoDisculpas(revisionSuspencionDTO);
            revisionSuspensionService.registar(revisionSuspencionDTO);
            return ResponseEntity.ok(adminService.SuspenderUsuario(codigo));

        } else if ("MODERADOR".equalsIgnoreCase(rol)) {
            Optional<Moderador> moderadorOptional = moderadorService.findById(codigo);
            Moderador moderadorEntity = moderadorOptional.get();
            String correo=moderadorEntity.getCorreo();
            String usuario=moderadorEntity.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            enviarCorreoDisculpas(revisionSuspencionDTO);
            revisionSuspensionService.registar(revisionSuspencionDTO);
            return ResponseEntity.ok(moderadorService.SuspenderUsuario(codigo));

        }else if ("NORMAL".equalsIgnoreCase(rol)) {
            Optional<Normal> normalOptional = normalService.findById(codigo);
            Normal normalEntity = normalOptional.get();
            String correo=normalEntity.getCorreo();
            String usuario=normalEntity.getUsuario().getUsername();
            revisionSuspencionDTO.setCorreo(correo);
            revisionSuspencionDTO.setUsuario(usuario);
            enviarCorreoDisculpas(revisionSuspencionDTO);
            return ResponseEntity.ok(normalService.SuspenderUsuario(codigo));
        }
        else {
            // Rol no permitido
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("No tienes permisos para suspender usuarios.");
        }
    }



    private void enviarCorreoDisculpas(RevisionSuspencionDTO revisionSuspencionDTO) {

        try {
            String destinatario = revisionSuspencionDTO.getCorreo();
            String asunto = revisionSuspencionDTO.getAsunto(); // Ej: "Tu cuenta ha sido suspendida"
            String usuario = revisionSuspencionDTO.getUsuario();
            LocalDate fecha = revisionSuspencionDTO.getFechaSuspension();
            String motivo = revisionSuspencionDTO.getMotivo(); // Si tienes un campo motivo

            String contenidoHtml = """
        <html>
        <body style="font-family: Arial, sans-serif; line-height: 1.6; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh;">
            <div style="background-color: #fff; padding: 20px; border: 1px solid #ccc; width: 80%%; max-width: 600px; text-align: center; box-shadow: 0 0 10px rgba(0,0,0,0.1);">

                <h2 style="color: #c0392b;">Hola, %s</h2>
                <p style="color: #7f8c8d;">
                    Te informamos que tu cuenta en <strong>Social Like</strong> ha sido <strong>suspendida temporalmente</strong>.
                </p>
                <p style="color: #7f8c8d;">
                    <strong>Motivo:</strong> %s<br>
                    <strong>Fecha de suspensión:</strong> %s
                </p>
                <p style="color: #7f8c8d;">
                    Esta acción se ha tomado en base a nuestros Términos y Condiciones. Si consideras que esto es un error, puedes comunicarte con el soporte para iniciar un proceso de revisión.
                </p>
                <p style="color: #7f8c8d;">
                    Gracias,<br>
                    El equipo de Social Like
                </p>
                <p style="font-size: 12px; color: #7f8c8d;">
                    No respondas a este correo. Este mensaje fue generado automáticamente.
                </p>
            </div>
        </body>
        </html>
        """.formatted(usuario, motivo, fecha.toString());

            MimeMessage mensaje = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
            helper.setTo(destinatario);
            helper.setSubject(asunto);
            helper.setText(contenidoHtml, true); // true = contenido HTML

            javaMailSender.send(mensaje);

        } catch (Exception e) {
            throw new RuntimeException("Error al enviar el correo de suspensión: " + e.getMessage(), e);
        }
    }

}

