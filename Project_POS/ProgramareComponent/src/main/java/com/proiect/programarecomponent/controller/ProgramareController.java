package com.proiect.programarecomponent.controller;

import com.proiect.programarecomponent.dto.ProgramareDTO;
import com.proiect.programarecomponent.hateoas.ProgramareHateoas;
import com.proiect.programarecomponent.service.ProgramareServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
@RequestMapping("/api/medical_office")

public class ProgramareController {
    private final ProgramareServiceImpl programareService;

    @Autowired
    public ProgramareController(ProgramareServiceImpl programareService) {
        this.programareService = programareService;
    }

    @GetMapping("/programari")
    public ResponseEntity<List<ProgramareDTO>> getAllAppointments() {
        List<ProgramareDTO> programari=programareService.getAllProgramari();
        return new ResponseEntity<>(programari, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProgramareDTO> createProgramare(@RequestBody ProgramareDTO programareDTO){
        ProgramareDTO savedProgramare = programareService.createProgramare(programareDTO);

        return new ResponseEntity<>(savedProgramare, HttpStatus.CREATED);
    }
    @GetMapping("/patients/{cnp}/programari")
    public ResponseEntity<?> getProgramariByPacientCNP(@PathVariable String cnp){
        List<ProgramareDTO> programari=programareService.getProgramariByPacientCnp(cnp);

        if(!programari.isEmpty()){
            List<EntityModel<ProgramareDTO>> programariModels = programari.stream()
                    .map(programare -> new ProgramareHateoas().toModel(programare))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/physicians/{id_doctor}/programari")
    public ResponseEntity<?> getProgramariByDoctorId(@PathVariable int id_doctor){
        System.out.println("here");
        List<ProgramareDTO> programari=programareService.getProgramariByDoctorId(id_doctor);

        if(!programari.isEmpty()){
            List<EntityModel<ProgramareDTO>> programariModels = programari.stream()
                    .map(programare -> new ProgramareHateoas().toModel(programare))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "data")
    public ResponseEntity<?> getProgramariByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date data ){
        List<ProgramareDTO> programari=programareService.getProgramariByDate(data);

        if(!programari.isEmpty()){
            List<EntityModel<ProgramareDTO>> programariModels = programari.stream()
                    .map(programare -> new ProgramareHateoas().toModel(programare))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("{idPacient}/{idDoctor}/{data}")
    public ResponseEntity<?> getProgramareById(
            @PathVariable  String idPacient,
            @PathVariable  int idDoctor,
            @PathVariable  @DateTimeFormat(pattern = "yyyy-MM-dd") Date data
    ){
    Optional<ProgramareDTO> programare=programareService.getProgramareById(idPacient,idDoctor,data);
        if(programare.isPresent()){
            EntityModel<ProgramareDTO> programareModel = new ProgramareHateoas().toModel(programare.get());
            return new ResponseEntity<>(programareModel, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("programari/{id_pacient}/{date}/{type}")
    public ResponseEntity<?> getAppointmentsByDate(
            @PathVariable(name = "id_pacient") String id_pacient,
            @PathVariable(name = "date") String date,
            @PathVariable(name = "type", required=false) String type) throws ParseException {

        List<ProgramareDTO> programari;
        if ("month".equalsIgnoreCase(type)) {
            System.out.println("month");
            programari = programareService.getProgramariByDataLuna(id_pacient, date);
        } else if ("day".equalsIgnoreCase(type)) {
            programari = programareService.getProgramariByDataZi(id_pacient, date);
        } else {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = dateFormat.parse(date);
            programari = programareService.getProgramariByIdAndDate(id_pacient, parsedDate);
        }

        if (!programari.isEmpty()) {
            List<EntityModel<ProgramareDTO>> programariModels = programari.stream()
                    .map(programare -> new ProgramareHateoas().toModel(programare))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(programariModels, HttpStatus.OK);
        } else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(ProgramareController.class).getAllAppointments()).withRel("parent");

            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
}

