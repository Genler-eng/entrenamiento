package com.entrenamiento.entrenamiento.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.entrenamiento.entrenamiento.dto.EntrenamientoRequestDTO;
import com.entrenamiento.entrenamiento.dto.JugadorResultadoDTO;
import com.entrenamiento.entrenamiento.dto.JugadorTitularDto;
import com.entrenamiento.entrenamiento.Entity.Entrenamiento;
import com.entrenamiento.entrenamiento.Repository.EntrenamientoRepository;

@Service
public class EntrenamientoService {

    private final EntrenamientoRepository entrenamientoRepository;

    public EntrenamientoService(EntrenamientoRepository entrenamientoRepository) {
        this.entrenamientoRepository = entrenamientoRepository;
    }

    // pesos de la formula que da el enunciado
    private static final double PESO_POTENCIA = 0.20;
    private static final double PESO_VELOCIDAD = 0.30;
    private static final double PESO_PASES = 0.50;

    public void registrarEntrenamiento(EntrenamientoRequestDTO request) {

        for (JugadorResultadoDTO j : request.getJugadores()) {

            double resultado = (j.getPotenciaTiro() * PESO_POTENCIA)
                    + (j.getVelocidad() * PESO_VELOCIDAD)
                    + (j.getPases() * PESO_PASES);

            String nombreJugador = j.getNombre().trim();

            Entrenamiento nuevoRegistro = new Entrenamiento(
                    request.getNumeroEntrenamiento(),
                    nombreJugador,
                    j.getPotenciaTiro(),
                    j.getVelocidad(),
                    j.getPases(),
                    resultado
            );

            entrenamientoRepository.save(nuevoRegistro);
        }
    }

    public Object obtenerEquipoTitular() {

        List<Entrenamiento> reg1 = entrenamientoRepository.findByNumeroEntrenamiento(1);
        List<Entrenamiento> reg2 = entrenamientoRepository.findByNumeroEntrenamiento(2);
        List<Entrenamiento> reg3 = entrenamientoRepository.findByNumeroEntrenamiento(3);

        if (reg1.isEmpty() || reg2.isEmpty() || reg3.isEmpty()) {
            return "todavia no estan los 3 entrenamientos de la semana, no se puede calcular el equipo titular";
        }

        List<Entrenamiento> todosLosRegistros = entrenamientoRepository.findAll();

        Map<String, Double> sumaPorJugador = new HashMap<>();
        Map<String, Integer> vecesPorJugador = new HashMap<>();

        for (Entrenamiento registro : todosLosRegistros) {
            String nombre = registro.getJugador();

            if (!sumaPorJugador.containsKey(nombre)) {
                sumaPorJugador.put(nombre, 0.0);
                vecesPorJugador.put(nombre, 0);
            }

            double sumaAnterior = sumaPorJugador.get(nombre);
            sumaPorJugador.put(nombre, sumaAnterior + registro.getResultado());

            int vecesAnterior = vecesPorJugador.get(nombre);
            vecesPorJugador.put(nombre, vecesAnterior + 1);
        }

        List<JugadorTitularDto> tablaPromedios = new ArrayList<>();

        for (String nombre : sumaPorJugador.keySet()) {
            double suma = sumaPorJugador.get(nombre);
            int veces = vecesPorJugador.get(nombre);
            double promedio = suma / veces;
            tablaPromedios.add(new JugadorTitularDto(nombre, promedio));
        }

        // ordenamos de mayor a menor promedio
        int n = tablaPromedios.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                double actual = tablaPromedios.get(j).getResultadoPromedio();
                double siguiente = tablaPromedios.get(j + 1).getResultadoPromedio();

                if (actual < siguiente) {
                    JugadorTitularDto aux = tablaPromedios.get(j);
                    tablaPromedios.set(j, tablaPromedios.get(j + 1));
                    tablaPromedios.set(j + 1, aux);
                }
            }
        }

        List<JugadorTitularDto> titulares = new ArrayList<>();
        int cantidadTitulares = Math.min(5, tablaPromedios.size());

        for (int i = 0; i < cantidadTitulares; i++) {
            titulares.add(tablaPromedios.get(i));
        }

        return titulares;
    }
}