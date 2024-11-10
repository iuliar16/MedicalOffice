package com.proiect.pacientcomponent.hateoas;

import com.proiect.pacientcomponent.controller.PacientController;
import com.proiect.pacientcomponent.dto.PacientDTO;
import com.proiect.programarecomponent.controller.ProgramareController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilderDslKt.withRel;

public class PacientHateoas implements RepresentationModelAssembler<PacientDTO, EntityModel<PacientDTO>> {
    @Override
    public EntityModel<PacientDTO> toModel(PacientDTO pacient) {
        EntityModel<PacientDTO> pacientModel = EntityModel.of(pacient,
                linkTo(methodOn(PacientController.class).getPatientsById(pacient.getIdUser())).withSelfRel(),
                linkTo(methodOn(PacientController.class).getAllPatients()).withRel("parent"),
                linkTo(methodOn(ProgramareController.class).getProgramariByPacientCNP(pacient.getCnp())).withRel("programari")
        );
        return pacientModel;
    }
}

