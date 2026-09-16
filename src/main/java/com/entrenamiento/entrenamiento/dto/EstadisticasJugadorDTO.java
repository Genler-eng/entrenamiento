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

    public String getNombre() { return nombre; }
    public Integer getPotenciaTiro() { return potenciaTiro; }
    public Integer getVelocidad() { return velocidad; }
    public Integer getPases() { return pases; }
}