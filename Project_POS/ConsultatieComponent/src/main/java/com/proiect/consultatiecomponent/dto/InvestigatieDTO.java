package com.proiect.consultatiecomponent.dto;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "investigatii")
public class InvestigatieDTO {
    @Id
    private String id;
    private String denumire;
    private int durata_procesare;
    private String rezultat;
}
