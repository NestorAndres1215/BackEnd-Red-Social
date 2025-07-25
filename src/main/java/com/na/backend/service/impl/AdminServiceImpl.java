package com.na.backend.service.impl;

import com.na.backend.dto.AdminDTO;
import com.na.backend.message.UsuarioMessage;
import com.na.backend.model.*;
import com.na.backend.repository.AdminRepository;
import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.AdminService;
import com.na.backend.service.ModeradorService;
import com.na.backend.service.SuspencionesService;
import com.na.backend.service.UsuarioService;
import com.na.backend.validator.Secuencia;
import com.na.backend.validator.Validaciones;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public  class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private Validaciones validaciones;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private SuspencionesService suspencionesService;

    @Autowired
    private ModeradorService moderadorService;

    @Override
    public List<Admin> getAdminsByUsuarioCodigo(String usuarioCodigo) {
        List<Admin> admins = adminRepository.findByUsuario_Codigo(usuarioCodigo);
        // Si la lista está vacía, devolvemos null
        if (admins.isEmpty()) {
            return null;
        } else {
            return admins;
        }
    }

    @Override
    public Optional<Admin> findById(String codigo) {
        return adminRepository.findById(codigo);
    }

    @Override
    public List<Admin> listarAdminsActivados() {
        return adminRepository.listarAdminsActivados();
    }

    @Override
    public List<Admin> listarAdminsDesactivado() {
        return adminRepository.listarAdminsInactivos();
    }

    @Override
    public Admin Registro(AdminDTO adminDTO) throws Exception {
        try {
            validarAdmin(adminDTO);
            String codigoUsuario = obtenerUltimoCodigoUsuario();
            String ultimoCodigo = Secuencia.incrementarSecuencia(codigoUsuario);
            String codigoAdmin = ObtenerUltimoCodigoAdmin();
            String ultimoCodigoAdmin = Secuencia.incrementarSecuencia(codigoAdmin);

            Login login = new Login();
            login.setCodigo(ultimoCodigo);
            login.setUsername(adminDTO.getUsername());
            login.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
            login.setEstado("ACTIVO");
            login.setRol("ADMIN");
            login.setCorreo(adminDTO.getCorreo());
            login.setTelefono(adminDTO.getTelefono());

            Usuario usuario = new Usuario();
            usuario.setCodigo(ultimoCodigo);
            usuario.setUsername(adminDTO.getUsername());
            usuario.setPassword(adminDTO.getPassword()); // Podrías encriptarla aquí también
            usuario.setEstado("ACTIVO");
            usuario.setTelefono(adminDTO.getTelefono());
            usuario.setRol("0001");
            usuario.setCorreo(adminDTO.getCorreo());

            Admin admin = new Admin();
            admin.setCodigo(ultimoCodigoAdmin);
            admin.setNombre(adminDTO.getNombre());
            admin.setApellido(adminDTO.getApellido());
            admin.setTelefono(adminDTO.getTelefono());
            admin.setCorreo(adminDTO.getCorreo());
            admin.setEdad(adminDTO.getEdad());
            admin.setEstado("ACTIVO");
            admin.setUsuario(ultimoCodigo);
            admin.setFechaNacimiento(adminDTO.getFechaNacimiento());
            admin.setFechaRegistro(LocalDate.now());
            loginRepository.save(login);
            usuarioRepository.save(usuario);
            return adminRepository.save(admin);

        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al registrar el administrador: " + e.getMessage(), e);
        }
    }

    @Override
    public Admin Actualizar(AdminDTO adminDTO) {
            System.out.print("Administradores"+adminDTO);
        Optional<Admin> adminOptional = adminRepository.findById(adminDTO.getCodigoAdmin());
        if (!adminOptional.isPresent()) {
            throw new IllegalArgumentException(
                    "El administrador con el código " + adminDTO.getCodigoAdmin() + " no existe.");
        }
        Admin admin = adminOptional.get();
        admin.setNombre(adminDTO.getNombre());
        admin.setApellido(adminDTO.getApellido());
        admin.setTelefono(adminDTO.getTelefono());
        admin.setFechaNacimiento(adminDTO.getFechaNacimiento());
        admin.setEstado("ACTIVO");
        admin.setCorreo(adminDTO.getCorreo());

        Usuario usuario = usuarioRepository.findById(adminDTO.getCodigoUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "El usuario con código " + adminDTO.getCodigoUsuario() + " no existe."));
        usuario.setUsername(adminDTO.getUsername());
        usuario.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
        usuario.setCorreo(adminDTO.getCorreo());
        usuario.setEstado("ACTIVO");
        usuario.setTelefono(adminDTO.getTelefono());
        usuario.setRol("0001");

        Login login = loginRepository.findById(adminDTO.getCodigoUsuario())
                .orElseThrow(() -> new RuntimeException(
                        "El login con código " + adminDTO.getCodigoUsuario() + " no existe."));
        login.setUsername(adminDTO.getUsername());
        login.setPassword(bCryptPasswordEncoder.encode(adminDTO.getPassword()));
        login.setCorreo(adminDTO.getCorreo());
        login.setTelefono(adminDTO.getTelefono());
        login.setEstado("ACTIVO");

        loginRepository.save(login);
        usuarioRepository.save(usuario);
        return adminRepository.save(admin);

    }

    @Override
    public String obtenerUltimoCodigoUsuario() {
        return usuarioRepository.obtenerUltimoCodigo();
    }

    @Override
    public String ObtenerUltimoCodigoAdmin() {
        return adminRepository.obtenerUltimoCodigoAdmin();
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
    public Admin DesactivarUsuario(String usuarioCodigo) {
        Optional<Admin> adminOptional = adminRepository.findById(usuarioCodigo);
        if (adminOptional.isPresent()) {
            Admin admin = adminOptional.get();
            Optional<Usuario> usuario = usuarioRepository.findById(admin.getUsuario().getCodigo());
            if (usuario.isPresent()) {
                Usuario usuarioEntity = usuario.get();
                usuarioEntity.setEstado("INACTIVO");
                usuarioRepository.save(usuarioEntity);
            }
            Optional<Login> login = loginRepository.findById(admin.getUsuario().getCodigo());

            if (login.isPresent()) {
                Login loginEntity = login.get();
                loginEntity.setEstado("INACTIVO");
                loginRepository.save(loginEntity);
            }

            admin.setEstado("INACTIVO");

            return adminRepository.save(admin);
        } else {
            throw new IllegalArgumentException(UsuarioMessage.ADMIN_NO_EXISTE.getMensaje());
        }
    }

    @Override
    public Admin SuspenderUsuario(String usuarioCodigo) {

            Optional<Admin> adminOptional = adminRepository.findById(usuarioCodigo);
            System.out.print(adminOptional);

            if (adminOptional.isPresent()) {
                Admin admin = adminOptional.get();

                Optional<Usuario> usuario = usuarioRepository.findById(admin.getUsuario().getCodigo());
                if (usuario.isPresent()) {
                    Usuario usuarioEntity = usuario.get();
                    usuarioEntity.setEstado("SUSPENDIDO");
                    usuarioRepository.save(usuarioEntity);
                }

                Optional<Login> login = loginRepository.findById(admin.getUsuario().getCodigo());
                if (login.isPresent()) {
                    Login loginEntity = login.get();
                    loginEntity.setEstado("SUSPENDIDO");
                    loginRepository.save(loginEntity);
                }

                admin.setEstado("SUSPENDIDO");
                return adminRepository.save(admin);
            } else {
                throw new IllegalArgumentException("El código de usuario no existe: " + usuarioCodigo);
            }


    }



    @Override
    public Login ActivarUsuario(String usuarioCodigo) {

            Optional<Login> login = loginRepository.findById(usuarioCodigo);
            if (login.isPresent()) {
                Login loginEntity = login.get();

                Optional<Admin> admin = adminRepository.findByCorreo(login.get().getCorreo());
                System.out.print(admin);
                if(admin.isPresent()){
                    Admin adminEntity = admin.get();
                    adminEntity.setEstado("ACTIVO");
                   adminRepository.save(adminEntity);
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
            Optional<Admin> admin = adminRepository.findByCorreo(login.getCorreo());
            System.out.print(admin);
            if(admin.isPresent()){
                Admin adminEntity = admin.get();
                adminEntity.setEstado("BLOQUEADO");
                adminRepository.save(adminEntity);
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

    private boolean validarAdmin(AdminDTO adminDTO) {

        if (usuarioService.usuarioExistePorUsername(adminDTO.getUsername())) {
            throw new IllegalArgumentException("USUARIO YA EXISTE");
        }
        if (ExistePorEmail(adminDTO.getCorreo())) {
            throw new IllegalArgumentException("CORREO YA EXISTE");
        }

        if (ExistePorTelefono(adminDTO.getTelefono())) {
            throw new IllegalArgumentException("TELEFONO YA EXISTE");
        }
        if (!validaciones.validarTelefono(adminDTO.getTelefono())) {
            throw new IllegalArgumentException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }

        if (!validaciones.correoEsValido(adminDTO.getCorreo())) {
            throw new IllegalArgumentException("EL CORREO NO TIENEN FORMATO VALIDO");
        }

        if (adminDTO.getEdad() < 18) {
            throw new IllegalArgumentException("EDAD NO PERMITIDA");
        }
        return ResponseEntity.ok("Validación exitosa").hasBody();
    }





    @Override
    public List<Map<String, Object>> obtenerAdminsExcluyendoUsuario(String username) {
        List<Object[]> resultados = adminRepository.listarAdminsExcluyendoUsuario(username);
        List<Map<String, Object>> lista = new ArrayList<>();

        for (Object[] fila : resultados) {
            Map<String, Object> adminMap = new HashMap<>();

            // Campos de la tabla admin (asumiendo orden exacto)
            adminMap.put("Codigo", fila[0]);
            adminMap.put("Nombre", fila[1]);
            adminMap.put("Apellido", fila[2]);
            adminMap.put("Correo", fila[3]);
            adminMap.put("Telefono", fila[4]);
            adminMap.put("Edad", fila[5]);
            adminMap.put("FechaNacimiento", fila[6]);
            adminMap.put("Estado", fila[7]);
            adminMap.put("FechaRegistro", fila[8]);
            adminMap.put("CodUsuario", fila[9]);

            // Campos de la tabla usuario (extras del SP)
            adminMap.put("Usuario", fila[10]);
            adminMap.put("Contra", fila[11]);
            adminMap.put("Rol", fila[12]);

            lista.add(adminMap);
        }

        return lista;
    }



}
