package com.na.backend.service.impl;

import com.na.backend.dto.RevisionSuspencionDTO;
import com.na.backend.model.RevisionSuspension;
import com.na.backend.model.Suspensiones;
import com.na.backend.repository.RevisionSuspensionRepository;
import com.na.backend.repository.SuspensionesRepository;
import com.na.backend.service.RevisionSuspensionService;
import com.na.backend.validator.Secuencia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class RevisionSuspensionServiceImpl implements RevisionSuspensionService {

    @Autowired
    private RevisionSuspensionRepository revisionSuspensionRepository;

    @Autowired
    private SuspensionesRepository suspensionesRepository;


    @Override
    public String obtenerUltimoCodigo() {
        return revisionSuspensionRepository.obtenerUltimoCodigo();
    }

    @Override
    public List<RevisionSuspension> listarRevision() {
        return revisionSuspensionRepository.findAll();
    }

    @Override
    public RevisionSuspension registar(RevisionSuspencionDTO revisionSuspencionDTO) {
   try{
       String detalle = String.format("Su cuenta ha sido suspendida debido a , lo cual constituye una infracción de las normas de nuestra comunidad. Le invitamos a revisar nuestras políticas para mayor información.");

        String codigoUsuario = suspensionesRepository.obtenerUltimoCodigo();
        String ultimoCodigo = Secuencia.incrementarSecuencia(codigoUsuario);
        Suspensiones suspensionesEntity=new Suspensiones();
        suspensionesEntity.setCodigo(ultimoCodigo);
        suspensionesEntity.setFecha_suspension(revisionSuspencionDTO.getFechaSuspension());
        suspensionesEntity.setFecha_expiracion(LocalDate.now().plusMonths(3));

        suspensionesEntity.setDetalle_motivo(detalle);
        suspensionesEntity.setMotivo_corto(revisionSuspencionDTO.getMotivo());
        suspensionesEntity.setHora_suspension(LocalTime.now());
        suspensionesEntity.setUsuario(revisionSuspencionDTO.getCodigo());

suspensionesRepository.save(suspensionesEntity);


        String codigoR = obtenerUltimoCodigo();
        String ultimoCodigoE = Secuencia.incrementarSecuencia(codigoR);
        RevisionSuspension revisionSuspension = new RevisionSuspension();
        revisionSuspension.setCodigo(ultimoCodigoE);
        revisionSuspension.setModerador(revisionSuspencionDTO.getModerador());
        revisionSuspension.setEstado_revision("PENDIENTE");
        revisionSuspension.setSuspensiones(ultimoCodigo);
        return revisionSuspensionRepository.save(revisionSuspension);}
   catch (Exception e) {
       e.printStackTrace();
       throw new RuntimeException(e);
   }
    }


}
