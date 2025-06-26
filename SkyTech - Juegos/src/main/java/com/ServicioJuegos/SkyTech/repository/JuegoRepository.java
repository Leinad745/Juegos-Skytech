package com.ServicioJuegos.SkyTech.repository;

import org.springframework.stereotype.Repository;

import java.time.Year;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ServicioJuegos.SkyTech.model.Juego;

@Repository
// Interfaz que extiende de JpaRepository para realizar operaciones CRUD en la entidad Juego
public interface JuegoRepository extends JpaRepository<Juego, Long> {

    @Query(value = "SELECT * FROM juego ORDER BY fecha_lanzamiento DESC LIMIT 1", nativeQuery = true)
    public Juego buscarJuegoMasReciente();
    
    @Query(value = "SELECT * FROM juego WHERE EXTRACT(YEAR FROM fecha_lanzamiento) = ?1", nativeQuery = true)
    public List<Juego> findByYear(Year year);

    public Juego findByTituloJuego(String tituloJuego);
    List<Juego> findByDesarrollador(String desarrollador);
    List<Juego> findByGenero(String genero);
    List<Juego> findByClasificacionESRB(String clasificacionESRB);

}
