package com.entrenamiento.entrenamiento.dto;

public class JugadorTitularDto {

    private String jugador;
    private double resultadoPromedio;

    public JugadorTitularDto(String jugador, double resultadoPromedio) {
        this.jugador = jugador;
        this.resultadoPromedio = resultadoPromedio;
    }

    public String getJugador() {
        return jugador;
    }

    public void setJugador(String jugador) {
        this.jugador = jugador;
    }

    public double getResultadoPromedio() {
        return resultadoPromedio;
    }

    public void setResultadoPromedio(double resultadoPromedio) {
        this.resultadoPromedio = resultadoPromedio;
    }
}