package com.entrenamiento.entrenamiento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEntrenamiento;

    @ManyToOne
    private Jugador jugador;

    private Integer potenciaTiro;
    private Integer velocidad;
    private Integer pases;
    private Double resultado;
}