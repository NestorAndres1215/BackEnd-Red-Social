package com.na.backend.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.na.backend.security.Authority;

import jakarta.persistence.*;

@Entity
@Table(name = "login")
public class Login implements UserDetails {

    @Id
    @Column(name = "ul_codigo")
    private String codigo;
    @Column(name = "ul_usuario")
    private String username;
    @Column(name = "ul_contra")
    private String password;
    @Column(name = "ul_correo")
    private String correo;
    @Column(name = "ul_telefono")
    private String telefono;
    @Column(name = "ul_rol")
    private String rol;
    @Column(name = "ul_estado", nullable = false)
    private String estado;

    public Login(String codigo, String username, String password, String correo, String telefono, String rol,
            String estado) {
        this.codigo = codigo;
        this.username = username;
        this.password = password;
        this.correo = correo;
        this.telefono = telefono;
        this.rol = rol;
        this.estado = estado;
    }

    public Login() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Login{" +
                "codigo='" + codigo + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", correo='" + correo + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol='" + rol + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Authority> autoridades = new HashSet<>();

        autoridades.add(new Authority(getRol()));

        return autoridades;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}
