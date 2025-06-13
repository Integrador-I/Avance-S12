package com.example.Seguimiento.service;

import com.example.Seguimiento.dto.RastreoDTO;
import com.example.Seguimiento.entity.Rastreo;
import com.example.Seguimiento.repository.RastreoRepository;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RastreoService
{
    @Autowired
    private RastreoRepository rastreoRepository;

    public RastreoDTO buscarPorCodigo(String codigo) {
        List<Rastreo> rastreos = rastreoRepository.findByCodigoConEncomienda(codigo);

        if (rastreos.isEmpty()) {
            throw new RuntimeException("Código no encontrado");
        }

        Rastreo rastreo = rastreos.get(0);
        

        return new RastreoDTO(
                rastreo.getCodigo(),
                rastreo.getDescripcion(),
                rastreo.getEncomienda().getEstado(),
                rastreo.getEncomienda().getDescripcion(),
                rastreo.getEncomienda().getCliente().getNombre(),
                rastreo.getEncomienda().getDestino().getNombre()
        );
    }

    @Transactional
    public void actualizarEstadoEncomienda(String codigoRastreo, String nuevoEstado) {
        List<Rastreo> rastreos = rastreoRepository.findByCodigoConEncomienda(codigoRastreo);
        
        if (rastreos.isEmpty()) {
            throw new RuntimeException("Código de rastreo no encontrado");
        }

        Rastreo rastreo = rastreos.get(0);
        rastreo.getEncomienda().setEstado(nuevoEstado);
    }
}
