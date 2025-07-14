package com.na.backend.service;

import com.na.backend.dto.CodigoVerificacioDTO;
import com.na.backend.dto.VerificacionDTO;
import com.na.backend.model.CodigoVerificacion;
import com.na.backend.model.Login;
import jakarta.mail.MessagingException;
import org.springframework.http.ResponseEntity;

public interface CodigoVerificacionService {

    CodigoVerificacion generarYGuardarCodigo(VerificacionDTO verificacionDTO) throws MessagingException;

    String ObtenerUltimoCodigo();
    ResponseEntity<?> verificar(CodigoVerificacioDTO codigoVerificacioDTO);

}
