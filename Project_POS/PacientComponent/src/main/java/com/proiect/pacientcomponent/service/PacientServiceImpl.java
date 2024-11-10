package com.proiect.pacientcomponent.service;

import com.proiect.pacientcomponent.dto.PacientDTO;
import com.proiect.pacientcomponent.repository.PacientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PacientServiceImpl implements IPacientService{
    private PacientRepository pacientRepository;

    @Autowired
    public PacientServiceImpl(PacientRepository pacientRepository){
        this.pacientRepository=pacientRepository;
    }
    @Override
    public List<PacientDTO> getAllPacients() {
        return pacientRepository.findAll();
    }

    @Override
    public Optional<PacientDTO> getPacientById(int id) {
        return pacientRepository.findByIdUser(id);
    }

    @Override
    public PacientDTO createPacient(PacientDTO pacientDTO) {
        return pacientRepository.save(pacientDTO);
    }

    @Override
    public void deletePacient(int id) {
        pacientRepository.deleteById(id);
    }

    @Override
    public Optional<PacientDTO> getPacientByEmail(String email) {
        return pacientRepository.findByEmail(email);
    }

    @Override
    public Optional<PacientDTO> getPacientByCNP(String cnp) {
        return pacientRepository.findByCNP(cnp);
    }

    @Override
    public PacientDTO updatePacient(int id, PacientDTO pacientDTO) {
        Optional<PacientDTO> existingPacient = pacientRepository.findByIdUser(id);

        if (existingPacient.isPresent()) {
            PacientDTO pacientToUpdate = existingPacient.get();
            pacientToUpdate.setCnp(pacientDTO.getCnp());
            pacientToUpdate.setIdUser(pacientDTO.getIdUser());
            pacientToUpdate.setNume(pacientDTO.getNume());
            pacientToUpdate.setPrenume(pacientDTO.getPrenume());
            pacientToUpdate.setEmail(pacientDTO.getEmail());
            pacientToUpdate.setTelefon(pacientDTO.getTelefon());
            pacientToUpdate.setData_nasterii(pacientDTO.getData_nasterii());
            pacientToUpdate.setIs_active(pacientDTO.getIs_active());

            return pacientRepository.save(pacientToUpdate);
        } else {
            return null;
        }
    }

}
