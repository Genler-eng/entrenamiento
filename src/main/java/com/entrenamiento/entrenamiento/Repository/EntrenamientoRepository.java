package com.entrenamiento.entrenamiento.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.entrenamiento.entrenamiento.Entity.Entrenamiento;


public interface EntrenamientoRepository extends JpaRepository<Entrenamiento, Long> {
    //Este metedo le pide al spring que busque en la base de datos todos los registros del entrenamiento
    List<Entrenamiento> findByNumeroEntrenamiento(Integer numeroEntrenamiento);
}
