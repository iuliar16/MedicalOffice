package com.proiect.doctorcomponent.service;

import com.proiect.doctorcomponent.dto.DoctorDTO;

import java.util.List;
import java.util.Optional;

public interface IDoctorService {
    List<DoctorDTO> getAllDoctors();
    List<DoctorDTO> getDoctorsBySpecializare(String specializare);
    List<DoctorDTO> getDoctorsByNume(String nume);
    Optional<DoctorDTO> getDoctorByIdDoctor(int id);
    DoctorDTO createDoctor(DoctorDTO doctorDTO);
    void deleteDoctor(int id);
    DoctorDTO updateDoctor(int id, DoctorDTO consultatieDTO);

    Optional<DoctorDTO> getDoctorByIdUser(int id);
}
