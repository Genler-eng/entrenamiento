package com.entrenamiento.entrenamiento.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntrenamientoResponseDTO {
    private String mensaje;

    public EstadisticasJugadorDTO[] getJugadores() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJugadores'");
    }

    public Integer getNumeroEntrenamiento() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getNumeroEntrenamiento'");
    }
}