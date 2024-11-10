package com.proiect.programarecomponent.repository;

import com.proiect.programarecomponent.dto.ProgramareDTO;
import com.proiect.programarecomponent.dto.ProgramareKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramareRepository extends JpaRepository<ProgramareDTO, ProgramareKey> {

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idPacient = :cnp")
    List<ProgramareDTO> findByPacientCnp(@Param("cnp") String cnp);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idDoctor = :id_doctor")
    List<ProgramareDTO> findByDoctorId(@Param("id_doctor") int id_doctor);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idPacient = :cnp AND p.programareKey.idDoctor = :idDoctor AND p.programareKey.data = :data")
    Optional<ProgramareDTO> findByProgramareKey(@Param("cnp") String cnp, @Param("idDoctor") int idDoctor, @Param("data") Date data);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idPacient = :cnp AND MONTH(p.programareKey.data) = :month")
    List<ProgramareDTO> findByProgramareKeyDataLuna(@Param("cnp") String cnp, @Param("month") String month);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idPacient = :cnp AND DAY(p.programareKey.data) = :day " +
            "AND MONTH(p.programareKey.data) = MONTH(CURRENT_DATE)")
    List<ProgramareDTO> findByProgramareKeyDataZi(@Param("cnp") String cnp, @Param("day") String day);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.idPacient = :cnp AND p.programareKey.data = :data")
    List<ProgramareDTO> findByProgramareKeyIdAndData(@Param("cnp") String cnp, @Param("data") Date data);

    @Query("SELECT p FROM ProgramareDTO p WHERE p.programareKey.data = :data")
    List<ProgramareDTO> findByProgramareDate(Date data);

}



