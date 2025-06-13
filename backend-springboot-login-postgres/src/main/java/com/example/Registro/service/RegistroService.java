package com.example.Registro.service;

import java.util.UUID;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.Registro.model.Registro;
import com.example.Registro.repository.RegistroRepository;
import com.example.Seguimiento.entity.Destino;
import com.example.Seguimiento.entity.Encomienda;
import com.example.Seguimiento.entity.Rastreo;
import com.example.Seguimiento.entity.Usuario;
import com.example.Seguimiento.repository.EncomiendaRepository;
import com.example.Seguimiento.repository.RastreoRepository;

@Service
public class RegistroService {

    private final RegistroRepository registroRepo;
    private final EncomiendaRepository encomiendaRepo;
    private final RastreoRepository rastreoRepo;

    public RegistroService(RegistroRepository r, EncomiendaRepository e, RastreoRepository t) {
        this.registroRepo = r;
        this.encomiendaRepo = e;
        this.rastreoRepo   = t;
    }

    /** Registra todo en una única transacción */
    @Transactional
    public String registrar(Registro dto) {

        // 1) Guardar la fila en “registro”
        registroRepo.save(dto);

        // 2) Crear la encomienda con estado = confirmed
        Encomienda encomienda = new Encomienda();
        encomienda.setDescripcion(dto.getTipoPaquete());
        encomienda.setEstado("confirmed");

        encomienda = encomiendaRepo.save(encomienda);   // estado por defecto

        Usuario clienteFijo = new Usuario();
        clienteFijo.setIdCliente(1);  // Suponiendo que ID es Long
        encomienda.setCliente(clienteFijo);

        Destino destinoFijo = new Destino();
        destinoFijo.setIdDestino(1);
        encomienda.setDestino(destinoFijo);

        encomienda = encomiendaRepo.save(encomienda);
        // 3) Generar un código único para rastreo
        String codigo = generarCodigoUnico();

        // 4) Crear la fila en “rastreo”
        Rastreo rastreo = new Rastreo();
        rastreo.setCodigo(codigo);
        rastreo.setDescripcion("Envío registrado");
        rastreo.setEncomienda(encomienda);
        rastreoRepo.save(rastreo);

        return codigo;   // se lo devolvemos al controller
    }

    /** Mezcla UUID + Random para evitar colisiones y mantenerlo legible */
    private String generarCodigoUnico() {
        String code;
        do {
            // 10 caracteres alfanuméricos en mayúsculas
            code = RandomStringUtils.randomAlphanumeric(10).toUpperCase();
        } while (rastreoRepo.findByCodigo(code).isPresent());
        return code;
    }
}
