package com.na.backend.service.impl;

import com.na.backend.dto.CodigoVerificacioDTO;
import com.na.backend.dto.VerificacionDTO;
import com.na.backend.model.CodigoVerificacion;
import com.na.backend.model.Login;
import com.na.backend.repository.CodigoVerificacionRepository;
import com.na.backend.repository.LoginRepository;
import com.na.backend.service.AdminService;
import com.na.backend.service.CodigoVerificacionService;
import com.na.backend.service.NormalService;
import com.na.backend.validator.Secuencia;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class CodigoVerificacionServiceImpl implements CodigoVerificacionService {

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;
    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private NormalService normalService;

    @Override
    public CodigoVerificacion generarYGuardarCodigo(VerificacionDTO verificacionDTO) throws MessagingException {
        String codigoUsuario = codigoVerificacionRepository.obtenerUltimoCodigo();
        String ultimoCodigo = Secuencia.incrementarSecuencia(codigoUsuario);
        String codigo = generarCodigo();

        CodigoVerificacion verificacion = codigoVerificacionRepository.findByCorreo(verificacionDTO.getCorreo())
                .orElse(new CodigoVerificacion());

        if(verificacion.getCodigo()==null) {
        verificacion.setCodigo(ultimoCodigo);
        }

        verificacion.setCorreo(verificacionDTO.getCorreo());
        verificacion.setUsuario(verificacionDTO.getUsuario());
        verificacion.setCodigo_verificacion(codigo);
        verificacion.setFecha_generacion(LocalDate.now());
        verificacion.setHora_generacion(LocalTime.now());
        enviarCorreoDisculpas(verificacion);

        return codigoVerificacionRepository.save(verificacion);
    }

    private void enviarCorreoDisculpas(CodigoVerificacion verificacion) throws MessagingException, MessagingException {
      try{  String destinatario = verificacion.getCorreo();
        String asunto = "Código de verificación en dos pasos";
        String codigo = verificacion.getCodigo_verificacion();
        String usuario = verificacion.getUsuario();

        String contenidoHtml = """
        <html>
        <body style="font-family: Arial, sans-serif; line-height: 1.6; background-color: #f5f5f5; margin: 0; padding: 0; display: flex; justify-content: center; align-items: center; height: 100vh;">
            <div style="background-color: #fff; padding: 20px; border: 1px solid #ccc; width: 80%%; max-width: 600px; text-align: center; box-shadow: 0 0 10px rgba(0,0,0,0.1);">
  
                <h2 style="color: #2c3e50;">Hola, %s</h2>
                <p style="color: #7f8c8d;">
                    Hemos recibido una solicitud de verificación para tu cuenta de Social Like.<br><br>
                    <strong style="font-size: 18px; color: #2c3e50;">Tu código de verificación es:</strong><br>
                    <span style="font-size: 24px; font-weight: bold; color: #2980b9;">%s</span><br><br>
                    Este código es válido por 15 minutos. Por favor, introdúcelo en la pantalla correspondiente para completar el proceso de verificación.
                </p>
                <p style="color: #7f8c8d;">
                    <strong>IMPORTANTE:</strong> No compartas este código con nadie. Social Like nunca te pedirá este código por mensaje, correo ni llamadas. Compartirlo puede poner en riesgo tu cuenta.
                </p>
                <p style="color: #7f8c8d;">Gracias,<br>El equipo de Social Like</p>
                <p style="font-size: 12px; color: #7f8c8d;">
                    No respondas a este correo. Este mensaje fue generado automáticamente debido a un intento de acceso desde un nuevo dispositivo. Si no realizaste esta solicitud, te recomendamos cambiar tu contraseña de inmediato.
                </p>
            </div>
        </body>
        </html>
        """.formatted(usuario, codigo);

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true); // true → HTML

        javaMailSender.send(mensaje);
      } catch (Exception e) {
          e.printStackTrace();
      }
    }

    @Override
    public String ObtenerUltimoCodigo() {
        return codigoVerificacionRepository.obtenerUltimoCodigo();
    }

    @Override
    public ResponseEntity<?> verificar(CodigoVerificacioDTO codigoVerificacioDTO) {
        Optional<CodigoVerificacion> codigoVerificacion =
                codigoVerificacionRepository.findByCorreo(codigoVerificacioDTO.getCorreo());


        if (!codigoVerificacion.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("No se encontró un código de verificación para el correo ingresado.");
        }

        CodigoVerificacion codigoVerificacion1 = codigoVerificacion.get();

        if (!codigoVerificacion1.getCodigo_verificacion().equals(codigoVerificacioDTO.getCodigo())) {
            return ResponseEntity
                    .ok("El código ingresado no coincide con el enviado.");
        }
        System.out.print("subiendo");

        codigoVerificacion1.setCodigo_verificacion("");
        Optional<Login> login =
                loginRepository.findByCorreo(codigoVerificacioDTO.getCorreo());
        Login longin1 = login.get();
        validacionRoles(longin1);


        return ResponseEntity.ok(codigoVerificacion1);
    }



    private String generarCodigo() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }
    private void validacionRoles(Login login){
        String rol = login.getRol();
 String codigo=login.getCodigo();
        if (rol.equalsIgnoreCase("ADMIN")) {
            adminService.ActivarUsuario(codigo);

        } else if (rol.equalsIgnoreCase("NORMAL")) {
           normalService.ActivarUsuario(codigo);

        } else if (rol.equalsIgnoreCase("MODERADOR")) {
            System.out.println("Es un moderador");

        } else {
            System.out.println("Rol no válido");
            throw new IllegalArgumentException("Rol no reconocido: " + rol);
        }
    }
}
