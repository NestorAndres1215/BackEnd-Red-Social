package com.na.backend.service.impl;

import com.na.backend.dto.request.RevisionSuspencionRequestDTO;
import com.na.backend.dto.response.RevisionResponseDTO;
import com.na.backend.mapper.RevisionMapper;
import com.na.backend.model.RevisionSuspension;
import com.na.backend.model.Suspensiones;
import com.na.backend.repository.RevisionSuspensionRepository;
import com.na.backend.repository.SuspensionesRepository;
import com.na.backend.service.RevisionSuspensionService;
import com.na.backend.util.SecuenciaUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RevisionSuspensionServiceImpl implements RevisionSuspensionService {

    @Autowired
    private RevisionSuspensionRepository revisionSuspensionRepository;

    @Autowired
    private SuspensionesRepository suspensionesRepository;

    @Autowired
    private RevisionMapper revisionMapper;

    @Override
    public String obtenerUltimoCodigo() {
        return revisionSuspensionRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<RevisionSuspension> listarRevision() {
        return revisionSuspensionRepository.findAll();
    }

    @Override
    public RevisionSuspension registroSuspenso(RevisionSuspension revisionSuspension) {
        return revisionSuspensionRepository.save(revisionSuspension);
    }

    @Override
    public List<RevisionResponseDTO> revisarSuspencion(String correo) {

        List<Object[]> filas = revisionSuspensionRepository.listarPorCorreo(correo);
        List<RevisionResponseDTO> listar = new ArrayList<>();

        for (Object[] fila : filas) {
            listar.add(revisionMapper.listarRevisionCorreo(fila));
        }

        return listar;
    }

    @Override
    public RevisionSuspension validacionSuspenderCorreo(String correo) {
        List<RevisionResponseDTO> revisiones = revisarSuspencion(correo);

        if (revisiones.isEmpty()) {
            throw new IllegalArgumentException("No se encontraron revisiones para el correo: " + correo);
        }

        RevisionResponseDTO original = revisiones.get(0);

        RevisionSuspension nuevaRevision = new RevisionSuspension();
        nuevaRevision.setCodigo(original.getCodigoRevision());
        nuevaRevision.setModerador(original.getCodigoModerador());
        nuevaRevision.setSuspensiones(original.getCodigoSuspension());
        nuevaRevision.setObservacion("PENDIENTE");
        nuevaRevision.setFecha_revision(LocalDate.now());

        return registroSuspenso(nuevaRevision);
    }

    @Override
    public RevisionSuspension registar(RevisionSuspencionRequestDTO revisionSuspencionDTO) {

        String detalle = String.format(
                "Su cuenta ha sido suspendida debido a , lo cual constituye una infracción de las normas de nuestra comunidad. Le invitamos a revisar nuestras políticas para mayor información.");

        String codigoUsuario = suspensionesRepository.obtenerUltimoCodigo();
        String ultimoCodigo = SecuenciaUtil.generarSiguienteCodigo(codigoUsuario);
        Suspensiones suspensionesEntity = new Suspensiones();
        suspensionesEntity.setCodigo(ultimoCodigo);
        suspensionesEntity.setFecha_suspension(revisionSuspencionDTO.getFechaSuspension());
        suspensionesEntity.setFecha_expiracion(LocalDate.now().plusMonths(3));

        suspensionesEntity.setDetalle_motivo(detalle);
        suspensionesEntity.setMotivo_corto(revisionSuspencionDTO.getMotivo());
        suspensionesEntity.setHora_suspension(LocalTime.now());
        suspensionesEntity.setUsuario(revisionSuspencionDTO.getCodigo());

        suspensionesRepository.save(suspensionesEntity);

        String codigoR = obtenerUltimoCodigo();
        String ultimoCodigoE = SecuenciaUtil.generarSiguienteCodigo(codigoR);
        RevisionSuspension revisionSuspension = new RevisionSuspension();
        revisionSuspension.setCodigo(ultimoCodigoE);
        revisionSuspension.setModerador(revisionSuspencionDTO.getModerador());
        revisionSuspension.setEstado_revision("INICIO");
        revisionSuspension.setSuspensiones(ultimoCodigo);
        return revisionSuspensionRepository.save(revisionSuspension);
    }

}
