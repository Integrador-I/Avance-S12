package com.example.pasajes.repository;

import com.example.pasajes.entities.Bus;
import org.springframework.data.repository.CrudRepository;

public interface BusRepository extends CrudRepository<Bus,Long> {
}
