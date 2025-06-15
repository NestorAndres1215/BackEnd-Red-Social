package com.na.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Menu;
import com.na.backend.model.Rol;

import java.util.List;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {
    // Metodo para buscar un rol por su codigo
    Rol findByCodigo(String codigo);

    @Repository
    interface MenuRepository extends JpaRepository<Menu, String> {

        // Metodo para buscar un menu por su id
        List<Menu> findByRol(Rol rol);

        // Metodo para buscar un menu por su nivel
        List<Menu> findByNivel(Integer menuNivel);
    }
}