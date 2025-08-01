package com.na.backend.service.impl;

import com.na.backend.dto.request.CodigoVerificacioRequestDTO;
import com.na.backend.dto.request.VerificacionRequestDTO;
import com.na.backend.model.CodigoVerificacion;
import com.na.backend.model.Login;
import com.na.backend.repository.CodigoVerificacionRepository;
import com.na.backend.repository.LoginRepository;
import com.na.backend.service.AdminService;
import com.na.backend.service.CodigoVerificacionService;
import com.na.backend.service.EmailService;
import com.na.backend.service.NormalService;
import com.na.backend.util.CodigoUtil;
import com.na.backend.util.SecuenciaUtil;

import jakarta.mail.MessagingException;
import jakarta.persistence.EntityNotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class CodigoVerificacionServiceImpl implements CodigoVerificacionService {

    @Autowired
    private CodigoVerificacionRepository codigoVerificacionRepository;
    @Autowired
    private AdminService adminService;
    @Autowired
    private LoginRepository loginRepository;
    @Autowired
    private NormalService normalService;
    @Autowired
    private EmailService emailService;

    @Override
    public CodigoVerificacion generarYGuardarCodigo(VerificacionRequestDTO verificacionDTO) throws MessagingException {
        String codigoUsuario = codigoVerificacionRepository.obtenerUltimoCodigo();
        String ultimoCodigo = SecuenciaUtil.generarSiguienteCodigo(codigoUsuario);
        String codigo = CodigoUtil.generarCodigo();

        CodigoVerificacion verificacion = codigoVerificacionRepository.findByCorreo(verificacionDTO.getCorreo())
                .orElse(new CodigoVerificacion());

        if (verificacion.getCodigo() == null) {
            verificacion.setCodigo(ultimoCodigo);
        }

        verificacion.setCorreo(verificacionDTO.getCorreo());
        verificacion.setUsuario(verificacionDTO.getUsuario());
        verificacion.setCodigo_verificacion(codigo);
        verificacion.setFecha_generacion(LocalDate.now());
        verificacion.setHora_generacion(LocalTime.now());
        emailService.enviarCorreoDisculpas(verificacion);

        return codigoVerificacionRepository.save(verificacion);

    }

    @Override
    public String ObtenerUltimoCodigo() {
        return codigoVerificacionRepository.obtenerUltimoCodigo();

    }

    @Override
    public CodigoVerificacion verificar(CodigoVerificacioRequestDTO codigoVerificacioDTO) {

        CodigoVerificacion verificacion = codigoVerificacionRepository.findByCorreo(codigoVerificacioDTO.getCorreo())
                .orElseThrow(() -> new EntityNotFoundException("No se encontró un código para el correo ingresado."));

        if (!verificacion.getCodigo_verificacion().equals(codigoVerificacioDTO.getCodigo())) {
            throw new IllegalArgumentException("El código ingresado no es correcto.");
        }
        verificacion.setCodigo_verificacion("");
        Login login = loginRepository.findByCorreo(codigoVerificacioDTO.getCorreo())
                .orElseThrow(() -> new EntityNotFoundException("No existe login asociado al correo."));
        validacionRoles(login);
        return null;

    }

    private void validacionRoles(Login login) {
        String rol = login.getRol();
        String codigo = login.getCodigo();

        switch (rol.toUpperCase()) {
            case "ADMIN":
                adminService.ActivarUsuario(codigo);
                break;

            case "NORMAL":
                normalService.ActivarUsuario(codigo);
                break;

            case "MODERADOR":

                break;

            default:

                throw new UnsupportedOperationException("Rol no soportado: " + rol);
        }
    }

}
