package com.example.Seguimiento.controller;

import com.example.Seguimiento.dto.RastreoDTO;
import com.example.Seguimiento.entity.Encomienda;
import com.example.Seguimiento.entity.Rastreo;
import com.example.Seguimiento.repository.EncomiendaRepository;
import com.example.Seguimiento.repository.RastreoRepository;
import com.example.Seguimiento.service.RastreoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/seguimiento")
public class RastreoController {

    @Autowired
    private RastreoService rastreoService;

    @Autowired
    private RastreoRepository rastreoRepository;

    @Autowired
    private EncomiendaRepository encomiendaRepository;

    @GetMapping("/{codigo}")
    public ResponseEntity<RastreoDTO> buscarPorCodigo(@PathVariable String codigo) {
        try {
            RastreoDTO dto = rastreoService.buscarPorCodigo(codigo);
            return ResponseEntity.ok(dto);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/ping")
    public String ping() { return "pong"; }

    @PatchMapping("/{codigo}/estado")
    public ResponseEntity<Void> actualizarEstado(
            @PathVariable String codigo,
            @RequestBody Map<String, String> request) {
        try {
            String nuevoEstado = request.get("estado");
            rastreoService.actualizarEstadoEncomienda(codigo, nuevoEstado);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/lista")
    public ResponseEntity<List<Map<String, String>>> obtenerListaEncomiendas() {

        List<Rastreo> rastreos = rastreoRepository.findAll();

        List<Map<String, String>> lista = rastreos.stream().map(r -> {
            Map<String, String> item = new HashMap<>();
            item.put("codigo", r.getCodigo());
            item.put("estado",
                    r.getEncomienda() != null ? r.getEncomienda().getEstado()
                                              : "sin estado");
            return item;
        }).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }

    @PutMapping("/actualizar")
    public ResponseEntity<?> actualizarCodigoYEstado(@RequestBody Map<String, String> data) {

        String codigoAnterior = data.get("codigoAnterior");
        String nuevoCodigo    = data.get("nuevoCodigo");
        String nuevoEstado    = data.get("estado");

        Optional<Rastreo> rastreoOpt = rastreoRepository.findByCodigo(codigoAnterior);
        if (rastreoOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Código no encontrado");
        }

        if (!codigoAnterior.equals(nuevoCodigo) &&
            rastreoRepository.findByCodigo(nuevoCodigo).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("El nuevo código ya existe");
        }

        Rastreo rastreo = rastreoOpt.get();

        rastreo.setCodigo(nuevoCodigo);
        rastreoRepository.save(rastreo);

        Encomienda enc = rastreo.getEncomienda();
        if (enc != null) {
            enc.setEstado(nuevoEstado);
            encomiendaRepository.save(enc);
        }

        return ResponseEntity.ok("Actualizado correctamente");
    }
}
