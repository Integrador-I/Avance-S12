package com.example.pasajes.service;


import com.example.pasajes.entities.Bus;
import com.example.pasajes.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
public class BusServiceImpl  implements IBusService{

    @Autowired
    private BusRepository busRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Bus> findAll() {
        return (List<Bus>) busRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Bus> findById(Long id) {
        return busRepository.findById(id);
    }

    @Override
    @Transactional
    public Bus save(Bus bus) {
        bus.setPlate(bus.getPlate());
        bus.setType(bus.getType());
        bus.setState(bus.getState());
        bus.setCapacity(bus.getCapacity());
        return busRepository.save(bus);
    }

    @Override
    @Transactional
    public Optional<Bus> update(Bus bus, Long id) {
        Optional<Bus> busOptional = this.findById(id);
        return busOptional.map( bsOp ->{
            bsOp.setType(bus.getType());
            bsOp.setState(bus.getState());
            bsOp.setPlate(bus.getPlate());
            bsOp.setCapacity(bus.getCapacity());
            return Optional.of(busRepository.save(bsOp));
        }).orElseGet(()->Optional.empty());
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        busRepository.deleteById(id);
    }
}
