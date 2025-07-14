package com.na.backend.repository;

import com.na.backend.model.CodigoVerificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodigoVerificacionRepository extends JpaRepository<CodigoVerificacion,String> {
    Optional<CodigoVerificacion> findByCorreo(String correo);

    @Query(value = "SELECT MAX(cv_codigo) FROM Codigo_Verificacion", nativeQuery = true)
    String obtenerUltimoCodigo();
}
