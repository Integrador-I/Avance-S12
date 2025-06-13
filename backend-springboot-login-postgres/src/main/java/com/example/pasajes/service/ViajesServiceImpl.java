package com.example.pasajes.service;

import com.example.pasajes.dto.UpdateViajeDTO;
import com.example.pasajes.dto.ViajesDTO;
import com.example.pasajes.entities.Bus;
import com.example.pasajes.entities.Conductores;
import com.example.pasajes.entities.Rutas;
import com.example.pasajes.entities.Viajes;
import com.example.pasajes.mapper.ViajesMapper;
import com.example.pasajes.repository.BusRepository;
import com.example.pasajes.repository.ConductoresRepository;
import com.example.pasajes.repository.RutasRepository;
import com.example.pasajes.repository.ViajesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ViajesServiceImpl implements IViajesService
{

    @Autowired
    private ViajesRepository viajesRepository;

    @Autowired
    private BusRepository busRepository;

    @Autowired
    private ConductoresRepository conductoresRepository;

    @Autowired
    private RutasRepository rutasRepository;


    @Override
    public List<Viajes> findAll() {
        return (List<Viajes>) viajesRepository.findAll();
    }

    @Override
    public Optional<Viajes> findById(Long id) {
        return viajesRepository.findById(id);
    }

    @Override
    public Viajes save(Viajes viajes) {
        viajes.setSalida(viajes.getSalida());
        viajes.setLlegada(viajes.getLlegada());
        viajes.setBus(viajes.getBus());
        viajes.setRutas(viajes.getRutas());
        viajes.setConductores(viajes.getConductores());
        return viajesRepository.save(viajes);
    }

    @Override
    public Optional<Viajes> update(Viajes viajes, Long id) {
        Optional<Viajes> viajesOptional = viajesRepository.findById(id);
        return viajesOptional.map( viOp ->{
            viOp.setSalida(viajes.getSalida());
            viOp.setLlegada(viajes.getLlegada());
            viOp.setBus(viajes.getBus());
            viOp.setRutas(viajes.getRutas());
            viOp.setConductores(viajes.getConductores());
            return Optional.of(viajesRepository.save(viOp));
        }).orElseGet(()->Optional.empty());
    }

    @Override
    public void deleteById(Long id) {
        viajesRepository.deleteById(id);
    }

    @Override
    public List<ViajesDTO> findAllTable() {
        List<Viajes> viajes = this.findAll();
        return viajes.stream().map( v ->{
            ViajesDTO viajesDTO = new ViajesDTO();
            viajesDTO.setId(v.getId());
            viajesDTO.setSalida(v.getSalida());
            viajesDTO.setLlegada(v.getLlegada());
            viajesDTO.setPlate(v.getBus().getPlate());
            viajesDTO.setOrigin(v.getRutas().getOrigin());
            viajesDTO.setDestination(v.getRutas().getDestination());
            viajesDTO.setName(v.getConductores().getName());
            viajesDTO.setLastname(v.getConductores().getLastname());
            return viajesDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public Viajes updateViajes(UpdateViajeDTO updateViajeDTO) {
        Viajes viajes = viajesRepository.findById(updateViajeDTO.getId())
                .orElseThrow(() -> new RuntimeException("Viaje no encontrado"));

        viajes.setSalida(updateViajeDTO.getSalida());
        viajes.setLlegada(updateViajeDTO.getLlegada());

        Bus bus = busRepository.findById(updateViajeDTO.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus no encontrado"));

        Conductores conductores = conductoresRepository.findById(updateViajeDTO.getConductorId())
                .orElseThrow(() -> new RuntimeException("Conductor no encontrado"));

        Rutas rutas = rutasRepository.findById(updateViajeDTO.getRutaId())
                .orElseThrow(() -> new RuntimeException("Ruta no encontrada"));

        viajes.setBus(bus);
        viajes.setConductores(conductores);
        viajes.setRutas(rutas);
        return viajesRepository.save(viajes);
    }

}
