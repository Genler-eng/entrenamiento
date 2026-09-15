package com.entrenamiento.entrenamiento.dto;

//esto es lo que devuelve cuando piden el equipo titular
//solo nombre y el promedio , no hace falta mandar mas datos 

public class JugadorTitularDto {

private String jugador;
private double resultadoPromedio;

public JugadorTitularDto(String jugador, double resultadoPromedio){
    this.jugador = jugador;
    this.resultadoPromedio = resultadoPromedio;
}

public String getJugador(){
    return jugador;
}
public void setjugador(String jugador){
    this.jugador = jugador;
}
public double getresultadoPromedio(){
    return resultadoPromedio;
}
public void setresultadoPromedio(double resultadoPromedio){
    this.resultadoPromedio = resultadoPromedio;
}

}
