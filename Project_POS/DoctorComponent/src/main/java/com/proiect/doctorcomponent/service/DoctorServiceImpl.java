package com.proiect.doctorcomponent.service;

import com.proiect.doctorcomponent.dto.DoctorDTO;
import com.proiect.doctorcomponent.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements IDoctorService{
    private DoctorRepository doctorRepository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository doctorRepository){
        this.doctorRepository=doctorRepository;
    }
    @Override
    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<DoctorDTO> getDoctorsBySpecializare(String specializare) {
        return doctorRepository.findBySpecializare(specializare);
    }

    @Override
    public List<DoctorDTO> getDoctorsByNume(String nume) {
        return doctorRepository.findByNume(nume);
    }

    @Override
    public Optional<DoctorDTO> getDoctorByIdDoctor(int id) {
        Optional<DoctorDTO> doctor = doctorRepository.findByIdDoctor(id);
        return doctor;
    }

    @Override
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        return doctorRepository.save(doctorDTO);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDTO updateDoctor(int id, DoctorDTO doctorDTO) {
        Optional<DoctorDTO> existingDoctor = doctorRepository.findById(id);

        if (existingDoctor.isPresent()) {
            DoctorDTO doctorToUpdate = existingDoctor.get();
            doctorToUpdate.setIdDoctor(doctorDTO.getIdDoctor());
            doctorToUpdate.setIdUser(doctorDTO.getIdUser());
            doctorToUpdate.setNume(doctorDTO.getNume());
            doctorToUpdate.setPrenume(doctorDTO.getPrenume());
            doctorToUpdate.setEmail(doctorDTO.getEmail());
            doctorToUpdate.setTelefon(doctorDTO.getTelefon());
            doctorToUpdate.setSpecializare(doctorDTO.getSpecializare());

            return doctorRepository.save(doctorToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public Optional<DoctorDTO> getDoctorByIdUser(int id) {
        return doctorRepository.findByIdUser(id);
    }
}
