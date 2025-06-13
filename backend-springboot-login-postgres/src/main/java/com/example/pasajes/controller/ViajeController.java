package com.example.pasajes.controller;

import com.example.pasajes.dto.UpdateViajeDTO;
import com.example.pasajes.dto.ViajesDTO;
import com.example.pasajes.entities.Viajes;
import com.example.pasajes.mapper.ViajesMapper;
import com.example.pasajes.service.IViajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viajes")
public class ViajeController
{
    @Autowired
    private IViajesService viajesService;

    @Autowired
    private ViajesMapper viajesMapper;

    @GetMapping
    public ResponseEntity<List<Viajes>> getAllRutas(){

        return ResponseEntity.ok(viajesService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Viajes> getViajesById (@PathVariable Long id){
        return viajesService.findById(id)
                .map(vj -> ResponseEntity.ok(vj)).orElse(ResponseEntity.notFound().build());

    }

    @PutMapping("/{id}")
    public ResponseEntity<Viajes> updateViajes (@RequestBody Viajes viajes, @PathVariable Long id){
        return viajesService.update(viajes,id)
                .map(vj -> ResponseEntity.ok(vj)).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping
    public ResponseEntity<ViajesDTO> createViajes (@RequestBody Viajes viajes){

        Viajes savedViajes = viajesService.save(viajes);
        ViajesDTO dto = viajesMapper.toDTO(savedViajes);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

   /* @PostMapping
    public ResponseEntity<Viajes> createViajes (@RequestBody Viajes viajes){

        Viajes savedViajes = viajesService.save(viajes);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedViajes);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteViajes(@PathVariable Long id)
    {
        viajesService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/table")
    public List<ViajesDTO> getViajesTable() {
        return viajesService.findAllTable();
    }

    @PutMapping("/table/{id}")
    public ResponseEntity<Viajes> updateViaje(@PathVariable Long id, @RequestBody UpdateViajeDTO updateViajeDTO) {
        Viajes actualizado = viajesService.updateViajes(updateViajeDTO);
        return ResponseEntity.ok(actualizado);
    }
}
