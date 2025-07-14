package com.na.backend.service;

import java.util.List;
import java.util.Map;

import com.na.backend.dto.AdminDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;

public interface AdminService {

    List<Admin> listarAdminsActivados();

    List<Admin> listarAdminsDesactivado();

    Admin Registro(AdminDTO adminDTO) throws Exception;

    Admin Actualizar(AdminDTO adminDTO);

    String obtenerUltimoCodigoUsuario();

    String ObtenerUltimoCodigoAdmin();

    boolean ExistePorEmail(String email);

    boolean ExistePorTelefono(String telefono);

    Admin DesactivarUsuario(String usuarioCodigo);

    Login ActivarUsuario(String usuarioCodigo);

    Login BloquearUsuario(String usuarioCodigo);

    List<Admin> getAdminsByUsuarioCodigo(String usuarioCodigo);

    List<Map<String, Object>> obtenerAdminsExcluyendoUsuario(String username);
}
