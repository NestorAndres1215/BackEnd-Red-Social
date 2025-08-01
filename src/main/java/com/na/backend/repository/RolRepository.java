package com.na.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.na.backend.model.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, String> {

    Optional<Rol> findByCodigo(String codigo);

}