package com.entrenamiento.entrenamiento.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.entrenamiento.Service.EntrenamientoService;
import com.entrenamiento.entrenamiento.dto.EntrenamientoResponseDTO;
import com.entrenamiento.entrenamiento.dto.EntrenamientoSaveRequestDTO;

@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping
    public EntrenamientoResponseDTO registrar(@RequestBody EntrenamientoSaveRequestDTO request) {
        return entrenamientoService.registrarEntrenamiento(request);
    }
}