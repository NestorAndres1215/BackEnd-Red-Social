package com.na.backend.repository;

import com.na.backend.model.Suspensiones;
import com.na.backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SuspensionesRepository extends JpaRepository<Suspensiones, String> {

    @Query(value = "SELECT MAX(sus_codigo) FROM Suspensiones", nativeQuery = true)
    String obtenerUltimoCodigo();

    List<Suspensiones> findByUsuario(Usuario usuario);

}
