package com.example.pasajes.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateViajeDTO
{
    private Long id;
    private LocalDate salida;
    private LocalDate llegada;

    private Long busId;
    private Long conductorId;
    private Long rutaId;

}


