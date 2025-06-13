package com.example.pasajes.service;

import com.example.pasajes.entities.Conductores;
import com.example.pasajes.repository.ConductoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class ConductoresServiceImpl implements IConductoresService{
    @Autowired
    ConductoresRepository conductoresRepository;
    @Override
    @Transactional(readOnly = true)
    public List<Conductores> findAll() {
        return (List<Conductores>) conductoresRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Conductores> findById(Long id) {
        return conductoresRepository.findById(id);
    }

    @Override
    @Transactional
    public Conductores save(Conductores conductores) {
        conductores.setDni(conductores.getDni());
        conductores.setEmail(conductores.getEmail());
        conductores.setExpiration(conductores.getExpiration());
        conductores.setName(conductores.getName());
        conductores.setLicense(conductores.getLicense());
        conductores.setIncome(conductores.getIncome());
        conductores.setPhone(conductores.getPhone());
        conductores.setLastname(conductores.getLastname());
        conductores.setState(conductores.getState());
        return conductoresRepository.save(conductores);
    }

    @Override
    @Transactional
    public Optional<Conductores> update(Conductores conductores, Long id) {
        Optional<Conductores> conductoresOptional = conductoresRepository.findById(id);
        return conductoresOptional.map( cdOp ->{
            cdOp.setDni(conductores.getDni());
            cdOp.setEmail(conductores.getEmail());
            cdOp.setExpiration(conductores.getExpiration());
            cdOp.setName(conductores.getName());
            cdOp.setLicense(conductores.getLicense());
            cdOp.setIncome(conductores.getIncome());
            cdOp.setPhone(conductores.getPhone());
            cdOp.setLastname(conductores.getLastname());
            cdOp.setState(conductores.getState());
            return Optional.of(conductoresRepository.save(cdOp));
        }).orElseGet(()->Optional.empty());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        conductoresRepository.deleteById(id);
    }
}
