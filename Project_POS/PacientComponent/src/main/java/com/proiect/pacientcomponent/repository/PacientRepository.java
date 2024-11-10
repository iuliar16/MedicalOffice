package com.proiect.pacientcomponent.repository;

import com.proiect.pacientcomponent.dto.PacientDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacientRepository extends JpaRepository<PacientDTO, Integer> {
    Optional<PacientDTO> findByIdUser(int id_user);

    @Query("SELECT p FROM PacientDTO p WHERE p.email=:email")
    Optional<PacientDTO> findByEmail(@Param("email") String email);

    @Query("SELECT p FROM PacientDTO p WHERE p.cnp=:cnp")
    Optional<PacientDTO> findByCNP(@Param("cnp") String cnp);
}

