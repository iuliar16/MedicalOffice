package com.proiect.doctorcomponent.repository;

import com.proiect.doctorcomponent.dto.DoctorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorDTO, Integer> {
    List<DoctorDTO> findBySpecializare(String specialization);
    List<DoctorDTO> findByNume(String nume);

    @Query("SELECT d FROM DoctorDTO d WHERE d.idDoctor=:idDoctor")
    Optional<DoctorDTO> findByIdDoctor(@Param("idDoctor") int idDoctor);

    @Query("SELECT d FROM DoctorDTO d WHERE d.idUser=:idUser")
    Optional<DoctorDTO> findByIdUser(@Param("idUser") int idUser);
}
