package com.na.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Moderador;

@Repository
public interface ModeradorRepository extends JpaRepository<Moderador, String> {

    @Query(value = "SELECT MAX(mod_codigo) FROM Moderador", nativeQuery = true)
    String obtenerUltimoCodigoNormal();

    @Query(value = "SELECT * FROM moderador WHERE mod_estado = :estado", nativeQuery = true)
    List<Moderador> listarModeradoresPorEstado(@Param("estado") String estado);

    @Query(value = "SELECT * FROM moderador WHERE mod_estado = 'ACTIVO' ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Moderador obtenerModeradorActivoAleatorio();
}


