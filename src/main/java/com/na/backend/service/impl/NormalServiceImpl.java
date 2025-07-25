package com.na.backend.service.impl;


import com.na.backend.dto.NormalDTO;
import com.na.backend.message.UsuarioMessage;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;
import com.na.backend.model.Normal;
import com.na.backend.model.Usuario;
import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.NormalRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.NormalService;
import com.na.backend.service.UsuarioService;
import com.na.backend.validator.Secuencia;
import com.na.backend.validator.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Validaciones validaciones;

    @Override
    public List<Normal> obtenerNormalesPorUsuarioActivo(String username) {
        return normalRepository.buscarPorUsuarioYEstadoActivo(username);
    }

    @Override
    public List<Normal> obtenerNormalesPorEstado(String estado) {
        return normalRepository.listarNormalesPorEstado(estado);
    }


    @Override
    public Normal Registro(NormalDTO normalDTO) throws Exception {
        try{
            validarNormal(normalDTO);
            String codigoUsuario = obtenerUltimoCodigoUsuario();
            String ultimoCodigo = Secuencia.incrementarSecuencia(codigoUsuario);
            String codigoNormal = ObtenerUltimoCodigoNormal();
            String ultimoCodigoNormal = Secuencia.incrementarSecuencia(codigoNormal);
            Login login = new Login();
            login.setCodigo(ultimoCodigo);
            login.setUsername(normalDTO.getUsername());
            login.setPassword(bCryptPasswordEncoder.encode(normalDTO.getPassword()));
            login.setEstado("ACTIVO");
            login.setRol("NORMAL");
            login.setCorreo(normalDTO.getCorreo());
            login.setTelefono(normalDTO.getTelefono());


            Usuario usuario = new Usuario();
            usuario.setCodigo(ultimoCodigo);
            usuario.setUsername(normalDTO.getUsername());
            usuario.setPassword(normalDTO.getPassword()); // Podrías encriptarla aquí también
            usuario.setEstado("ACTIVO");
            usuario.setTelefono(normalDTO.getTelefono());
            usuario.setRol("0003");
            usuario.setCorreo(normalDTO.getCorreo());


            Normal normal = new Normal();
            normal.setCodigo(ultimoCodigoNormal);
            normal.setNombre(normalDTO.getNombre());
            normal.setApellido(normalDTO.getApellido());
            normal.setCorreo(normalDTO.getCorreo());
            normal.setTelefono(normalDTO.getTelefono());
            normal.setEdad(normalDTO.getEdad());
            normal.setFechaNacimiento(normalDTO.getFechaNacimiento());
            normal.setEstado("ACTIVO");
            normal.setFechaRegistro(LocalDate.now());
            normal.setGenero(normalDTO.getGenero());
            normal.setNacionalidad(normalDTO.getNacionalidad());
            normal.setUsuario(ultimoCodigo);
            loginRepository.save(login);
            usuarioRepository.save(usuario);
            return normalRepository.save(normal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al registrar el normal: " + e.getMessage(), e);
        }

    }

    @Override
    public Normal Actualizar(NormalDTO normalDTO) {
        try{

                Optional<Normal> optionalNormal = normalRepository.findById(normalDTO.getCodigoNormal());
                if (!optionalNormal.isPresent()) {
                    throw new IllegalArgumentException(
                            "El administrador con el código " + normalDTO.getCodigoNormal() + " no existe.");
                }
                Normal normal =optionalNormal.get();
                normal.setNombre(normalDTO.getNombre());
                normal.setApellido(normalDTO.getApellido());
                normal.setCorreo(normalDTO.getCorreo());
                normal.setTelefono(normalDTO.getTelefono());
                normal.setEdad(normalDTO.getEdad());
                normal.setFechaNacimiento(normalDTO.getFechaNacimiento());
                normal.setEstado("ACTIVO");
                normal.setGenero(normalDTO.getGenero());
                normal.setNacionalidad(normalDTO.getNacionalidad());
                Usuario usuario = usuarioRepository.findById(normalDTO.getCodigoUsuario())
                        .orElseThrow(() -> new RuntimeException(
                                "El usuario con código " + normalDTO.getCodigoUsuario() + " no existe."));
                usuario.setUsername(normalDTO.getUsername());
                usuario.setPassword(bCryptPasswordEncoder.encode(normalDTO.getPassword()));
                usuario.setCorreo(normal.getCorreo());
                usuario.setEstado("ACTIVO");
                usuario.setTelefono(normalDTO.getTelefono());
                usuario.setRol("0003");

                Login login = loginRepository.findById(normalDTO.getCodigoUsuario())
                        .orElseThrow(() -> new RuntimeException(
                                "El login con código " + normalDTO.getCodigoUsuario() + " no existe."));
                login.setUsername(normalDTO.getUsername());
                login.setPassword(bCryptPasswordEncoder.encode(normalDTO.getPassword()));
                login.setCorreo(normalDTO.getCorreo());
                login.setTelefono(normalDTO.getTelefono());
                login.setEstado("ACTIVO");
                loginRepository.save(login);
                usuarioRepository.save(usuario);
                return normalRepository.save(normal);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

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
    public Login ActivarUsuario(String usuarioCodigo) {

        Optional<Login> login = loginRepository.findById(usuarioCodigo);
        if (login.isPresent()) {
            Login loginEntity = login.get();

            Optional<Normal> normal = normalRepository.findByCorreo(login.get().getCorreo());

            if(normal.isPresent()){
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
            return loginRepository.save(loginEntity);
        }
        else {
            throw new IllegalArgumentException(UsuarioMessage.ADMIN_NO_EXISTE.getMensaje());
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
            if(normal.isPresent()){
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
            throw new IllegalArgumentException(UsuarioMessage.ADMIN_NO_EXISTE.getMensaje());
        }

    }

    @Override
    public Optional<Normal> findById(String codigo) {
        return normalRepository.findById(codigo);
    }

    private boolean validarNormal(NormalDTO normalDTO) {

        if (usuarioService.usuarioExistePorUsername(normalDTO.getUsername())) {
            throw new IllegalArgumentException("USUARIO YA EXISTE");
        }
        if (ExistePorEmail(normalDTO.getCorreo())) {
            throw new IllegalArgumentException("CORREO YA EXISTE");
        }

        if (ExistePorTelefono(normalDTO.getTelefono())) {
            throw new IllegalArgumentException("TELEFONO YA EXISTE");
        }
        if (!validaciones.validarTelefono(normalDTO.getTelefono())) {
            throw new IllegalArgumentException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }

        if (!validaciones.correoEsValido(normalDTO.getCorreo())) {
            throw new IllegalArgumentException("EL CORREO NO TIENEN FORMATO VALIDO");
        }

        if (normalDTO.getEdad() < 18) {
            throw new IllegalArgumentException("EDAD NO PERMITIDA");
        }
        return ResponseEntity.ok("Validación exitosa").hasBody();
    }
}
