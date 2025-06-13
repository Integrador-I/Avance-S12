package com.example.pasajes.controller;

import com.example.pasajes.entities.Bus;
import com.example.pasajes.entities.Conductores;
import com.example.pasajes.service.IConductoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductores")
public class ConductoresController
{
    @Autowired
    private IConductoresService conductoresService;

    @GetMapping
    public ResponseEntity<List<Conductores>> getAllConductores(){
        return ResponseEntity.ok(conductoresService.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Conductores> getConductoresById (@PathVariable Long id){
        return conductoresService.findById(id)
                .map(cs -> ResponseEntity.ok(cs)).orElse(ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}")
    public ResponseEntity<Conductores> updateConductores (@RequestBody Conductores conductores, @PathVariable Long id){
        return conductoresService.update(conductores,id)
                .map(cs -> ResponseEntity.ok(cs)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Conductores> createBus (@RequestBody Conductores conductores){
        Conductores saveConductores = conductoresService.save(conductores);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveConductores);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBus(@PathVariable Long id)
    {
        conductoresService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
