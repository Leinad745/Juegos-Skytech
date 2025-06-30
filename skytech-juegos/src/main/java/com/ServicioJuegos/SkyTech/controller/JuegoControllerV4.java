package com.ServicioJuegos.SkyTech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.MediaTypes;
import com.ServicioJuegos.SkyTech.service.JuegoService;

//import io.swagger.v3.oas.models.media.MediaType;

import com.ServicioJuegos.SkyTech.assemblers.JuegoModelAssembler;
import com.ServicioJuegos.SkyTech.model.Juego;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v4/juegos")
public class JuegoControllerV4 {
    @Autowired
    private JuegoService juegoService;

    @Autowired
    private JuegoModelAssembler assembler;

    // GET Methods
    @GetMapping(produces = MediaTypes.HAL_JSON_VALUE)
    public CollectionModel<EntityModel<Juego>> listarJuegos() {
        List<EntityModel<Juego>> juegos = juegoService.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
            
            return CollectionModel.of(juegos,
                linkTo(methodOn(JuegoControllerV4.class).listarJuegos()).withSelfRel());
            }
    
    @GetMapping(value = "/{id}", produces = MediaTypes.HAL_JSON_VALUE)
    public EntityModel<Juego> buscarPorId(@PathVariable Long id) {
        Juego juego = juegoService.findById(id);
        return assembler.toModel(juego);
    }
}  
