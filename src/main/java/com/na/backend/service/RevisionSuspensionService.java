package com.na.backend.service;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.dto.response.RevisionResponseDTO;
import com.na.backend.model.RevisionSuspension;
import java.util.List;

public interface RevisionSuspensionService {

    String obtenerUltimoCodigo();
    
  

    List<RevisionSuspension> listarRevision();

   RevisionSuspension registar(RevisionSuspencionRequestDTO revisionSuspencionDTO);

    RevisionSuspension registroSuspenso(RevisionSuspension revisionSuspension);

    List<RevisionResponseDTO> revisarSuspencion(String correo);

    RevisionSuspension validacionSuspenderCorreo(String correo);
}
