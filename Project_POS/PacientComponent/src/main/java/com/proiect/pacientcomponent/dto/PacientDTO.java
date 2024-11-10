package com.proiect.pacientcomponent.dto;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="pacienti")
public class PacientDTO {
    @Id
    private String cnp;

    @Basic
    @Column(name = "id_user")
    private int idUser;

    @Basic
    @Column(name = "nume")
    private String nume;

    @Basic
    @Column(name = "prenume")
    private String prenume;

    @Basic
    @Column(name = "email")
    private String email;

    @Basic
    @Column(name = "telefon")
    private String telefon;

    @Basic
    @Column(name = "data_nasterii")
    private Date data_nasterii;

    @Basic
    @Column(name = "is_active")
    private Boolean is_active;


    public PacientDTO(int idUser, String cnp, String nume, String prenume, String email, String telefon, Date data_nasterii, Boolean is_active) {
        this.idUser = idUser;
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.data_nasterii = data_nasterii;
        this.is_active = is_active;
    }

    public PacientDTO() {

    }
}