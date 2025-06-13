package com.example.pasajes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;



@Entity
@Table(name="viajes")
@Getter
@Setter
@NoArgsConstructor
public class Viajes
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name ="ruta_id")
    private Rutas rutas;

    private LocalDate salida;

    private LocalDate llegada;

    @ManyToOne
    @JoinColumn(name ="conductor_id")
    private Conductores conductores;

    @ManyToOne
    @JoinColumn(name ="bus_id")
    private Bus bus;



}