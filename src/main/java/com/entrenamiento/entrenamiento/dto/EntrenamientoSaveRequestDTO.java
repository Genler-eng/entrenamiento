package com.entrenamiento.entrenamiento.dto;

import java.util.List;
import lombok.Data;

@Data
public class EntrenamientoSaveRequestDTO {
    private Integer numeroEntrenamiento;
    private List<EstadisticasJugadorDTO> jugadores;
}
