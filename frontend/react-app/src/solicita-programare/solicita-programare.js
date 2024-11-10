import React, { useEffect, useState } from 'react';
import './solicita-programare.css'; 

function SolicitaProgramare() {
    const [numeDoctor, setNumeDoctor] = useState('');
    const [dataProgramare, setDataProgramare] = useState('');

    const [doctori, setMedici] = useState([]);
    const [isLoggedIn,  setIsLoggedIn] = useState(true);
    
    useEffect(() => {
        const token = localStorage.getItem('token');
        if (token != null) {
            fetch('http://localhost:8000/api/doctors/', {
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            })
            .then(response => response.json())
            .then(data => setMedici(data))
            .catch(error => console.error('Eroare la preluarea datelor:', error));
        } else {
            setIsLoggedIn(false);
        }
    }, []);

    if (!isLoggedIn) {
        return <p>Nu ești autorizat pentru această resursă.</p>;
    }
    const fetchPatientData = async () => {
        const user_id = localStorage.getItem('user_id');
        const jwtToken = localStorage.getItem('token'); 
        const response = await fetch(`http://localhost:8000/api/patients/${user_id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        return data;
    };
    const fetchPhysicianData = async (nume) => {
        const jwtToken = localStorage.getItem('token'); 
        const response = await fetch(`http://localhost:8000/api/doctors/?name=${nume}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        return data;
    };
    const makeProgramare= async(programare) => {
        try {
            const response = await fetch('http://localhost:8000/api/programari', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                },
                body: JSON.stringify(programare)
            });
            const data = await response.json();
            if (response.ok) {
                console.log('Programare solicitată cu succes!');
            } else {
                throw new Error(data.message || 'Eroare la solicitarea programării');
            }
        } catch (error) {
            console.error('Eroare la trimiterea programării:', error);
        }
    }

    const handleSolicitare = async (event) => {
        event.preventDefault();
    
        try {
            const datePacient = await fetchPatientData(); 
            const dateDoctor = await fetchPhysicianData(numeDoctor);
    
            if (datePacient && dateDoctor && dateDoctor.length > 0) {
                const programare = {
                    programareKey: {
                        idPacient: datePacient.cnp,
                        idDoctor: dateDoctor[0].idDoctor,
                        data: dataProgramare
                    },
                    status: "neprezentat"
                };
                await makeProgramare(programare);
                alert(`Programare solicitată cu ${numeDoctor} pe data de ${dataProgramare}`);
            } else {
                console.error('Datele pentru pacient/doctor nu sunt complete.');
            }
        } catch (error) {
            console.error('Eroare la solicitarea programarii:', error);
        }
    };
    
    

    return (
        <div className="solicita-programare">
            <h1>Solicită o Programare</h1>
            <form onSubmit={handleSolicitare}>
                <label>
                    Alege Doctorul:
                    <select value={numeDoctor} onChange={(e) => setNumeDoctor(e.target.value)}>
                        <option value="">Selectează un doctor</option>
                        {doctori.map((doctor, index) => (
                            <option key={index} value={doctor.nume}>{doctor.nume} {doctor.prenume} - {doctor.specializare}</option>
                        ))}
                    </select>
                </label>
                <label>
                    Alege Data:
                    <input
                        type="date"
                        value={dataProgramare}
                        onChange={(e) => setDataProgramare(e.target.value)}
                    />
                </label>
                <button type="submit">Solicită Programare</button>
            </form>
        </div>
    );
}

export default SolicitaProgramare;
