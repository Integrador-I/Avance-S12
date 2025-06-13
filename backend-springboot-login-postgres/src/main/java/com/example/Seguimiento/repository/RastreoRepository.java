package com.example.Seguimiento.repository;

import com.example.Seguimiento.entity.Rastreo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RastreoRepository extends JpaRepository<Rastreo, Integer> {

    Optional<Rastreo> findByCodigo(String codigo);

    @Query("SELECT r FROM Rastreo r JOIN FETCH r.encomienda WHERE r.codigo = :codigo")
    List<Rastreo> findByCodigoConEncomienda(@Param("codigo") String codigo);
}
