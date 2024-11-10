package com.proiect.consultatiecomponent.service;

import com.proiect.consultatiecomponent.dto.ConsultatieDTO;

import java.util.List;
import java.util.Optional;

public interface IConsultatieService {
    ConsultatieDTO createConsultatie(ConsultatieDTO consultatieDTO);
    List<ConsultatieDTO> getConsultatii();
    void deleteConsultatie(String id);
    Optional<ConsultatieDTO> getConsultatieById(String id);

    List<ConsultatieDTO> getConsultatieByPacientId(String idPacient);
    ConsultatieDTO updateConsultatie(String id, ConsultatieDTO consultatieDTO);

    List<ConsultatieDTO> getConsultatieByPacientIdAndIdDoctor(String idPacient, int idDoctor);
}
