package com.entrenamiento.entrenamiento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Esta clase representa cada registro de un jugador en un entrenamiento
// osea, una fila = un jugador en un entrenamiento especifico (1, 2 o 3)
@Entity
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEntrenamiento; // 1, 2 o 3
    private String jugador;
    private Integer potenciaTiro;
    private Integer velocidad;
    private Integer pases;
    private Double resultado; // esto se calcula solo, no lo manda el usuario

    public Entrenamiento() {
    }

    public Entrenamiento(Integer numeroEntrenamiento, String jugador, Integer potenciaTiro,
                          Integer velocidad, Integer pases, Double resultado) {
        this.numeroEntrenamiento = numeroEntrenamiento;
        this.jugador = jugador;
        this.potenciaTiro = potenciaTiro;
        this.velocidad = velocidad;
        this.pases = pases;
        this.resultado = resultado;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getNumeroEntrenamiento() { return numeroEntrenamiento; }
    public void setNumeroEntrenamiento(Integer numeroEntrenamiento) { this.numeroEntrenamiento = numeroEntrenamiento; }

    public String getJugador() { return jugador; }
    public void setJugador(String jugador) { this.jugador = jugador; }

    public Integer getPotenciaTiro() { return potenciaTiro; }
    public void setPotenciaTiro(Integer potenciaTiro) { this.potenciaTiro = potenciaTiro; }

    public Integer getVelocidad() { return velocidad; }
    public void setVelocidad(Integer velocidad) { this.velocidad = velocidad; }

    public Integer getPases() { return pases; }
    public void setPases(Integer pases) { this.pases = pases; }

    public Double getResultado() { return resultado; }
    public void setResultado(Double resultado) { this.resultado = resultado; }
}
