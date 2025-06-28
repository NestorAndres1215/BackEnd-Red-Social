package com.na.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, String> {

    @Query(value = "SELECT MAX(ad_codigo) FROM Admin", nativeQuery = true)
    String obtenerUltimoCodigoAdmin();

    @Query(value = "SELECT * FROM admin WHERE ad_estado = 'ACTIVADO'", nativeQuery = true)
    List<Admin> listarAdminsActivados();

    @Query(value = "SELECT * FROM admin WHERE ad_estado = 'INACTIVO'", nativeQuery = true)
    List<Admin> listarAdminsInactivos();

    // Correo Existente
    boolean existsByCorreo(String correo);

    // Telefono Existente
    boolean existsByTelefono(String telefono);

    List<Admin> findByUsuario_Codigo(String usuarioCodigo);
}
