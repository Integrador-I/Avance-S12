package com.example.pasajes.service;

import com.example.pasajes.entities.Rutas;
import com.example.pasajes.repository.RutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class RutasServiceImpl implements IRutasService {
    @Autowired
    private RutasRepository rutasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Rutas> findAll() {
        return (List<Rutas>) rutasRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Rutas> findById(Long id) {
        return rutasRepository.findById(id);
    }

    @Override
    @Transactional
    public Rutas save(Rutas rutas) {
        rutas.setDestination(rutas.getDestination());
        rutas.setDuration(rutas.getDuration());
        rutas.setDistance(rutas.getDistance());
        rutas.setOrigin(rutas.getOrigin());
        return rutasRepository.save(rutas);
    }

    @Override
    @Transactional
    public Optional<Rutas> update(Rutas rutas, Long id) {
        Optional<Rutas> rutasOptional = rutasRepository.findById(id);
        return rutasOptional.map(rtOp ->{
            rtOp.setOrigin(rutas.getOrigin());
            rtOp.setDistance(rutas.getDistance());
            rtOp.setDuration(rutas.getDuration());
            rtOp.setDestination(rutas.getDestination());
            return Optional.of(rutasRepository.save(rtOp));
        }).orElseGet(()->Optional.empty());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        rutasRepository.deleteById(id);
    }
}
