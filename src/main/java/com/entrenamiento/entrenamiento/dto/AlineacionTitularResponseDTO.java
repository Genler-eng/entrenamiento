package com.entrenamiento.entrenamiento.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlineacionTitularResponseDTO {
    private String mensaje;
    private List<JugadorTitularResponseDTO> titulares;
}