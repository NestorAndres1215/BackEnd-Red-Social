package com.na.backend.service.impl;

import com.na.backend.dto.request.NormalRequestDTO;
import com.na.backend.model.Login;
import com.na.backend.model.Normal;
import com.na.backend.model.Usuario;
import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.NormalRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.NormalService;
import com.na.backend.service.UsuarioService;
import com.na.backend.util.SecuenciaUtil;
import com.na.backend.util.ValidacionUtil;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class NormalServiceImpl implements NormalService {

    @Autowired
    private NormalRepository normalRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public List<Normal> obtenerNormalesPorUsuarioActivo(String username) {
        return normalRepository.buscarPorUsuarioYEstadoActivo(username);
    }

    @Override
    public List<Normal> obtenerNormalesPorEstado(String estado) {
        return normalRepository.listarNormalesPorEstado(estado);
    }

    @Override
    public String obtenerUltimoCodigoUsuario() {
        return usuarioRepository.obtenerUltimoCodigo();
    }

    @Override
    public String ObtenerUltimoCodigoNormal() {
        return normalRepository.obtenerUltimoCodigoNormal();
    }

    @Override
    public boolean ExistePorEmail(String email) {
        return usuarioRepository.existsByCorreo(email);
    }

    @Override
    public boolean ExistePorTelefono(String telefono) {
        return usuarioRepository.existsByTelefono(telefono);
    }

    @Override
    public Normal SuspenderUsuario(String usuarioCodigo) {

        Optional<Normal> normalOptional = normalRepository.findById(usuarioCodigo);

        if (normalOptional.isPresent()) {
            Normal normal = normalOptional.get();

            Optional<Usuario> usuario = usuarioRepository.findById(normal.getUsuario().getCodigo());
            if (usuario.isPresent()) {
                Usuario usuarioEntity = usuario.get();
                usuarioEntity.setEstado("SUSPENDIDO");
                usuarioRepository.save(usuarioEntity);
            }

            Optional<Login> login = loginRepository.findById(normal.getUsuario().getCodigo());
            if (login.isPresent()) {
                Login loginEntity = login.get();
                loginEntity.setEstado("SUSPENDIDO");
                loginRepository.save(loginEntity);
            }

            normal.setEstado("SUSPENDIDO");
            return normalRepository.save(normal);
        } else {
            throw new IllegalArgumentException("El código de usuario no existe: " + usuarioCodigo);
        }
    }

    @Override
    public Normal DesactivarUsuario(String usuarioCodigo) {
        return null;
    }

    @Override
    public void ActivarUsuario(String usuarioCodigo) {

        Optional<Login> login = loginRepository.findById(usuarioCodigo);
        if (login.isPresent()) {
            Login loginEntity = login.get();

            Optional<Normal> normal = normalRepository.findByCorreo(login.get().getCorreo());

            if (normal.isPresent()) {
                Normal normalEntity = normal.get();
                normalEntity.setEstado("ACTIVO");
                normalRepository.save(normalEntity);
            }
            Optional<Usuario> usuario = usuarioRepository.findById(usuarioCodigo);
            if (usuario.isPresent()) {

                Usuario usuarioEntity = usuario.get();
                usuarioEntity.setEstado("ACTIVO");
                usuarioRepository.save(usuarioEntity);
            }
            loginEntity.setEstado("ACTIVO");
            loginRepository.save(loginEntity);
        } else {
            throw new IllegalArgumentException("No se pudo activar");
        }
    }

    @Override
    public Login BloquearUsuario(String usuarioCodigo) {
        System.out.print(usuarioCodigo);
        Login login = loginRepository.findByUsername(usuarioCodigo);

        if (login != null) {
            System.out.print(login);
            login.setEstado("BLOQUEADO");
            Optional<Normal> normal = normalRepository.findByCorreo(login.getCorreo());
            System.out.print(normal);
            if (normal.isPresent()) {
                Normal normal1 = normal.get();
                normal1.setEstado("BLOQUEADO");
                normalRepository.save(normal1);
            }
            Optional<Usuario> usuario = usuarioRepository.findById(login.getCodigo());
            if (usuario.isPresent()) {

                Usuario usuarioEntity = usuario.get();
                usuarioEntity.setEstado("BLOQUEADO");
                usuarioRepository.save(usuarioEntity);
            }
            return loginRepository.save(login);
        }

        else {
            throw new IllegalArgumentException("Nose pudo bloquear");
        }

    }

    @Override
    public Optional<Normal> findById(String codigo) {
        return normalRepository.findById(codigo);
    }

    private boolean validarNormal(NormalRequestDTO normalDTO) {

        if (usuarioService.usuarioExistePorUsername(normalDTO.getUsernameNormal())) {
            throw new IllegalArgumentException("USUARIO YA EXISTE");
        }
        if (ExistePorEmail(normalDTO.getCorreoNormal())) {
            throw new IllegalArgumentException("CORREO YA EXISTE");
        }

        if (ExistePorTelefono(normalDTO.getTelefonoNormal())) {
            throw new IllegalArgumentException("TELEFONO YA EXISTE");
        }
        if (!ValidacionUtil.esTelefonoValido(normalDTO.getTelefonoNormal())) {
            throw new IllegalArgumentException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }

        if (!ValidacionUtil.esCorreoValido(normalDTO.getCorreoNormal())) {
            throw new IllegalArgumentException("EL CORREO NO TIENEN FORMATO VALIDO");
        }

        if (normalDTO.getEdadNormal() < 18) {
            throw new IllegalArgumentException("EDAD NO PERMITIDA");
        }
        return ResponseEntity.ok("Validación exitosa").hasBody();
    }

    @Override
    public Normal Registro(NormalRequestDTO normalDTO) throws Exception {
        try {
            validarNormal(normalDTO);
            String codigoUsuario = obtenerUltimoCodigoUsuario();
            String ultimoCodigo = SecuenciaUtil.generarSiguienteCodigo(codigoUsuario);
            String codigoNormal = ObtenerUltimoCodigoNormal();
            String ultimoCodigoNormal = SecuenciaUtil.generarSiguienteCodigo(codigoNormal);

            usuarioService.regUsuario(ultimoCodigo, normalDTO.getUsernameNormal(), normalDTO.getPasswordNormal(),
                    normalDTO.getCorreoNormal(), normalDTO.getTelefonoNormal(), "0003");
            usuarioService.regLogin(ultimoCodigo, normalDTO.getUsernameNormal(), normalDTO.getPasswordNormal(),
                    normalDTO.getCorreoNormal(), normalDTO.getTelefonoNormal(), "NORMAL");

            Normal normal = new Normal();
            normal.setCodigo(ultimoCodigoNormal);
            normal.setNombre(normalDTO.getNombreNormal());
            normal.setApellido(normalDTO.getApellidoNormal());
            normal.setCorreo(normalDTO.getCorreoNormal());
            normal.setTelefono(normalDTO.getTelefonoNormal());
            normal.setEdad(normalDTO.getEdadNormal());
            normal.setFechaNacimiento(normalDTO.getFechaNacimientoNormal());
            normal.setEstado("ACTIVO");
            normal.setFechaRegistro(LocalDate.now());
            normal.setGenero(normalDTO.getGeneroNormal());
            normal.setNacionalidad(normalDTO.getNacionalidadNormal());
            normal.setUsuario(ultimoCodigo);

            return normalRepository.save(normal);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Normal Actualizar(NormalRequestDTO normalDTO) {

        Optional<Normal> optionalNormal = normalRepository.findById(normalDTO.getCodigoNormal());
        if (!optionalNormal.isPresent()) {
            throw new IllegalArgumentException(
                    "El administrador con el código " + normalDTO.getCodigoNormal() + " no existe.");
        }

        usuarioService.actLogin(normalDTO.getCodigoUsuario(), normalDTO.getUsernameNormal(),
                normalDTO.getPasswordNormal(), normalDTO.getCorreoNormal(), normalDTO.getTelefonoNormal(), "NORMAL");

        usuarioService.actUsuario(normalDTO.getCodigoUsuario(), normalDTO.getUsernameNormal(),
                normalDTO.getPasswordNormal(), normalDTO.getCorreoNormal(), normalDTO.getTelefonoNormal(), "0003");

        Normal normal = optionalNormal.get();
        normal.setNombre(normalDTO.getNombreNormal());
        normal.setApellido(normalDTO.getApellidoNormal());
        normal.setCorreo(normalDTO.getCorreoNormal());
        normal.setTelefono(normalDTO.getTelefonoNormal());
        normal.setEdad(normalDTO.getEdadNormal());
        normal.setFechaNacimiento(normalDTO.getFechaNacimientoNormal());
        normal.setEstado("ACTIVO");
        normal.setGenero(normalDTO.getGeneroNormal());
        normal.setNacionalidad(normalDTO.getNacionalidadNormal());

        return normalRepository.save(normal);
    }
}
