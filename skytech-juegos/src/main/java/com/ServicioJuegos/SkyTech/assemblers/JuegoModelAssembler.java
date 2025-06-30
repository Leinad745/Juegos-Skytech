package com.ServicioJuegos.SkyTech.assemblers;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.ServicioJuegos.SkyTech.controller.JuegoControllerV4;
import com.ServicioJuegos.SkyTech.model.Juego;

@Component
public class JuegoModelAssembler implements RepresentationModelAssembler<Juego, EntityModel<Juego>> {

    @Override
    public EntityModel<Juego> toModel(Juego juego) {
        return EntityModel.of(juego,
                linkTo(methodOn(JuegoControllerV4.class).buscarPorId(juego.getIdJuego())).withSelfRel(),
                linkTo(methodOn((JuegoControllerV4.class)).listarJuegos()).withRel("juegos"));
    }

}
