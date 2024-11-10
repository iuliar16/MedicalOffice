package com.proiect.doctorcomponent.controller;

import com.proiect.doctorcomponent.dto.DoctorDTO;
import com.proiect.doctorcomponent.hateoas.DoctorHateoas;
import com.proiect.doctorcomponent.repository.DoctorRepository;
import com.proiect.doctorcomponent.service.DoctorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/medical_office/physicians")
public class DoctorController {
    private final DoctorServiceImpl doctorService;

    @Autowired
    public DoctorController(DoctorServiceImpl doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        List<DoctorDTO> doctors=doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @GetMapping( params = "page")
    public ResponseEntity<?> getAllDoctorsPaginated(
            @RequestParam(name = "page", defaultValue = "1") int page,
            @RequestParam(name = "items_per_page", required = false) Integer itemsPerPage) {
        if(itemsPerPage==null)
            itemsPerPage=5; // daca itemsPerPage nu este specificat
        int offset = (page - 1) * itemsPerPage;
        List<DoctorDTO> doctors = doctorService.getAllDoctors();

        if (offset < doctors.size()) {
            int toIndex = Math.min(offset + itemsPerPage, doctors.size());
            List<DoctorDTO> paginatedDoctors = doctors.subList(offset, toIndex);

            List<EntityModel<DoctorDTO>> doctorModels = paginatedDoctors.stream()
                    .map(doctor -> new DoctorHateoas().toModel(doctor))
                    .collect(Collectors.toList());

            return new ResponseEntity<>(doctorModels, HttpStatus.OK);
        } else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(params = "id_doctor")
    public ResponseEntity<?> getDoctorByIdDoctor(@RequestParam int id_doctor){
        Optional<DoctorDTO> doctor=doctorService.getDoctorByIdDoctor(id_doctor);

        if(!doctor.isEmpty()){
            return new ResponseEntity<>(new DoctorHateoas().toModel(doctor.get()), HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(params = "idUser")
    public ResponseEntity<?> getDoctorById(@RequestParam int idUser){
        Optional<DoctorDTO> doctor=doctorService.getDoctorByIdUser(idUser);

        if(!doctor.isEmpty()){
            return new ResponseEntity<>(new DoctorHateoas().toModel(doctor.get()), HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(params = "specialization")
    public ResponseEntity<?> getDoctorsBySpecialization(@RequestParam(name = "specialization") String specializare) {
        List<DoctorDTO> doctors=doctorService.getDoctorsBySpecializare(specializare);

        if(!doctors.isEmpty()){
            List<EntityModel<DoctorDTO>> doctorModels = doctors.stream()
                    .map(doctor -> new DoctorHateoas().toModel(doctor))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(doctorModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(params = "name")
    public ResponseEntity<?> getDoctorsByName(@RequestParam(name = "name") String nume) {
        List<DoctorDTO> doctors=doctorService.getDoctorsByNume(nume);

        if(!doctors.isEmpty()){
            List<EntityModel<DoctorDTO>> doctorModels = doctors.stream()
                    .map(doctor -> new DoctorHateoas().toModel(doctor))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(doctorModels, HttpStatus.OK);
        }
        else {
            Map<String, ArrayList<Link>> links = new HashMap<>();
            ArrayList<Link> arrayLinks = new ArrayList<>();

            Link parentLink = linkTo(methodOn(DoctorController.class).getAllDoctors()).withRel("parent");
            arrayLinks.add(parentLink);
            links.put("_links", new ArrayList<Link>(arrayLinks));

            return new ResponseEntity<>(links, HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO){
        DoctorDTO savedDoctor = doctorService.createDoctor(doctorDTO);
        return new ResponseEntity<>(savedDoctor,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDoctor(@PathVariable int id){
        Optional<DoctorDTO> doctor = doctorService.getDoctorByIdUser(id);
        if (doctor.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable int id, @RequestBody DoctorDTO doctorDTO) {
        Optional<DoctorDTO> existingDoctor = doctorService.getDoctorByIdDoctor(id);

        if (existingDoctor.isPresent()) {
            DoctorDTO updatedDoctor = doctorService.updateDoctor(id, doctorDTO);
            EntityModel<DoctorDTO>doctorModel = new DoctorHateoas().toModel(updatedDoctor);

            return new ResponseEntity<>(doctorModel, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
