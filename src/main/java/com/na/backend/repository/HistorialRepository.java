package com.na.backend.repository;

import com.na.backend.model.Historial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface HistorialRepository extends JpaRepository<Historial, String> {

    @Query(value = "SELECT MAX(hu_codigo) FROM historialusuario", nativeQuery = true)
    String obtenerUltimoCodigo();

    @Query(value = "CALL sp_listar_historial_por_usuario(:username, :estado)", nativeQuery = true)
     List<Object[]> listarHistorialPorUsuario(
            @Param("username") String username,
            @Param("estado") String estado);

}
