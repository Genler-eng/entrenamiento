package com.entrenamiento.entrenamiento.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// Representa a un jugador dentro del json que llega en el POST
// ej: { "nombre": "Jugador1", "potenciaTiro": 10, "velocidad": 5, "pases": 25 }
public class JugadorResultadoDTO {

    @NotBlank(message = "El nombre del jugador es obligatorio")
    private String nombre;

    @NotNull(message = "La potencia de tiro es obligatoria")
    private Integer potenciaTiro;

    @NotNull(message = "La velocidad es obligatoria")
    private Integer velocidad;

    @NotNull(message = "Los pases son obligatorios")
    private Integer pases;

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getPotenciaTiro() { return potenciaTiro; }
    public void setPotenciaTiro(Integer potenciaTiro) { this.potenciaTiro = potenciaTiro; }

    public Integer getVelocidad() { return velocidad; }
    public void setVelocidad(Integer velocidad) { this.velocidad = velocidad; }

    public Integer getPases() { return pases; }
    public void setPases(Integer pases) { this.pases = pases; }
}