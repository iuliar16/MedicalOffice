package com.proiect.programarecomponent.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="programari")
@AllArgsConstructor
@NoArgsConstructor
public class ProgramareDTO {
    @EmbeddedId
    private ProgramareKey programareKey;

    @Basic
    @Column(name="status")
    private String status;

}
