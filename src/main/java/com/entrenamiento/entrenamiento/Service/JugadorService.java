package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.Entity.Jugador;
import com.entrenamiento.entrenamiento.dto.AlineacionTitularResponseDTO;
import com.entrenamiento.entrenamiento.dto.JugadorTitularResponseDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JugadorService {

    private final EntrenamientoService entrenamientoService;

    public AlineacionTitularResponseDTO obtenerTitulares() {
        if (!entrenamientoService.yaEstanLosTresEntrenamientos()) {
            return new AlineacionTitularResponseDTO("No hay suficiente información para determinar el equipo titular.", null);
        }

        List<Entrenamiento> todos = entrenamientoService.obtenerTodosLosRegistros();

        Map<Jugador, List<Entrenamiento>> porJugador = new HashMap<>();
        for (int i = 0; i < todos.size(); i++) {
            Entrenamiento ent = todos.get(i);
            Jugador jug = ent.getJugador();
            
            List<Entrenamiento> listaJugador = porJugador.get(jug);
            if (listaJugador == null) {
                listaJugador = new ArrayList<>();
                porJugador.put(jug, listaJugador);
            }
            listaJugador.add(ent);
        }

        List<JugadorTitularResponseDTO> ranking = new ArrayList<>();

        for (Map.Entry<Jugador, List<Entrenamiento>> entry : porJugador.entrySet()) {
            List<Entrenamiento> listaEntrenamientos = entry.getValue();
            double suma = 0.0;
            
            for (int i = 0; i < listaEntrenamientos.size(); i++) {
                suma += listaEntrenamientos.get(i).getResultado();
            }

            double promedio = 0.0;
            if (listaEntrenamientos.size() > 0) {
                promedio = suma / listaEntrenamientos.size();
            }

            promedio = Math.round(promedio * 100.0) / 100.0;
            
            JugadorTitularResponseDTO titularDto = new JugadorTitularResponseDTO(entry.getKey().getNombre(), promedio);
            ranking.add(titularDto);
        }

        for (int i = 0; i < ranking.size() - 1; i++) {
            for (int j = 0; j < ranking.size() - 1 - i; j++) {
                JugadorTitularResponseDTO actual = ranking.get(j);
                JugadorTitularResponseDTO siguiente = ranking.get(j + 1);
                
                if (actual.getPuntaje() < siguiente.getPuntaje()) {
                    ranking.set(j, siguiente);
                    ranking.set(j + 1, actual);
                }
            }
        }

        List<JugadorTitularResponseDTO> titulares = new ArrayList<>();
        int limite = 5;
        if (ranking.size() < 5) {
            limite = ranking.size();
        }

        for (int i = 0; i < limite; i++) {
            titulares.add(ranking.get(i));
        }

        return new AlineacionTitularResponseDTO("Equipo titular generado correctamente", titulares);
    }
}