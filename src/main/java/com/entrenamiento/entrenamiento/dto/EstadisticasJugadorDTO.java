package com.entrenamiento.entrenamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EstadisticasJugadorDTO {
    private String nombre;
    private Integer potenciaTiro;
    private Integer velocidad;
    private Integer pases;
}