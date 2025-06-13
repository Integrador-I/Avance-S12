package com.example.pasajes.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="bus")
@Getter
@Setter
@NoArgsConstructor
public class Bus
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String plate;

    private String type;

    private Integer capacity;

    private String state;

}
