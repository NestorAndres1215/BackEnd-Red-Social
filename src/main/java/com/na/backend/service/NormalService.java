package com.na.backend.service;



import com.na.backend.dto.NormalDTO;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;
import com.na.backend.model.Normal;

import java.util.List;
import java.util.Optional;

public interface NormalService {
    List<Normal> obtenerNormalesPorUsuarioActivo(String username);
    List<Normal> listarUsuarioNormalActivos();

    List<Normal> listarUsuarioNormalDesactivos();

    List<Normal> listarUsuarioNormalSuspendidos();

    List<Normal> listarUsuarioNormalInhabilitados();

    Normal Registro(NormalDTO normalDTO) throws Exception;

    Normal Actualizar(NormalDTO normalDTO);

    String obtenerUltimoCodigoUsuario();

    String ObtenerUltimoCodigoNormal();

    boolean ExistePorEmail(String email);

    boolean ExistePorTelefono(String telefono);

    Normal SuspenderUsuario(String usuarioCodigo);
    Normal DesactivarUsuario(String usuarioCodigo);

    Login ActivarUsuario(String usuarioCodigo);

    Login BloquearUsuario(String usuarioCodigo);

    Optional<Normal> findById(String codigo);

}
