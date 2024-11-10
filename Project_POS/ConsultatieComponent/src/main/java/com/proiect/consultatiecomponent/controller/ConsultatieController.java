package com.proiect.consultatiecomponent.controller;


import com.proiect.consultatiecomponent.dto.ConsultatieDTO;
import com.proiect.consultatiecomponent.dto.InvestigatieDTO;
import com.proiect.consultatiecomponent.hateoas.ConsultatieHateoas;
import com.proiect.consultatiecomponent.service.ConsultatieServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/medical_office/consultatii")
public class ConsultatieController {
    private ConsultatieServiceImpl consultatieService;

    @Autowired
    public ConsultatieController(ConsultatieServiceImpl consultatieService) {
        this.consultatieService = consultatieService;
    }

    @GetMapping
    public ResponseEntity<List<ConsultatieDTO>> getAllConsultatii() {
        List<ConsultatieDTO> consultatii=consultatieService.getConsultatii();
        return new ResponseEntity<>(consultatii, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<ConsultatieDTO> createConsultatie(@RequestBody ConsultatieDTO consultatieDTO){
        if (consultatieDTO.getInvestigatii() != null) {
            for (InvestigatieDTO investigatie : consultatieDTO.getInvestigatii()) {
                investigatie.setId(UUID.randomUUID().toString());
            }
        }

        ConsultatieDTO savedDoctor = consultatieService.createConsultatie(consultatieDTO);
        return new ResponseEntity<>(savedDoctor,HttpStatus.CREATED);
    }
    @GetMapping(params="id")
    public ResponseEntity<?> getConsultatieById(@RequestParam String id){
        Optional<ConsultatieDTO> consultatie=consultatieService.getConsultatieById(id);

        if(!consultatie.isEmpty()){
            return new ResponseEntity<>(new ConsultatieHateoas().toModel(consultatie.get()), HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ConsultatieController.class).getAllConsultatii()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(params = "id_pacient")
    public ResponseEntity<?> getConsultatiiByIdPacient(@RequestParam String id_pacient){
        List<ConsultatieDTO> consultatii=consultatieService.getConsultatieByPacientId(id_pacient);

        if(!consultatii.isEmpty()){
            List<EntityModel<ConsultatieDTO>> programariModels = consultatii.stream()
                    .map(consultatie -> new ConsultatieHateoas().toModel(consultatie))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ConsultatieController.class).getAllConsultatii()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/patients/{id_pacient}/physicians/{id_doctor}")
    public ResponseEntity<?> getConsultatiiByIdPacientAndIdDoctor(@PathVariable String id_pacient,@PathVariable int id_doctor){
        List<ConsultatieDTO> consultatii=consultatieService.getConsultatieByPacientIdAndIdDoctor(id_pacient,id_doctor);

        if(!consultatii.isEmpty()){
            List<EntityModel<ConsultatieDTO>> programariModels = consultatii.stream()
                    .map(consultatie -> new ConsultatieHateoas().toModel(consultatie))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ConsultatieController.class).getAllConsultatii()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("{id}/investigatii")
    public ResponseEntity<?> getInvestigatiiConsultatie(@PathVariable String id){
        Optional<ConsultatieDTO> consultatie = consultatieService.getConsultatieById(id);

        if (consultatie.isPresent()) {
            return new ResponseEntity<>(consultatie.get().getInvestigatii(), HttpStatus.OK);
        } else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ConsultatieController.class).getAllConsultatii()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateConsultatie(@PathVariable String id, @RequestBody ConsultatieDTO consultatieDTO) {
        Optional<ConsultatieDTO> existingConsultatie = consultatieService.getConsultatieById(id);

        if (existingConsultatie.isPresent()) {
            ConsultatieDTO updatedConsultatie = consultatieService.updateConsultatie(id, consultatieDTO);
            EntityModel<ConsultatieDTO> consultatieModel = new ConsultatieHateoas().toModel(updatedConsultatie);

            return new ResponseEntity<>(consultatieModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultatie(@PathVariable String id){
        Optional<ConsultatieDTO> doctor = consultatieService.getConsultatieById(id);
        if (!doctor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        consultatieService.deleteConsultatie(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

