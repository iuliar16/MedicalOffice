package com.proiect.consultatiecomponent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "consultatii")
public class ConsultatieDTO {
    @Id
    private String id;
    private String idPacient;
    private int idDoctor;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date data;
    private String diagnostic;
    private List<InvestigatieDTO> investigatii;
}
