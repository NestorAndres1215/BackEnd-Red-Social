package com.na.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.na.backend.model.Normal;

@Repository
public interface NormalRepository extends JpaRepository<Normal, String> {

    @Query(value = "SELECT MAX(no_codigo) FROM Normal", nativeQuery = true)
    String obtenerUltimoCodigoNormal();

    @Query(value = "SELECT * FROM normal WHERE no_estado = 'ACTIVADO'", nativeQuery = true)
    List<Normal> listarNormalesActivados();

    @Query(value = "SELECT * FROM normal WHERE no_estado = 'INACTIVO'", nativeQuery = true)
    List<Normal> listarNormalesInactivos();

    @Query(value = "SELECT * FROM normal WHERE no_estado = 'SUSPENDIDO'", nativeQuery = true)
    List<Normal> listarNormalesSuspendidos();

    // Correo Existente
    boolean existsByCorreo(String correo);

    // Telefono Existente
    boolean existsByTelefono(String telefono);
}
