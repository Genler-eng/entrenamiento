package com.entrenamiento.entrenamiento.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EntrenamientoSaveRequestDTO {
    private Integer numeroEntrenamiento;
    private List<EstadisticasJugadorDTO> jugadores;

    public Integer getNumeroEntrenamiento() { return numeroEntrenamiento; }
    public List<EstadisticasJugadorDTO> getJugadores() { return jugadores; }
}