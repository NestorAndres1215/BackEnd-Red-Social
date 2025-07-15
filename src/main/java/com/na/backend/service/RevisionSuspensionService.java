package com.na.backend.service;

import com.na.backend.dto.RevisionSuspencionDTO;
import com.na.backend.model.RevisionSuspension;

import java.util.List;

public interface RevisionSuspensionService {

    String obtenerUltimoCodigo();
    List<RevisionSuspension> listarRevision();
    RevisionSuspension registar (RevisionSuspencionDTO revisionSuspencionDTO);
}


