import React, { useState, useEffect } from 'react';
import './date-personale.css';

function DatePersonale() {
    const [datePacient, setDatePacient] = useState({});
    const [dateDoctor, setDateDoctor] = useState({});

    const fetchPatientData = async () => {
        const jwtToken = localStorage.getItem('token');
        const user_id = localStorage.getItem('user_id');
        const response = await fetch(`http://localhost:8000/api/patients/?id=${user_id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        setDatePacient(data);
    };

    const isPacient = () => {
        const role = localStorage.getItem('role');
        return role === "pacient";
    }
    const isDoctor = () => {
        const role = localStorage.getItem('role');
        return role === "doctor";
    }

    const fetchDoctorData = async () => {
        const jwtToken = localStorage.getItem('token');
        const user_id = localStorage.getItem('user_id');
        const response = await fetch(`http://localhost:8000/api/doctors/?idUser=${user_id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        setDateDoctor(data);
    };

    useEffect(() => {
        if (isPacient()) 
        {
            fetchPatientData()
        }
        else if (isDoctor())
        {
            fetchDoctorData()
        }
    }, []);

    return (
        <div className="date-personale">
            <h1>Date Personale</h1>
            {isPacient() && (
                <>
                    <div className="info">
                        <p><strong>Nume:</strong> {datePacient.nume}</p>
                        <p><strong>Prenume:</strong> {datePacient.prenume}</p>
                        <p><strong>Email:</strong> {datePacient.email}</p>
                        <p><strong>Data Nașterii:</strong> {datePacient.data_nasterii}</p>
                        <p><strong>Telefon:</strong> {datePacient.telefon}</p>
                    </div>
                </>
            )}
             {isDoctor() && (
                <>
                    <div className="info">
                        <p><strong>Nume:</strong> {dateDoctor.nume}</p>
                        <p><strong>Prenume:</strong> {dateDoctor.prenume}</p>
                        <p><strong>Email:</strong> {dateDoctor.email}</p>
                        <p><strong>Specializare:</strong> {dateDoctor.specializare}</p>
                        <p><strong>Telefon:</strong> {dateDoctor.telefon}</p>
                    </div>
                </>
            )}
        </div>
    );
}

export default DatePersonale;
