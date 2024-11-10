package com.proiect.doctorcomponent.dto;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="doctori")
@Getter
@Setter
public class DoctorDTO {
    @Basic
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_doctor")
    private int idDoctor;

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
    @Column(name = "specializare")
    private String specializare;

    public DoctorDTO(int id_doctor, int id_user, String nume, String prenume, String email, String telefon, String specializare) {
        this.idDoctor = id_doctor;
        this.idUser = id_user;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.telefon = telefon;
        this.specializare = specializare;
    }

    public DoctorDTO() {

    }
}

