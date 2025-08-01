package com.na.backend.service;

import java.util.List;

import java.util.Optional;

import com.na.backend.dto.request.AdminRequestDTO;
import com.na.backend.dto.response.AdminResponseDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;

public interface AdminService {

  Optional<Admin> findById(String codigo);

  List<Admin> listarAdmins(String estado);

  Admin Registro(AdminRequestDTO adminDTO) throws Exception;

  Admin Actualizar(AdminRequestDTO adminDTO);

  String obtenerUltimoCodigoUsuario();

  String ObtenerUltimoCodigoAdmin();

  boolean ExistePorEmail(String email);

  boolean ExistePorTelefono(String telefono);

  Admin DesactivarUsuario(String usuarioCodigo);

  Admin SuspenderUsuario(String usuarioCodigo);

  Login ActivarUsuario(String usuarioCodigo);

  Login BloquearUsuario(String usuarioCodigo);

  List<Admin> getAdminsByUsuarioCodigo(String usuarioCodigo);

  List<AdminResponseDTO> obtenerAdminsExcluyendoUsuario(String username);

}
