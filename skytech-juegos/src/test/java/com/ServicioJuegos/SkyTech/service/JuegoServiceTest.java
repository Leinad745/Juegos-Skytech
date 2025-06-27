package com.ServicioJuegos.SkyTech.service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.ServicioJuegos.SkyTech.model.Juego;
import com.ServicioJuegos.SkyTech.repository.JuegoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;
import java.time.Year;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class JuegoServiceTest {

    @InjectMocks // Inyecta el servicio de Juego para ser probado.
    private JuegoService juegoService;

    @Mock // Crea un dummy del repositorio para simular su comportamiento.
    private JuegoRepository juegoRepository;

    @Test
    public void testFindAll() {

        Year anioLanzamiento = Year.of(2017);
        //Comportamiento del mock
        when(juegoRepository.findAll()).thenReturn(List.of(new Juego(1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", anioLanzamiento, "Nintendo EPD", "Nintendo", "E10+", true)));
        
        List<Juego> juegos = juegoService.findAll();
        // Verifica que la lista devuelta no sea nula y contenga exactamente un juego.
        assertNotNull(juegos);
        assertEquals(1, juegos.size());
        }

    @Test
    public void testFindById() {
        Long id = 1L;
        Year anioLanzamiento = Year.of(2017);
        Juego juego = new Juego(id, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", anioLanzamiento, "Nintendo EPD", "Nintendo", "E10+", true
        );

        when(juegoRepository.findById(id)).thenReturn(Optional.of(juego));
        Juego found = juegoService.findById(id);

        assertNotNull(found);
        assertEquals(juego.getIdJuego(), found.getIdJuego());

}

    @Test
    public void testSave() {
        Year anioLanzamiento = Year.of(2017);
        Juego juego = new Juego(1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", anioLanzamiento, "Nintendo EPD", "Nintendo", "E10+", true
        );

        when(juegoRepository.save(juego)).thenReturn(juego);
        Juego saved = juegoService.save(juego);

        assertNotNull(saved);
        assertEquals(juego.getTituloJuego(), saved.getTituloJuego());
    }

    @Test
    public void testFindByYear() {
        Year anioLanzamiento = Year.of(2017);
        Juego juego = new Juego(
            1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", anioLanzamiento, "Nintendo EPD", "Nintendo", "E10+", true
        );

        when(juegoRepository.findByYear(anioLanzamiento)).thenReturn(List.of(juego));

        List<Juego> juegos = juegoService.findByYear(anioLanzamiento);
        assertNotNull(juegos);
        assertEquals(1, juegos.size());
        assertEquals(juego.getTituloJuego(), juegos.get(0).getTituloJuego());
    }

    @Test
    public void testBuscarJuegoMasReciente() {
        Year year = Year.of(2023);
        Juego juegoReciente = new Juego(
            1L, "Juego Reciente", "Descripción del juego reciente", 
            "Aventura", year, "Desarrollador A", "Publicador A", "E10+", true
        );

        when(juegoRepository.buscarJuegoMasReciente()).thenReturn(juegoReciente);

        Juego resultado = juegoService.buscarJuegoMasReciente();
        assertNotNull(resultado);
        assertEquals(juegoReciente.getTituloJuego(), resultado.getTituloJuego());
    }

    @Test
    public void testFindByTituloJuego() {
        String tituloJuego = "The Legend of Zelda: Breath of the Wild";
        Year year = Year.of(2017);

        Juego juego = new Juego(
            1L, tituloJuego, "Un juego de aventura en un mundo abierto", 
            "Aventura", year, "Nintendo EPD", "Nintendo", "E10+", true
        );

        when(juegoRepository.findByTituloJuego(tituloJuego)).thenReturn(juego);
        Juego found = juegoService.findByTituloJuego(tituloJuego);
        assertNotNull(found);
        assertEquals(tituloJuego, found.getTituloJuego());
    }

    @Test
    public void testDelete() {
        Long id = 1L;

        doNothing().when(juegoRepository).deleteById(id);
        juegoService.delete(id);

        verify(juegoRepository, times(1)).deleteById(id);
    }
    
    /*
     * Se decidio no implementar todos los metodos de busqueda
     * Debido a que son redundates y no aportan valor significativo a las pruebas unitarias
     */
}