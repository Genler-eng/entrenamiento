package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.Entity.Jugador;
import com.entrenamiento.entrenamiento.Repository.EntrenamientoRepository;
import com.entrenamiento.entrenamiento.dto.EntrenamientoResponseDTO;
import com.entrenamiento.entrenamiento.dto.EstadisticasJugadorDTO;

@Service
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;

    private static final double PESO_POTENCIA = 0.20;
    private static final double PESO_VELOCIDAD = 0.30;
    private static final double PESO_PASES = 0.50;

    public EntrenamientoService(EntrenamientoRepository entrenamientoRepository) {
        this.entrenamientoRepository = entrenamientoRepository;
    }

    public EntrenamientoResponseDTO registrarEntrenamiento(EntrenamientoResponseDTO request) {
        List<Entrenamiento> registros = new ArrayList<>();

        for (EstadisticasJugadorDTO j : request.getJugadores()) {
            double resultado = (j.getPotenciaTiro() * PESO_POTENCIA)
                             + (j.getVelocidad() * PESO_VELOCIDAD)
                             + (j.getPases() * PESO_PASES);

            Entrenamiento nuevoRegistro = new Entrenamiento();
            nuevoRegistro.setNumeroEntrenamiento(request.getNumeroEntrenamiento());

            Jugador jugador = new Jugador();
            jugador.setNombre(j.getNombre());
            nuevoRegistro.setJugador(jugador);

            nuevoRegistro.setPotenciaTiro(j.getPotenciaTiro());
            nuevoRegistro.setVelocidad(j.getVelocidad());
            nuevoRegistro.setPases(j.getPases());
            nuevoRegistro.setResultado(resultado);

            registros.add(nuevoRegistro);
        }

        entrenamientoRepository.saveAll(registros);
        return new EntrenamientoResponseDTO("entrenamiento numero " + request.getNumeroEntrenamiento() + " guardado correctamente");
    }

    public boolean yaEstanLosTresEntrenamientos() {
        return !entrenamientoRepository.findByNumeroEntrenamiento(1).isEmpty()
            && !entrenamientoRepository.findByNumeroEntrenamiento(2).isEmpty()
            && !entrenamientoRepository.findByNumeroEntrenamiento(3).isEmpty();
    }

    public List<Entrenamiento> obtenerTodosLosRegistros() {
        return entrenamientoRepository.findAll();
    }
}