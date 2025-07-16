package com.na.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {

    List<Usuario> findByUsername(String username);

    boolean existsByUsername(String username);
    
    boolean existsByCorreo(String correo);

    boolean existsByTelefono(String telefono);

    boolean existsByUsernameAndPassword(String username, String password);

    @Query(value = "SELECT MAX(us_codigo) FROM Usuario", nativeQuery = true)
    String obtenerUltimoCodigo();

}