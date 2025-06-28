package com.na.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Moderador;

@Repository
public interface ModeradorRepository extends JpaRepository<Moderador, String> {

    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerUltimoCodigoNormal();

    @Query(value = "SELECT * FROM moderador WHERE mod_estado = 'ACTIVADO'", nativeQuery = true)
    List<Moderador> listarModeradoresActivados();

    @Query(value = "SELECT * FROM moderador WHERE mod_estado = 'INACTIVO'", nativeQuery = true)
    List<Moderador> listarModeradoresInactivos();

    @Query(value = "SELECT * FROM moderador WHERE mod_estado = 'SUSPENDIDO'", nativeQuery = true)
    List<Moderador> listarModeradoresSuspendidos();

    // Correo Existente
    boolean existsByCorreo(String correo);

    // Telefono Existente
    boolean existsByTelefono(String telefono);
}
