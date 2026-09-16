package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.dto.AlineacionTitularResponseDTO;
import com.entrenamiento.entrenamiento.dto.JugadorTitularResponseDTO;

@Service
public class JugadorService {

    private final EntrenamientoService entrenamientoService;

    public JugadorService(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    public AlineacionTitularResponseDTO obtenerEquipoTitular() {
        if (!entrenamientoService.yaEstanLosTresEntrenamientos()) {
            return new AlineacionTitularResponseDTO(
                "todavia no estan los 3 entrenamientos de la semana, no se puede calcular el equipo titular", 
                null
            );
        }

        List<Entrenamiento> registros = entrenamientoService.obtenerTodosLosRegistros();
        List<JugadorTitularResponseDTO> tablaPromedios = calcularPromedios(registros);
        
        ordenarDeMayorAMenor(tablaPromedios);

        List<JugadorTitularResponseDTO> titulares = tablaPromedios.subList(0, Math.min(5, tablaPromedios.size()));

        return new AlineacionTitularResponseDTO(null, titulares);
    }

    private List<JugadorTitularResponseDTO> calcularPromedios(List<Entrenamiento> registros) {
        Map<String, Double> sumaPorJugador = new HashMap<>();
        Map<String, Integer> conteoPorJugador = new HashMap<>();

        for (Entrenamiento reg : registros) {
            String nombre = reg.getJugador().getNombre();
            
            sumaPorJugador.put(nombre, sumaPorJugador.getOrDefault(nombre, 0.0) + reg.getResultado());
            conteoPorJugador.put(nombre, conteoPorJugador.getOrDefault(nombre, 0) + 1);
        }

        List<JugadorTitularResponseDTO> promedios = new ArrayList<>();
        for (String nombre : sumaPorJugador.keySet()) {
            double promedio = sumaPorJugador.get(nombre) / conteoPorJugador.get(nombre);
            promedios.add(new JugadorTitularResponseDTO(nombre, promedio));
        }

        return promedios;
    }

    private void ordenarDeMayorAMenor(List<JugadorTitularResponseDTO> lista) {
        int n = lista.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (lista.get(j).getResultadoPromedio() < lista.get(j + 1).getResultadoPromedio()) {
                    JugadorTitularResponseDTO aux = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, aux);
                }
            }
        }
    }
}