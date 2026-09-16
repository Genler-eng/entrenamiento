
package com.entrenamiento.entrenamiento.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.entrenamiento.entrenamiento.dto.AlineacionTitularResponseDTO;
import com.entrenamiento.entrenamiento.Service.JugadorService;

@RestController
@RequestMapping("/jugadores")
public class JugadorController {

    private final JugadorService jugadorService;

    public JugadorController(JugadorService jugadorService) {
        this.jugadorService = jugadorService;
    }

    @GetMapping("/equipo-titular")
    public ResponseEntity<AlineacionTitularResponseDTO> obtenerEquipoTitular() {
        return ResponseEntity.ok(jugadorService.obtenerEquipoTitular());
    }
}