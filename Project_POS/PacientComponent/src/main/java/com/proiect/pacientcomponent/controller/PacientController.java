package com.proiect.pacientcomponent.controller;

import com.proiect.pacientcomponent.dto.PacientDTO;
import com.proiect.pacientcomponent.hateoas.PacientHateoas;
import com.proiect.pacientcomponent.repository.PacientRepository;
import com.proiect.pacientcomponent.service.PacientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/medical_office/patients")
public class PacientController {
    private final PacientServiceImpl pacientService;

    @Autowired
    public PacientController(PacientServiceImpl pacientService) {
        this.pacientService = pacientService;
    }

    @GetMapping
    public ResponseEntity<List<PacientDTO>> getAllPatients() {
        List<PacientDTO> pacienti=pacientService.getAllPacients();
        return new ResponseEntity<>(pacienti, HttpStatus.OK);
    }
    @GetMapping(params = "cnp")
    public ResponseEntity<?> getPatientByCNP(@RequestParam String cnp){
        Optional<PacientDTO> pacient=pacientService.getPacientByCNP(cnp);

        if(pacient.isPresent()){
            return new ResponseEntity<>(new PacientHateoas().toModel(pacient.get()), HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(PacientController.class).getAllPatients()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> getPatientsById(@RequestParam int id){
        Optional<PacientDTO> pacient=pacientService.getPacientById(id);

        if(pacient.isPresent()){
            return new ResponseEntity<>(new PacientHateoas().toModel(pacient.get()), HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(PacientController.class).getAllPatients()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<PacientDTO> createPatient(@RequestBody PacientDTO pacientDTO){
        PacientDTO savedPatient = pacientService.createPacient(pacientDTO);
        return new ResponseEntity<>(savedPatient,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable int id){
        Optional<PacientDTO> pacient = pacientService.getPacientById(id);
        if (!pacient.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        pacientService.deletePacient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping(params = "email")
    public ResponseEntity<?> getPacientByEmail(@RequestParam String email) {
        Optional<PacientDTO> pacient = pacientService.getPacientByEmail(email);

        if(pacient.isPresent()) {
            return new ResponseEntity<>(new PacientHateoas().toModel(pacient.get()), HttpStatus.OK);
        } else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(PacientController.class).getAllPatients()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updatePacient(@PathVariable int id, @RequestBody PacientDTO pacientDTO) {
        Optional<PacientDTO> existingPacient = pacientService.getPacientById(id);

        if (existingPacient.isPresent()) {
            PacientDTO updatedDoctor = pacientService.updatePacient(id, pacientDTO);
            EntityModel<PacientDTO>pacientModel = new PacientHateoas().toModel(updatedDoctor);

            return new ResponseEntity<>(pacientModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
