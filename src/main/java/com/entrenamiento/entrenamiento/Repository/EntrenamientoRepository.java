package com.entrenamiento.entrenamiento.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entrenamiento.entrenamiento.Entity.Entrenamiento;

public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    List<Entrenamiento> findByNumeroEntrenamiento(Integer numeroEntrenamiento);
}