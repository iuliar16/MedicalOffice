package com.proiect.consultatiecomponent.hateoas;

import com.proiect.consultatiecomponent.controller.ConsultatieController;
import com.proiect.consultatiecomponent.dto.ConsultatieDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ConsultatieHateoas implements RepresentationModelAssembler<ConsultatieDTO, EntityModel<ConsultatieDTO>> {
    @Override
    public EntityModel<ConsultatieDTO> toModel(ConsultatieDTO consultatie) {
        return EntityModel.of(consultatie,
                linkTo(methodOn(ConsultatieController.class).getConsultatieById(consultatie.getId())).withSelfRel(),
                linkTo(methodOn(ConsultatieController.class).getAllConsultatii()).withRel("parent")
        );
    }
}