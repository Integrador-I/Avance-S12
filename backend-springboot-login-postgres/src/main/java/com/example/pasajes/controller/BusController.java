package com.example.pasajes.controller;

import com.example.pasajes.entities.Bus;
import com.example.pasajes.service.IBusService;
import com.example.pasajes.service.IViajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
public class BusController
{
    @Autowired
    private IBusService busService;

    @Autowired
    private IViajesService viajesService;

    @GetMapping
    public ResponseEntity<List<Bus>> getAllBus(){
        return ResponseEntity.ok(busService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bus> getBusById (@PathVariable Long id){
        return busService.findById(id)
                .map(bs -> ResponseEntity.ok(bs)).orElse(ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}")
    public ResponseEntity<Bus> updateBus (@RequestBody Bus bus, @PathVariable Long id){
        return busService.update(bus,id)
                .map(bs -> ResponseEntity.ok(bs)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Bus> createBus (@RequestBody Bus bus){
        Bus savedBus = busService.save(bus);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id)
    {
        viajesService.deleteById(id);
        busService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
