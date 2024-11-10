package com.proiect.consultatiecomponent.service;

import com.proiect.consultatiecomponent.dto.ConsultatieDTO;
import com.proiect.consultatiecomponent.repository.ConsultatieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultatieServiceImpl implements IConsultatieService {
    private ConsultatieRepository consultatieRepository;

    @Autowired
    public ConsultatieServiceImpl(ConsultatieRepository consultatieRepository){
        this.consultatieRepository=consultatieRepository;
    }
    @Override
    public ConsultatieDTO createConsultatie(ConsultatieDTO consultatieDTO) {
        return consultatieRepository.save(consultatieDTO);
    }
    @Override
    public List<ConsultatieDTO> getConsultatii() {
        return consultatieRepository.findAll();
    }
    @Override
    public void deleteConsultatie(String id) {
        consultatieRepository.deleteById(id);
    }

    @Override
    public Optional<ConsultatieDTO> getConsultatieById(String id) {
        return consultatieRepository.findById(id);
    }

    @Override
    public List<ConsultatieDTO> getConsultatieByPacientId(String idPacient) {
        return consultatieRepository.findByIdPacient(idPacient);
    }
    @Override
    public ConsultatieDTO updateConsultatie(String id, ConsultatieDTO consultatieDTO) {
        Optional<ConsultatieDTO> existingConsultatie = consultatieRepository.findById(id);

        if (existingConsultatie.isPresent()) {
            ConsultatieDTO consultatieToUpdate = existingConsultatie.get();
            consultatieToUpdate.setIdPacient(consultatieDTO.getIdPacient());
            consultatieToUpdate.setIdDoctor(consultatieDTO.getIdDoctor());
            consultatieToUpdate.setData(consultatieDTO.getData());
            consultatieToUpdate.setDiagnostic(consultatieDTO.getDiagnostic());
            consultatieToUpdate.setInvestigatii(consultatieDTO.getInvestigatii());

            return consultatieRepository.save(consultatieToUpdate);
        } else {
            return null;
        }
    }

    @Override
    public List<ConsultatieDTO> getConsultatieByPacientIdAndIdDoctor(String idPacient, int idDoctor) {
        return consultatieRepository.findByIdPacientAndIdDoctor(idPacient,idDoctor);
    }


}
