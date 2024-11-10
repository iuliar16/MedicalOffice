package com.proiect.programarecomponent.service;

import com.proiect.programarecomponent.dto.ProgramareDTO;
import com.proiect.programarecomponent.dto.ProgramareKey;
import com.proiect.programarecomponent.repository.ProgramareRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ProgramareServiceImpl implements IProgramareService{
    private ProgramareRepository programareRepository;

    @Autowired
    public ProgramareServiceImpl(ProgramareRepository programareRepository){
        this.programareRepository=programareRepository;
    }
    @Override
    public List<ProgramareDTO> getAllProgramari() {
        return programareRepository.findAll();
    }

    @Override
    public ProgramareDTO createProgramare(ProgramareDTO programareDTO) {
        return programareRepository.save(programareDTO);
    }
    @Override
    public List<ProgramareDTO> getProgramariByPacientCnp(String cnp) {
        return programareRepository.findByPacientCnp(cnp);
    }

    @Override
    public List<ProgramareDTO> getProgramariByDoctorId(int id) {
        return programareRepository.findByDoctorId(id);
    }

    @Override
    public Optional<ProgramareDTO> getProgramareById(String cnp, int idDoctor, Date data) {
        return programareRepository.findByProgramareKey(cnp,idDoctor,data);
    }
    @Override
    public List<ProgramareDTO> getProgramariByDate(Date data) {
        return programareRepository.findByProgramareDate(data);
    }


    @Override
    public List<ProgramareDTO> getProgramariByDataLuna(String cnp, String month) {
        return programareRepository.findByProgramareKeyDataLuna(cnp,month);
    }

    @Override
    public List<ProgramareDTO> getProgramariByDataZi(String cnp, String month) {
        return programareRepository.findByProgramareKeyDataZi(cnp,month);
    }

    @Override
    public List<ProgramareDTO> getProgramariByIdAndDate(String cnp, Date data) {
        return programareRepository.findByProgramareKeyIdAndData(cnp,data);
    }


}
