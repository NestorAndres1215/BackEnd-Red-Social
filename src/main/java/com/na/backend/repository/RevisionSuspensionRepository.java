package com.na.backend.repository;

import com.na.backend.model.RevisionSuspension;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RevisionSuspensionRepository extends JpaRepository<RevisionSuspension,String> {

    @Query(value = "SELECT MAX(rs_codigo) FROM Revisiones_Suspension", nativeQuery = true)
    String obtenerUltimoCodigo();

}
