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

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;
    private final JugadorRepository jugadorRepository;

    public EntrenamientoResponseDTO registrarEntrenamiento(EntrenamientoSaveRequestDTO request) {
        List<Entrenamiento> registros = new ArrayList<>();
        List<EstadisticasJugadorDTO> jugadoresDto = request.getJugadores();

        for (int i = 0; i < jugadoresDto.size(); i++) {
            EstadisticasJugadorDTO j = jugadoresDto.get(i);
            
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

            
            Entrenamiento nuevoRegistro = new Entrenamiento(
                 null,
                request.getNumeroEntrenamiento(),
                jugador,
                j.getPotenciaTiro(),
                j.getVelocidad(),
                j.getPases(),
                resultado
            );

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