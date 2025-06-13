package com.example.pasajes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
@Entity
@Table(name ="rutas")
@Getter
@Setter
@NoArgsConstructor
public class Rutas
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String origin;

    private String destination;

    private Time duration;

    private long distance;

}
