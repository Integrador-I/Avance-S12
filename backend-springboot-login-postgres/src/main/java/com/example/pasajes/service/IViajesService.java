package com.example.pasajes.service;

import com.example.pasajes.dto.UpdateViajeDTO;
import com.example.pasajes.dto.ViajesDTO;
import com.example.pasajes.entities.Viajes;

import java.util.List;
import java.util.Optional;

public interface IViajesService
{
    List<Viajes> findAll();
    Optional<Viajes> findById(Long id);
    Viajes save(Viajes viajes);
    Optional<Viajes> update(Viajes viajes, Long id);
    void deleteById(Long id);
    List<ViajesDTO> findAllTable();
    Viajes updateViajes(UpdateViajeDTO updateViajeDTO);

}
