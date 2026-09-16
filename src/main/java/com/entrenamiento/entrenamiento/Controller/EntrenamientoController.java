package com.entrenamiento.entrenamiento.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import com.entrenamiento.entrenamiento.dto.EntrenamientoResponseDTO;
import com.entrenamiento.entrenamiento.Service.EntrenamientoService;

@RestController
@RequestMapping("/entrenamientos")
public class EntrenamientoController {

    private final EntrenamientoService entrenamientoService;

    public EntrenamientoController(EntrenamientoService entrenamientoService) {
        this.entrenamientoService = entrenamientoService;
    }

    @PostMapping
    public ResponseEntity<EntrenamientoResponseDTO> registrar(@Valid @RequestBody EntrenamientoResponseDTO request) {
        return new ResponseEntity<>(entrenamientoService.registrarEntrenamiento(request), HttpStatus.CREATED);
    }
}