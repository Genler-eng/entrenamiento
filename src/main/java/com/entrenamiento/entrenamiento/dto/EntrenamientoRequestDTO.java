package com.entrenamiento.entrenamiento.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

public class EntrenamientoRequestDTO {

    @NotNull(message = "debes indicar el numero de entrenamiento (1, 2 o 3)")
    private Integer numeroEntrenamiento;

    @NotEmpty(message = "debes mandar al menos un jugador")
    @Valid
    private List<JugadorResultadoDTO> jugadores;

    public Integer getNumeroEntrenamiento() {
        return numeroEntrenamiento;
    }

    public void setNumeroEntrenamiento(Integer numeroEntrenamiento) {
        this.numeroEntrenamiento = numeroEntrenamiento;
    }

    public List<JugadorResultadoDTO> getJugadores() {
        return jugadores;
    }

    public void setJugadores(List<JugadorResultadoDTO> jugadores) {
        this.jugadores = jugadores;
    }
}
