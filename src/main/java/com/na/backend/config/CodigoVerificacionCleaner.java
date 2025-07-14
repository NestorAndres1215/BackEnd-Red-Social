package com.na.backend.config;

import com.na.backend.model.CodigoVerificacion;
import com.na.backend.model.Login;
import com.na.backend.repository.CodigoVerificacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class CodigoVerificacionCleaner {

    @Autowired
    private CodigoVerificacionRepository codigoRepo;

    @Scheduled(fixedRate = 60000) // cada 60 segundos
    public void eliminarCodigosExpirados() {
        List<CodigoVerificacion> codigos = codigoRepo.findAll();

        for (CodigoVerificacion codigo : codigos) {
            LocalDateTime fechaHora = LocalDateTime.of(
                    codigo.getFecha_generacion(),
                    codigo.getHora_generacion()
            );

            if (fechaHora.plusMinutes(15).isBefore(LocalDateTime.now())) {
               codigo.setCodigo_verificacion("");
                codigoRepo.save(codigo);
            }
        }
    }
}