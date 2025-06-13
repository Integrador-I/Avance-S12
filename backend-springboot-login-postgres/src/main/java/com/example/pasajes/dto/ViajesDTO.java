package com.example.pasajes.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@NoArgsConstructor

public class ViajesDTO
{
    //mostrar lo de viajes
    private Long id;
    private LocalDate salida;
    private LocalDate llegada;

    //mostrar lo de bus
    private String plate;

    //mostrar lo de rutas
    private String origin;
    private String destination;

    //mostar conductor

    private String name;
    private String lastname;

}
