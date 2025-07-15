package com.na.backend.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.na.backend.dto.AdminDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;
import org.springframework.http.ResponseEntity;

public interface AdminService {
    Optional<Admin> findById(String codigo);

    List<Admin> listarAdminsActivados();

    List<Admin> listarAdminsDesactivado();

    Admin Registro(AdminDTO adminDTO) throws Exception;

    Admin Actualizar(AdminDTO adminDTO);

    String obtenerUltimoCodigoUsuario();

    String ObtenerUltimoCodigoAdmin();

    boolean ExistePorEmail(String email);

    boolean ExistePorTelefono(String telefono);

    Admin DesactivarUsuario(String usuarioCodigo);

    Admin SuspenderUsuario(String usuarioCodigo);

    Login ActivarUsuario(String usuarioCodigo);

    Login BloquearUsuario(String usuarioCodigo);

    List<Admin> getAdminsByUsuarioCodigo(String usuarioCodigo);

    List<Map<String, Object>> obtenerAdminsExcluyendoUsuario(String username);


}
