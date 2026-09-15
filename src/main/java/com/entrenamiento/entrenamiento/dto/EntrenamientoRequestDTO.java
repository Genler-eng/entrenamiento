package com.entrenamiento.entrenamiento.dto;

import java.util.List;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;

// Este es el que representa todo el body del POST /entrenamientos
// trae el numero de entrenamiento y la lista de jugadores con sus datos
public class EntrenamientoRequestDTO {

    @NotNull(message = "Debes indicar el número de entrenamiento (1, 2 o 3)")
    private Integer numeroEntrenamiento;

    @NotEmpty(message = "Debes enviar los resultados de al menos un jugador")
    @Valid // esto hace que valide tambien cada jugador de la lista, no solo que no este vacia
    private List<JugadorResultadoDTO> jugadores;

    public Integer getNumeroEntrenamiento() { return numeroEntrenamiento; }
    public void setNumeroEntrenamiento(Integer numeroEntrenamiento) { this.numeroEntrenamiento = numeroEntrenamiento; }

    public List<JugadorResultadoDTO> getJugadores() { return jugadores; }
    public void setJugadores(List<JugadorResultadoDTO> jugadores) { this.jugadores = jugadores; }
}
