package com.entrenamiento.entrenamiento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class JugadorResultadoDTO {

    @NotBlank(message = "el nombre del jugador es obligatorio")
    private String nombre;

    @NotNull(message = "falta la potencia de tiro")
    private Integer potenciaTiro;

    @NotNull(message = "falta la velocidad")
    private Integer velocidad;

    @NotNull(message = "faltan los pases")
    private Integer pases;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPotenciaTiro() {
        return potenciaTiro;
    }

    public void setPotenciaTiro(Integer potenciaTiro) {
        this.potenciaTiro = potenciaTiro;
    }

    public Integer getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Integer velocidad) {
        this.velocidad = velocidad;
    }

    public Integer getPases() {
        return pases;
    }

    public void setPases(Integer pases) {
        this.pases = pases;
    }
}