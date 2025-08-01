
package com.na.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.na.backend.model.Menu;
import com.na.backend.model.Rol;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {

    List<Menu> findByRol(Rol rol);

    List<Menu> findByNivel(Integer menuNivel);
}
