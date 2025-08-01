package com.na.backend.service.impl;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.model.CodigoVerificacion;
import com.na.backend.service.EmailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void enviarCorreoDisculpas(CodigoVerificacion verificacion) throws MessagingException {

        String destinatario = verificacion.getCorreo();
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
                """
                .formatted(usuario, codigo);
        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true); // true → HTML

        javaMailSender.send(mensaje);
    }

    @Override
    public void enviarCorreoSuspencion(RevisionSuspencionRequestDTO revisionSuspencionRequestDTO)
            throws MessagingException {

        String destinatario = revisionSuspencionRequestDTO.getCorreo();
        String asunto = revisionSuspencionRequestDTO.getAsunto(); // Ej: "Tu cuenta ha sido suspendida"
        String usuario = revisionSuspencionRequestDTO.getUsuario();
        LocalDate fecha = revisionSuspencionRequestDTO.getFechaSuspension();
        String motivo = revisionSuspencionRequestDTO.getMotivo(); // Si tienes un campo motivo

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
                """
                .formatted(usuario, motivo, fecha.toString());

        MimeMessage mensaje = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mensaje, true);
        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(contenidoHtml, true); // true = contenido HTML

        javaMailSender.send(mensaje);

    }

}
