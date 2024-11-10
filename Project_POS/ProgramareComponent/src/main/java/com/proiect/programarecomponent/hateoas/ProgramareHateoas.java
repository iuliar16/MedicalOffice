package com.proiect.programarecomponent.hateoas;

import com.proiect.consultatiecomponent.controller.ConsultatieController;
import com.proiect.programarecomponent.controller.ProgramareController;
import com.proiect.programarecomponent.dto.ProgramareDTO;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ProgramareHateoas implements RepresentationModelAssembler<ProgramareDTO, EntityModel<ProgramareDTO>> {
    @Override
    public EntityModel<ProgramareDTO> toModel(ProgramareDTO programare) {
        String consultatiiUrl = "http://localhost:8084/api/medical_office/consultatii/patients/" +programare.getProgramareKey().getIdPacient() + "/physicians/"+programare.getProgramareKey().getIdDoctor();
        return EntityModel.of(programare,
                linkTo(methodOn(ProgramareController.class).getProgramareById(programare.getProgramareKey().getIdPacient(), programare.getProgramareKey().getIdDoctor(), programare.getProgramareKey().getData())).withSelfRel(),
                linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent"),
                Link.of(consultatiiUrl).withRel("consultatii")
        );
    }
}