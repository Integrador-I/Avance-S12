package com.example.pasajes.service;

import com.example.pasajes.entities.Bus;

import java.util.List;
import java.util.Optional;

public interface IBusService
{
    List<Bus> findAll();
    Optional<Bus> findById(Long id);
    Bus save(Bus bus);
    Optional<Bus> update(Bus bus, Long id);
    void deleteById(Long id);
}
