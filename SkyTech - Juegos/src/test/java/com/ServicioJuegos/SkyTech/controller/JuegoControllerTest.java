package com.ServicioJuegos.SkyTech.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import com.ServicioJuegos.SkyTech.model.Juego;
import com.ServicioJuegos.SkyTech.service.JuegoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Year;
import java.util.List;

@WebMvcTest(JuegoController.class)
public class JuegoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JuegoService juegoService;


    @Autowired
    private ObjectMapper objectMapper;
    
    private Juego juego1;
    private Juego juego2;

    @BeforeEach
    void setUp() {
        juego1 = new Juego(
            1L, "The Legend of Zelda: Breath of the Wild", "Un juego de aventura en un mundo abierto", 
            "Aventura", Year.of(2017), "Nintendo EPD", "Nintendo", "E", true
        );

        juego2 = new Juego(2L, "Jet Set Radio Future", "Un juego de carreras y acción en un mundo futurista",
            "Carreras", Year.of(2000), "Smilebit", "Sega", "T", true);
    }

    @Test
    public void testGetAllJuegos() throws Exception {
        when(juegoService.findAll()).thenReturn(List.of(juego1));
        mockMvc.perform(get("/api/v3/juegos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$[0].descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$[0].genero").value("Aventura"))
                .andExpect(jsonPath("$[0].anioLanzamiento").value(2017))
                .andExpect(jsonPath("$[0].desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$[0].publicador").value("Nintendo"))
                .andExpect(jsonPath("$[0].clasificacionESRB").value("E"))
                .andExpect(jsonPath("$[0].disponibilidad").value(true));
    }

    @Test
    public void testBuscarPorId() throws Exception {
        when(juegoService.findById(1L)).thenReturn(juego1);

        mockMvc.perform(get("/api/v3/juegos/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idJuego").value(1))
                .andExpect(jsonPath("$.tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$.descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$.genero").value("Aventura"))
                .andExpect(jsonPath("$.anioLanzamiento").value(2017))
                .andExpect(jsonPath("$.desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$.publicador").value("Nintendo"))
                .andExpect(jsonPath("$.clasificacionESRB").value("E"))
                .andExpect(jsonPath("$.disponibilidad").value(true));
    }

    @Test
    public void testGetJuegosByYear() throws Exception {
        Year year = Year.of(2017);
        when(juegoService.findByYear(year)).thenReturn(List.of(juego1));
        mockMvc.perform(get("/api/v3/juegos/año/2017"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].idJuego").value(1))
                .andExpect(jsonPath("$[0].tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$[0].descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$[0].genero").value("Aventura"))
                .andExpect(jsonPath("$[0].anioLanzamiento").value(2017))
                .andExpect(jsonPath("$[0].desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$[0].publicador").value("Nintendo"))
                .andExpect(jsonPath("$[0].clasificacionESRB").value("E"))
                .andExpect(jsonPath("$[0].disponibilidad").value(true));
    }

    @Test
    public void testBuscarJuegoMasReciente() throws Exception {
                                                //Buscar solucion
        when(juegoService.buscarJuegoMasReciente()).thenReturn(juego1);
        mockMvc.perform(get("/api/v3/juegos/reciente"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idJuego").value(1))
                .andExpect(jsonPath("$.tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$.descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$.genero").value("Aventura"))
                .andExpect(jsonPath("$.anioLanzamiento").value(2017))
                .andExpect(jsonPath("$.desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$.publicador").value("Nintendo"))
                .andExpect(jsonPath("$.clasificacionESRB").value("E"))
                .andExpect(jsonPath("$.disponibilidad").value(true));
    }

    @Test
    public void testFindByTituloJuego() throws Exception {
        when(juegoService.findByTituloJuego("The Legend of Zelda: Breath of the Wild")).thenReturn(juego1);
        mockMvc.perform(get("/api/v3/juegos/titulo/The Legend of Zelda: Breath of the Wild"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idJuego").value(1))
                .andExpect(jsonPath("$.tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$.descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$.genero").value("Aventura"))
                .andExpect(jsonPath("$.anioLanzamiento").value(2017))
                .andExpect(jsonPath("$.desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$.publicador").value("Nintendo"))
                .andExpect(jsonPath("$.clasificacionESRB").value("E"))
                .andExpect(jsonPath("$.disponibilidad").value(true));
    }

    @Test
    public void testGuardarJuego() throws Exception {
        when(juegoService.save(any(Juego.class))).thenReturn(juego1);

        mockMvc.perform(post("/api/v3/juegos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(juego1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.idJuego").value(1))
                .andExpect(jsonPath("$.tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$.descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$.genero").value("Aventura"))
                .andExpect(jsonPath("$.anioLanzamiento").value(2017))
                .andExpect(jsonPath("$.desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$.publicador").value("Nintendo"))
                .andExpect(jsonPath("$.clasificacionESRB").value("E"))
                .andExpect(jsonPath("$.disponibilidad").value(true));
    }

    @Test
    public void testUpdateJuego() throws Exception {
        when(juegoService.findById(any(Long.class))).thenReturn(juego1);
        when(juegoService.save(any(Juego.class))).thenReturn(juego1);


        mockMvc.perform(put("/api/v3/juegos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(juego1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idJuego").value(1))
                .andExpect(jsonPath("$.tituloJuego").value("The Legend of Zelda: Breath of the Wild"))
                .andExpect(jsonPath("$.descripcion").value("Un juego de aventura en un mundo abierto"))
                .andExpect(jsonPath("$.genero").value("Aventura"))
                .andExpect(jsonPath("$.anioLanzamiento").value(2017))
                .andExpect(jsonPath("$.desarrollador").value("Nintendo EPD"))
                .andExpect(jsonPath("$.publicador").value("Nintendo"))
                .andExpect(jsonPath("$.clasificacionESRB").value("E"))
                .andExpect(jsonPath("$.disponibilidad").value(true));
    }

    @Test
    public void testDeleteJuego() throws Exception {
        doNothing().when(juegoService).delete(1L);
        mockMvc.perform(delete("/api/v3/juegos/1"))
                .andExpect(status().isNoContent());
        verify(juegoService, times(1)).delete(1L);
    }
}
