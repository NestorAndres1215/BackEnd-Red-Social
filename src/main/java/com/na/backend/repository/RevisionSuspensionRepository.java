package com.na.backend.repository;

import com.na.backend.model.RevisionSuspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RevisionSuspensionRepository extends JpaRepository<RevisionSuspension, String> {

    @Query(value = "SELECT MAX(rs_codigo) FROM Revisiones_Suspension", nativeQuery = true)
    String obtenerUltimoCodigo();

    @Query(value = "CALL sp_listar_revisiones_por_correo(:correo_usuario)", nativeQuery = true)
    List<Object[]> listarPorCorreo(@Param("correo_usuario") String correo);

}
