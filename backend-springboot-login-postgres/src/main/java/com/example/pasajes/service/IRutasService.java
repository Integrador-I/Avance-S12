package com.example.pasajes.service;
import com.example.pasajes.entities.Rutas;

import java.util.List;
import java.util.Optional;

public interface IRutasService
{
    List<Rutas> findAll();
    Optional<Rutas> findById(Long id);
    Rutas save(Rutas rutas);
    Optional<Rutas> update(Rutas rutas, Long id);
    void deleteById(Long id);

}
