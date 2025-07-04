package com.ServicioJuegos.SkyTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.ServicioJuegos.SkyTech.service.JuegoService;
import com.ServicioJuegos.SkyTech.model.Juego;
import org.springframework.web.bind.annotation.*;

import java.time.Year;
import java.util.List;

@RestController
@RequestMapping("/api/v3/juegos")
public class JuegoController {
    @Autowired
    private JuegoService juegoService;
    // GET Methods
    @GetMapping
    public ResponseEntity<List<Juego>> listarJuegos() {
        List<Juego> juegos = juegoService.findAll();
        if (juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(juegos);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Juego> buscarPorId(@PathVariable Long id) {
        try {
            Juego juego = juegoService.findById(id);
            return ResponseEntity.ok(juego);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/año/{anio}")
    public ResponseEntity<List<Juego>> getJuegosByYear(@PathVariable Year anio) {
        List<Juego> juegos = juegoService.findByYear(anio);
        if (juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(juegos);
    }
    
    @GetMapping("/reciente")
    public ResponseEntity<Juego> buscarJuegoMasReciente() {
        Juego juegoReciente = juegoService.buscarJuegoMasReciente();
        if (juegoReciente == null) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(juegoReciente);
    }

    @GetMapping("/titulo/{tituloJuego}")
    public Juego findByTituloJuego(@PathVariable String tituloJuego) {
        return juegoService.findByTituloJuego(tituloJuego);
    }

    @GetMapping("/desarrollador/{desarrollador}")
    public ResponseEntity<List<Juego>> findByDesarrollador(@PathVariable String desarrollador) {
        List<Juego> juegos = juegoService.findByDesarrollador(desarrollador);
        if (juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(juegos);
    }

    @GetMapping("/genero/{genero}")
    public ResponseEntity<List<Juego>> findByGenero(@PathVariable String genero) {
        List<Juego> juegos = juegoService.findByGenero(genero);
        if (juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(juegos);
    }

    @GetMapping("/clasificacion/{clasificacionESRB}")
    public ResponseEntity<List<Juego>> findByClasificacionESRB(@PathVariable String clasificacionESRB) {
        List<Juego> juegos = juegoService.findByClasificacionESRB(clasificacionESRB);
        if (juegos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } 
        return ResponseEntity.ok(juegos);
    }

    // POST Methods
    @PostMapping
    public ResponseEntity<Juego> guardarJuego(@RequestBody Juego juego) {
        Juego nuevoJuego = juegoService.save(juego);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoJuego);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<Juego> actualizarJuego(@PathVariable Long id, @RequestBody Juego juego) {
        try {
            Juego juegoAct = this.juegoService.findById(id);

            juegoAct.setTituloJuego(juego.getTituloJuego());
            juegoAct.setDesarrollador(juego.getDesarrollador());
            juegoAct.setClasificacionESRB(juego.getClasificacionESRB());
            juegoAct.setGenero(juego.getGenero());
            juegoAct.setAnioLanzamiento(juego.getAnioLanzamiento());
            juegoAct.setPublicador(juego.getPublicador());
            juegoAct.setDisponibilidad(juego.getDisponibilidad());
            juegoAct.setDescripcion(juego.getDescripcion());
            return ResponseEntity.ok(juegoService.save(juegoAct));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE Method
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarJuego(@PathVariable Long id) {
        try {
            juegoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}  
