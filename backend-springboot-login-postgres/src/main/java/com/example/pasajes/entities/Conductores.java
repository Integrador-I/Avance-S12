package com.example.pasajes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name ="conductores")
@Getter
@Setter
@NoArgsConstructor
public class Conductores
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    @Column(unique = true)
    private String dni;

    private String phone;

    @Column(unique = true)
    private String email;

    private String license;

    private LocalDate expiration;

    private String state;

    private LocalDate income;
}
