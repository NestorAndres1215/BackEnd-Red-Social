package com.na.backend.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.na.backend.model.Normal;

@Repository
public interface NormalRepository extends JpaRepository<Normal, String> {

    @Query(value = "SELECT MAX(no_codigo) FROM Normal", nativeQuery = true)
    String obtenerUltimoCodigoNormal();

    @Query(value = "SELECT * FROM normal WHERE no_estado = :estado", nativeQuery = true)
    List<Normal> listarNormalesPorEstado(@Param("estado") String estado);

    @Query(value = "SELECT COUNT(*) FROM normal WHERE no_estado = 'ACTIVO'", nativeQuery = true)
    long contarUsuariosNormales();

    @Query(value = """
            SELECT *
            FROM normal n
            JOIN usuario u ON n.no_usuario = u.us_codigo
            WHERE u.us_usuario = :username AND n.no_estado = 'ACTIVO'
            """, nativeQuery = true)
    List<Normal> buscarPorUsuarioYEstadoActivo(@Param("username") String username);

    Optional<Normal> findByCorreo(String correo);
}
