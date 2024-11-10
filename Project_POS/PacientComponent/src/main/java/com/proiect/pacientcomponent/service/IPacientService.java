package com.proiect.pacientcomponent.service;


import com.proiect.pacientcomponent.dto.PacientDTO;

import java.util.List;
import java.util.Optional;

public interface IPacientService {
    List<PacientDTO> getAllPacients();
    Optional<PacientDTO> getPacientById(int id);
    PacientDTO createPacient(PacientDTO pacientDTO);
    void deletePacient(int id);

    Optional<PacientDTO> getPacientByEmail(String email);

    Optional<PacientDTO> getPacientByCNP(String cnp);

    PacientDTO updatePacient(int id, PacientDTO pacientDTO);
}
