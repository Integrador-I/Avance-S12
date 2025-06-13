package com.example.pasajes.controller;

import com.example.pasajes.entities.Rutas;
import com.example.pasajes.service.IRutasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/rutas")
public class RutasController
{
    @Autowired
    private IRutasService rutasService;

    @GetMapping
    public ResponseEntity<List<Rutas>> getAllRutas(){
        return ResponseEntity.ok(rutasService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Rutas> getRutasById (@PathVariable Long id){
        return rutasService.findById(id)
                .map(bs -> ResponseEntity.ok(bs)).orElse(ResponseEntity.notFound().build());

    }
    @PutMapping("/{id}")
    public ResponseEntity<Rutas> updateRutas (@RequestBody Rutas rutas, @PathVariable Long id){
        return rutasService.update(rutas,id)
                .map(bs -> ResponseEntity.ok(bs)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<Rutas> createRutas (@RequestBody Rutas rutas){
        Rutas savedBus = rutasService.save(rutas);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBus);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRutas(@PathVariable Long id)
    {
        rutasService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
