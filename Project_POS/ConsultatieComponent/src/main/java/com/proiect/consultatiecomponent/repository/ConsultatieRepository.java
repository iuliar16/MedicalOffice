package com.proiect.consultatiecomponent.repository;

import com.proiect.consultatiecomponent.dto.ConsultatieDTO;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ConsultatieRepository extends MongoRepository<ConsultatieDTO, String> {
    @Query("{ idPacient :?0}")
    List<ConsultatieDTO> findByIdPacient(String idPacient);

    List<ConsultatieDTO> findByIdPacientAndIdDoctor(String idPacient, int idDoctor);
}
