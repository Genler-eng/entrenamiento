package com.entrenamiento.entrenamiento.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entrenamientos")
@Getter
@Setter
@NoArgsConstructor
public class Entrenamiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer numeroEntrenamiento;

    @ManyToOne
    @JoinColumn(name = "jugador_id", nullable = false)
    private Jugador jugador;

    private Integer potenciaTiro;
    private Integer velocidad;
    private Integer pases;
    private Double resultado;

    public Entrenamiento(Integer numeroEntrenamiento, Jugador jugador, Integer potenciaTiro, Integer velocidad, Integer pases, Double resultado) {
        this.numeroEntrenamiento = numeroEntrenamiento;
        this.jugador = jugador;
        this.potenciaTiro = potenciaTiro;
        this.velocidad = velocidad;
        this.pases = pases;
        this.resultado = resultado;
    }
}