package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.Entity.Jugador;
import com.entrenamiento.entrenamiento.Repository.EntrenamientoRepository;
import com.entrenamiento.entrenamiento.Repository.JugadorRepository;
import com.entrenamiento.entrenamiento.dto.EntrenamientoResponseDTO;
import com.entrenamiento.entrenamiento.dto.EntrenamientoSaveRequestDTO;
import com.entrenamiento.entrenamiento.dto.EstadisticasJugadorDTO;

@Service
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;
    private final JugadorRepository jugadorRepository;

    public EntrenamientoService(EntrenamientoRepository entrenamientoRepository, JugadorRepository jugadorRepository) {
        this.entrenamientoRepository = entrenamientoRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public EntrenamientoResponseDTO registrarEntrenamiento(EntrenamientoSaveRequestDTO request) {
        List<Entrenamiento> registros = new ArrayList<>();
        List<EstadisticasJugadorDTO> jugadoresDto = request.getJugadores();

        for (int i = 0; i < jugadoresDto.size(); i++) {
            EstadisticasJugadorDTO j = jugadoresDto.get(i);
            
            // Cálculo directo usando los porcentajes de forma literal en la fórmula
            double resultado = (j.getPotenciaTiro() * 0.20)
                             + (j.getVelocidad() * 0.30)
                             + (j.getPases() * 0.50);

            Optional<Jugador> optJugador = jugadorRepository.findByNombre(j.getNombre());
            Jugador jugador;

            if (optJugador.isPresent()) {
                jugador = optJugador.get();
            } else {
                Jugador nuevoJugador = new Jugador();
                nuevoJugador.setNombre(j.getNombre());
                jugador = jugadorRepository.save(nuevoJugador);
            }

            Entrenamiento nuevoRegistro = new Entrenamiento();
            nuevoRegistro.setNumeroEntrenamiento(request.getNumeroEntrenamiento());
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