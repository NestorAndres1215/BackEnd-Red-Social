package com.na.backend.service;


import com.na.backend.dto.request.NormalRequestDTO;
import com.na.backend.model.Login;
import com.na.backend.model.Normal;

import java.util.List;
import java.util.Optional;

public interface NormalService {
    
    List<Normal> obtenerNormalesPorUsuarioActivo(String username);

    List<Normal> obtenerNormalesPorEstado(String estado);

    Normal Registro(NormalRequestDTO normalDTO) throws Exception;

    Normal Actualizar(NormalRequestDTO normalDTO);

    String obtenerUltimoCodigoUsuario();

    String ObtenerUltimoCodigoNormal();

    boolean ExistePorEmail(String email);

    boolean ExistePorTelefono(String telefono);

    Normal SuspenderUsuario(String usuarioCodigo);

    Normal DesactivarUsuario(String usuarioCodigo);

    void ActivarUsuario(String usuarioCodigo);

    Login BloquearUsuario(String usuarioCodigo);

    Optional<Normal> findById(String codigo);

}
