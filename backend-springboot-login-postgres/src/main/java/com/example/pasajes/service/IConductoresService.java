package com.example.pasajes.service;

import com.example.pasajes.entities.Conductores;

import java.util.List;
import java.util.Optional;

public interface IConductoresService
{
    List<Conductores> findAll();
    Optional<Conductores> findById(Long id);
    Conductores save(Conductores conductores);
    Optional<Conductores> update(Conductores conductores, Long id);
    void deleteById(Long id);
}
