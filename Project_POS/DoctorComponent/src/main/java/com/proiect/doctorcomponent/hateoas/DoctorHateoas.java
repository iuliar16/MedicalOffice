package com.proiect.doctorcomponent.hateoas;

import com.proiect.doctorcomponent.controller.DoctorController;
import com.proiect.doctorcomponent.dto.DoctorDTO;
import com.proiect.programarecomponent.controller.ProgramareController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class DoctorHateoas implements RepresentationModelAssembler<DoctorDTO, EntityModel<DoctorDTO>> {
    @Override
    public EntityModel<DoctorDTO> toModel(DoctorDTO doctor) {
        String programariUrl = "http://localhost:8082/api/medical_office/physicians/" + doctor.getIdDoctor() + "/programari";
        return EntityModel.of(doctor,
                linkTo(methodOn(DoctorController.class).getDoctorById(doctor.getIdUser())).withSelfRel(),
                linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent"),
                Link.of(programariUrl).withRel("programari")

        );
    }
}