package com.ServicioJuegos.SkyTech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.time.Year;

@Entity
@Table(name = "juego")
@Data // Genera automáticamente los métodos getter y setter, así como equals(), hashCode() y toString()
@NoArgsConstructor
@AllArgsConstructor
public class Juego {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idJuego;

    @Column(unique=true, nullable = false)
    private String tituloJuego;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private String genero;

    @Column(nullable = false)
    private Year anioLanzamiento;

    @Column(nullable = false)
    private String desarrollador;

    @Column(nullable = false)
    private String publicador;

    @Column(nullable = false, length = 1)
    private String clasificacionESRB;

    @Column(nullable = false)
    private Boolean disponibilidad;
}
