package com.na.backend.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.stereotype.Service;


import com.na.backend.dto.request.AdminRequestDTO;
import com.na.backend.dto.response.AdminResponseDTO;

import com.na.backend.mapper.AdminMapper;
import com.na.backend.model.Admin;
import com.na.backend.model.Login;

import com.na.backend.repository.AdminRepository;
import com.na.backend.repository.LoginRepository;
import com.na.backend.repository.UsuarioRepository;
import com.na.backend.service.AdminService;
import com.na.backend.service.UsuarioService;
import com.na.backend.util.SecuenciaUtil;
import com.na.backend.util.ValidacionUtil;




@Service
public class AdminServiceImpl implements AdminService {

    private final AdminRepository adminRepository;
    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;

    private final UsuarioService usuarioService;
    private final AdminMapper adminMapper;

    public AdminServiceImpl(AdminRepository adminRepository,
            UsuarioRepository usuarioRepository,
            LoginRepository loginRepository,

            UsuarioService usuarioService,
            AdminMapper adminMapper) {
        this.adminRepository = adminRepository;
        this.usuarioRepository = usuarioRepository;
        this.loginRepository = loginRepository;
        this.usuarioService = usuarioService;
        this.adminMapper = adminMapper;
    }

    @Override
    public List<Admin> getAdminsByUsuarioCodigo(String usuarioCodigo) {
        List<Admin> admins = adminRepository.findByUsuario_Codigo(usuarioCodigo);
        return admins;
    }

    @Override
    public Optional<Admin> findById(String codigo) {
        return adminRepository.findById(codigo);
    }

    @Override
    public List<Admin> listarAdmins(String estado) {
        return adminRepository.findByEstado(estado);
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
        Admin admin = adminRepository.findById(usuarioCodigo)
                .orElseThrow(() -> new IllegalArgumentException(
                        "No se pudo encontrar el admin con código: " + usuarioCodigo));

        String usuarioId = admin.getUsuario().getCodigo();

        // Desactivar Usuario
        usuarioRepository.findById(usuarioId).ifPresent(usuario -> {
            usuario.setEstado("INACTIVO");
            usuarioRepository.save(usuario);
        });

        // Desactivar Login
        loginRepository.findById(usuarioId).ifPresent(login -> {
            login.setEstado("INACTIVO");
            loginRepository.save(login);
        });

        // Desactivar Admin
        admin.setEstado("INACTIVO");
        return adminRepository.save(admin);
    }

    @Override
    public Admin SuspenderUsuario(String usuarioCodigo) {
        Admin admin = adminRepository.findById(usuarioCodigo)
                .orElseThrow(() -> new IllegalArgumentException("El código de usuario no existe: " + usuarioCodigo));
        String usuarioId = admin.getUsuario().getCodigo();

        // Suspender Usuario
        usuarioRepository.findById(usuarioId).ifPresent(usuario -> {
            usuario.setEstado("SUSPENDIDO");
            usuarioRepository.save(usuario);
        });

        // Suspender Login
        loginRepository.findById(usuarioId).ifPresent(login -> {
            login.setEstado("SUSPENDIDO");
            loginRepository.save(login);
        });

        // Suspender Admin
        admin.setEstado("SUSPENDIDO");
        return adminRepository.save(admin);
    }

    @Override
    public Login ActivarUsuario(String usuarioCodigo) {
        Login login = loginRepository.findById(usuarioCodigo)
                .orElseThrow(() -> new IllegalArgumentException("No se encontre el codigo"));

        // Activar Admin
        adminRepository.findByCorreo(login.getCorreo()).ifPresent(admin -> {
            admin.setEstado("ACTIVO");
            adminRepository.save(admin);
        });

        // Activar Usuario
        usuarioRepository.findById(usuarioCodigo).ifPresent(usuario -> {
            usuario.setEstado("ACTIVO");
            usuarioRepository.save(usuario);
        });

        // Activar Login
        login.setEstado("ACTIVO");
        return loginRepository.save(login);
    }

    @Override
    public Login BloquearUsuario(String usuarioCodigo) {
        Login login = loginRepository.findById(usuarioCodigo)
                .orElseThrow(() -> new IllegalArgumentException("No se encontre el codigo"));

        // Bloquear Admin
        adminRepository.findByCorreo(login.getCorreo()).ifPresent(admin -> {
            admin.setEstado("BLOQUEADO");
            adminRepository.save(admin);
        });

        // Bloquear Usuario
        usuarioRepository.findById(usuarioCodigo).ifPresent(usuario -> {
            usuario.setEstado("BLOQUEADO");
            usuarioRepository.save(usuario);
        });

        // Bloquear Login
        login.setEstado("BLOQUEADO");
        return loginRepository.save(login);
    }

