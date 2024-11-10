import React, { useState, useEffect } from 'react';
import './istoric-medical.css';

function IstoricMedical() {
    const [istoric, setIstoric] = useState([]);
    const [datePacient, setDatePacient] = useState({});

    const fetchPatientData = async () => {
        const jwtToken = localStorage.getItem('token');
        const user_id = localStorage.getItem('user_id');
        const response = await fetch(`http://localhost:8000/api/patients/${user_id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        setDatePacient(data);
        return data;
    };

    const showConsultatii = async () => {
        try {
            const token = localStorage.getItem('token');
            const date = await fetchPatientData();
            if (token != null && date) {
                const response = await fetch(`http://localhost:8000/api/consultatii?${date.idPacient}`, {
                    headers: {
                        'Authorization': `Bearer ${localStorage.getItem('token')}`
                    }
                });
                const cons = await response.json();
                console.log(cons);
                setIstoric(cons);
            }
        } catch (error) {
            console.error('Eroare la preluarea datelor:', error);
        }

    };

    useEffect(() => {
        showConsultatii();
        console.log(istoric);
    }, []);


    return (
        <div className="istoric-medical">
            <h1>Istoric Medical</h1>
            <div className="inregistrari">
                {istoric.length > 0 ? istoric.map((intrare, index) => (
                    <div key={index} className="intrare">
                        <h3>Data: {intrare.data}</h3>
                        <p><strong>Diagnostic:</strong> {intrare.diagnostic}</p>
                        {intrare.investigatii && intrare.investigatii.length > 0 && (
                            <div>
                                <h4>Investigații:</h4>
                                <ul>
                                    {intrare.investigatii.map((investigatie, idx) => (
                                        <li key={idx}>
                                            Denumire: {investigatie.denumire},
                                            Durata: {investigatie.durata_procesare} zile,
                                            Rezultat: {investigatie.rezultat}
                                        </li>
                                    ))}
                                </ul>
                            </div>
                        )}
                    </div>
                )) : (
                    <p>Nu există înregistrări în istoricul medical.</p>
                )}
            </div>
        </div>
    );
}
export default IstoricMedical;
