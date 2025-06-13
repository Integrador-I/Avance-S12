package com.example.pasajes.mapper;

import com.example.pasajes.dto.ViajesDTO;
import com.example.pasajes.entities.Viajes;
import org.springframework.stereotype.Component;


    @Component
    public class ViajesMapper {

        public ViajesDTO toDTO(Viajes viaje) {
            ViajesDTO dto = new ViajesDTO();
            dto.setId(viaje.getId());
            dto.setSalida(viaje.getSalida());
            dto.setLlegada(viaje.getLlegada());

            if (viaje.getBus() != null) {
                dto.setPlate(viaje.getBus().getPlate());
            }

            if (viaje.getRutas() != null) {
                dto.setOrigin(viaje.getRutas().getOrigin());
                dto.setDestination(viaje.getRutas().getDestination());
            }

            if (viaje.getConductores() != null) {
                dto.setName(viaje.getConductores().getName());
                dto.setLastname(viaje.getConductores().getLastname());
            }

            return dto;
        }
    }