    private void validarAdmin(AdminRequestDTO adminDTO) {

        if (!ValidacionUtil.esTelefonoValido(adminDTO.getTelefonoAdmin())) {
            throw new IllegalArgumentException("EL TELEFONO DEBE TENER 9 DIGITOS");
        }

        if (!ValidacionUtil.esCorreoValido(adminDTO.getCorreoAdmin())) {
            throw new IllegalArgumentException("EL CORREO NO TIENE FORMATO VALIDO");
        }

        if (adminDTO.getEdadAdmin() < 18) {
            throw new IllegalArgumentException("EDAD NO PERMITIDA");
        }

        if (usuarioService.usuarioExistePorUsername(adminDTO.getUsernameAdmin())) {
            throw new IllegalArgumentException("USUARIO YA EXISTE");
        }

        if (ExistePorEmail(adminDTO.getCodigoAdmin())) {
            throw new IllegalArgumentException("CORREO YA EXISTE");
        }

        if (ExistePorTelefono(adminDTO.getTelefonoAdmin())) {
            throw new IllegalArgumentException("TELEFONO YA EXISTE");
        }
    }

    @Override
    public List<AdminResponseDTO> obtenerAdminsExcluyendoUsuario(String username) {
        List<Object[]> resultados = adminRepository.listarAdminsExcluyendoUsuario(username);
        List<AdminResponseDTO> listaDTO = new ArrayList<>();
        for (Object[] fila : resultados) {
            AdminResponseDTO dto = adminMapper.listarAdmin(fila);
            listaDTO.add(dto);
        }
        return listaDTO;
    }

    @Override
    public Admin Registro(AdminRequestDTO adminDTO) throws Exception {

        try {

            validarAdmin(adminDTO);

            String codigoUsuario = obtenerUltimoCodigoUsuario();

            String ultimoCodigo = SecuenciaUtil.generarSiguienteCodigo(codigoUsuario);

            String codigoAdmin = ObtenerUltimoCodigoAdmin();

            String ultimoCodigoAdmin = SecuenciaUtil.generarSiguienteCodigo(codigoAdmin);

            usuarioService.regUsuario(ultimoCodigo, adminDTO.getUsernameAdmin(), adminDTO.getPasswordAdmin(),
                    adminDTO.getCorreoAdmin(), adminDTO.getTelefonoAdmin(), "0001");

            usuarioService.regLogin(ultimoCodigo, adminDTO.getUsernameAdmin(), adminDTO.getPasswordAdmin(),
                    adminDTO.getCorreoAdmin(), adminDTO.getTelefonoAdmin(), "ADMIN");

            Admin admin = new Admin();
            admin.setCodigo(ultimoCodigoAdmin);
            admin.setNombre(adminDTO.getNombreAdmin());
            admin.setApellido(adminDTO.getApellidoAdmin());
            admin.setTelefono(adminDTO.getTelefonoAdmin());
            admin.setCorreo(adminDTO.getCorreoAdmin());
            admin.setEdad(adminDTO.getEdadAdmin());
            admin.setEstado("ACTIVO");
            admin.setUsuario(ultimoCodigo);
            admin.setFechaNacimiento(adminDTO.getFechaNacimientoAdmin());
            admin.setFechaRegistro(LocalDate.now());

            return adminRepository.save(admin);
        } catch (Exception e) {
            throw new Exception("Error al registrar el administrador: " + e.getMessage(), e);
        }

    }

    @Override
    public Admin Actualizar(AdminRequestDTO adminDTO) {

        Optional<Admin> adminOptional = adminRepository.findById(adminDTO.getCodigoAdmin());

        if (!adminOptional.isPresent()) {
            throw new IllegalArgumentException(
                    "El administrador con el código " + adminDTO.getCodigoAdmin() + " no existe.");
        }

        usuarioService.actLogin(adminDTO.getCodigoUsuarioAdmin(), adminDTO.getUsernameAdmin(),
                adminDTO.getPasswordAdmin(), adminDTO.getCorreoAdmin(), adminDTO.getTelefonoAdmin(), "ADMIN");

        usuarioService.actUsuario(adminDTO.getCodigoUsuarioAdmin(), adminDTO.getUsernameAdmin(),
                adminDTO.getPasswordAdmin(), adminDTO.getCorreoAdmin(), adminDTO.getTelefonoAdmin(), "0001");

        Admin admin = adminOptional.get();
        admin.setNombre(adminDTO.getNombreAdmin());
        admin.setApellido(adminDTO.getApellidoAdmin());
        admin.setTelefono(adminDTO.getTelefonoAdmin());
        admin.setFechaNacimiento(adminDTO.getFechaNacimientoAdmin());
        admin.setEstado("ACTIVO");
        admin.setCorreo(adminDTO.getCorreoAdmin());

        return adminRepository.save(admin);

    }

}
