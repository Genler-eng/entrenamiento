package com.entrenamiento.entrenamiento.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entrenamiento.entrenamiento.Service.JugadorService;
import com.entrenamiento.entrenamiento.dto.AlineacionTitularResponseDTO;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("/titulares")
    public AlineacionTitularResponseDTO obtenerTitulares() {
        return jugadorService.obtenerTitulares();
    }
}