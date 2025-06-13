package com.example.Registro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Registro.model.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
}
