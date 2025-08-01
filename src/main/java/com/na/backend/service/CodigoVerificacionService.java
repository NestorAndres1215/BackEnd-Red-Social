package com.na.backend.service;

import com.na.backend.dto.request.CodigoVerificacioRequestDTO;
import com.na.backend.dto.request.VerificacionRequestDTO;
import com.na.backend.model.CodigoVerificacion;
import jakarta.mail.MessagingException;

public interface CodigoVerificacionService {

    CodigoVerificacion generarYGuardarCodigo(VerificacionRequestDTO verificacionDTO) throws MessagingException;

    String ObtenerUltimoCodigo();

    CodigoVerificacion verificar(CodigoVerificacioRequestDTO codigoVerificacioDTO);

}
