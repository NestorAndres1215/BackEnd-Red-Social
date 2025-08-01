package com.na.backend.service;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.model.CodigoVerificacion;

import jakarta.mail.MessagingException;

public interface EmailService {

    void enviarCorreoDisculpas(CodigoVerificacion verificacion) throws MessagingException;
    void enviarCorreoSuspencion(RevisionSuspencionRequestDTO revisionSuspencionRequestDTO) throws MessagingException;
    
}
