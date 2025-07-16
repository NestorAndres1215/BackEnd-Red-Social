package com.na.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.na.backend.model.Rol;



@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

    Rol findByCodigo(String codigo);

}