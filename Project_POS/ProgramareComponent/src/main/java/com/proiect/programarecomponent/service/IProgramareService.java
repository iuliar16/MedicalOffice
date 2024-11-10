package com.proiect.programarecomponent.service;

import com.proiect.programarecomponent.dto.ProgramareDTO;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface IProgramareService {
    List<ProgramareDTO> getAllProgramari();
    ProgramareDTO createProgramare(ProgramareDTO programareDTO);
    List<ProgramareDTO> getProgramariByPacientCnp(String cnp);
    List<ProgramareDTO> getProgramariByDoctorId(int id);
    List<ProgramareDTO> getProgramariByDate(Date data);
    Optional<ProgramareDTO> getProgramareById(String cnp, int idDoctor, Date data);
    List<ProgramareDTO> getProgramariByDataLuna(String cnp, String month);
    List<ProgramareDTO> getProgramariByDataZi(String cnp, String month);
    List<ProgramareDTO> getProgramariByIdAndDate(String cnp, Date data);

}
