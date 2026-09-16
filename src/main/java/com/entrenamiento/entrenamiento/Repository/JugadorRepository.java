package com.entrenamiento.entrenamiento.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entrenamiento.entrenamiento.Entity.Jugador;

public interface JugadorRepository extends JpaRepository<Jugador, Long> {
    Optional<Jugador> findByNombre(String nombre);
}
