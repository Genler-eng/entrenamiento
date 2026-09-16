package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.Entity.Jugador;
import com.entrenamiento.entrenamiento.dto.AlineacionTitularResponseDTO;
import com.entrenamiento.entrenamiento.dto.JugadorTitularResponseDTO;

@Service
public class JugadorService {

    private final EntrenamientoService entrenamientoService;

    public JugadorService(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    public AlineacionTitularResponseDTO obtenerTitulares() {
        // Validar si están los 3 entrenamientos obligatorios
        if (!entrenamientoService.yaEstanLosTresEntrenamientos()) {
            return new AlineacionTitularResponseDTO("No hay suficiente información para determinar el equipo titular.", null);
        }

        List<Entrenamiento> todos = entrenamientoService.obtenerTodosLosRegistros();

        // Agrupar entrenamientos por jugador y calcular su promedio
        Map<Jugador, List<Entrenamiento>> porJugador = todos.stream()
                .collect(Collectors.groupingBy(Entrenamiento::getJugador));

        List<JugadorTitularResponseDTO> ranking = new ArrayList<>();

        for (Map.Entry<Jugador, List<Entrenamiento>> entry : porJugador.entrySet()) {
            double promedio = entry.getValue().stream()
                    .mapToDouble(Entrenamiento::getResultado)
                    .average()
                    .orElse(0.0);

            // Redondear a 2 decimales
            promedio = Math.round(promedio * 100.0) / 100.0;

            ranking.add(new JugadorTitularResponseDTO(entry.getKey().getNombre(), promedio));
        }

        // Ordenar de mayor a menor puntaje y tomar los 5 mejores
        List<JugadorTitularResponseDTO> titulares = ranking.stream()
                .sorted(Comparator.comparingDouble(JugadorTitularResponseDTO::getPuntaje).reversed())
                .limit(5)
                .collect(Collectors.toList());

        return new AlineacionTitularResponseDTO("Equipo titular generado correctamente", titulares);
    }
}