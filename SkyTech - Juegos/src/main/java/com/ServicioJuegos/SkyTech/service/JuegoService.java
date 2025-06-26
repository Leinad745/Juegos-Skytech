package com.ServicioJuegos.SkyTech.service;

import com.ServicioJuegos.SkyTech.repository.JuegoRepository;
import com.ServicioJuegos.SkyTech.model.Juego;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.time.Year;

@Service
@Transactional // Anotación que indica que esta clase es un servicio y que las operaciones en ella son transaccionales
public class JuegoService {

    @Autowired
    private JuegoRepository juegoRepository;

    public List<Juego> findAll() {
        return juegoRepository.findAll();
    }

    public Juego findById(Long id) {
        return juegoRepository.findById(id).get();
    }

    public Juego save(Juego juego) {
        return juegoRepository.save(juego);
    }

    public List<Juego> findByYear(Year year) {
        return juegoRepository.findByYear(year);
    }

    public Juego buscarJuegoMasReciente() {
        return juegoRepository.buscarJuegoMasReciente();
    }

    public void delete(Long id) {
        juegoRepository.deleteById(id);
    }

    // El metodo findByTituloJuego busca juegos por su título sin embargo el JPA no reconoce la funcion
    // Lo resolvi, no generan funciones lol
    public Juego findByTituloJuego(String tituloJuego) {
        return juegoRepository.findByTituloJuego(tituloJuego);
    }

    public List<Juego> findByDesarrollador(String desarrollador) {
        return juegoRepository.findByDesarrollador(desarrollador);
    }

    public List<Juego> findByGenero(String genero) {
        return juegoRepository.findByGenero(genero);
    }

    public List<Juego> findByClasificacionESRB(String clasificacionESRB) {
        return juegoRepository.findByClasificacionESRB(clasificacionESRB);
    }
}
