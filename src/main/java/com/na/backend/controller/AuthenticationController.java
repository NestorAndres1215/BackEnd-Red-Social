package com.na.backend.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.na.backend.jwt.JwtRequest;
import com.na.backend.jwt.JwtResponse;
import com.na.backend.jwt.JwtUtils;
import com.na.backend.message.SeguridadMessage;
import com.na.backend.model.Login;
import com.na.backend.security.UserDetailsServiceImpl;
import com.na.backend.service.UsuarioService;

@Controller
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        try {
            // Verifica que el usuario exista
            if (!usuarioService.usuarioExistePorUsername(jwtRequest.getUsername())) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(SeguridadMessage.USUARIO_NO_ENCONTRADO.getMensaje());
            }

            // Verifica las credenciales del usuario
            if (!usuarioService.existsByUsernameAndPassword(jwtRequest.getUsername(), jwtRequest.getPassword())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(SeguridadMessage.CREDENCIALES_INVALIDAS.getMensaje());
            }

            // Autenticación
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));

            // Cargar detalles del usuario
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());

            // Generación del token
            String token = this.jwtUtils.generateToken(userDetails);

            // Responder con el token
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(SeguridadMessage.ERROR_SOLICITUD.getMensaje());
        }
    }

    @GetMapping("/actual-usuario")
    public ResponseEntity<?> obtenerUsuarioActual(Principal principal) {
        try {
            if (principal == null || principal.getName() == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(SeguridadMessage.USUARIO_NO_AUTORIZADO.getMensaje());
            }

            Login usuario = (Login) this.userDetailsService.loadUserByUsername(principal.getName());
            return ResponseEntity.ok(usuario);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(SeguridadMessage.ERROR_USUARIO_ACTUAL.getMensaje());
        }
    }
}
