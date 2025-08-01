package com.na.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.na.backend.model.Login;
import java.util.Optional;

@Repository
public interface LoginRepository extends JpaRepository<Login, String> {

    Login findByUsername(String username);

    Optional<Login> findByCorreo(String email);

}
