package com.entrenamiento.entrenamiento.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import com.entrenamiento.entrenamiento.dto.EntrenamientoRequestDTO;
import com.entrenamiento.entrenamiento.Service.EntrenamientoService;

@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping
    public String registrar(@Valid @RequestBody EntrenamientoRequestDTO request) {
        entrenamientoService.registrarEntrenamiento(request);
        return "entrenamiento numero " + request.getNumeroEntrenamiento() + " guardado correctamente";
    }

    @GetMapping("/equipo-titular")
    public Object equipoTitular() {
        return entrenamientoService.obtenerEquipoTitular();
    }
}