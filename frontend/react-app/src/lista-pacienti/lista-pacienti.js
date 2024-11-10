import React, { useState, useEffect } from 'react';
import './lista-pacienti.css';
import { Link } from 'react-router-dom';

function ListaPacienti() {
    const [pacienti, setPacienti] = useState([]);
    const [isLoggedIn, setIsLoggedIn] = useState(true);
    const [dateDoctor, setDateDoctor] = useState({});
    const [programari, setProgramari] = useState([]);
    const role = localStorage.getItem('role');

    useEffect(() => {
        func();
    }, []);

    const func = async () => {
        const date = await fetchDoctorData();
        console.log(date);
        const token = localStorage.getItem('token');
        console.log(date.idDoctor);
        let response = null;
        if (token != null && date) {
            response = await fetch(`http://localhost:8000/api/programari/physicians/${date.idDoctor}/programari`, {
                method: 'GET',
                headers: {
                    'Authorization': `Bearer ${token}`
                }
            });
        } else {
            setIsLoggedIn(false);
        }
        const prog = await response.json();
        console.log(prog)
        const programariCuPacienti = await Promise.all(prog.map(async (programare) => {
            console.log(prog)
            console.log(programare.programareKey.idPacient);
            const pacient = await fetchPatientData(programare.programareKey.idPacient);
            return { ...programare, pacient: pacient };
        }));
        setProgramari(programariCuPacienti);
        console.log(programariCuPacienti);

    }
    const fetchPatientData = async (cnp) => {
        const jwtToken = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8000/api/patients/?cnp=${cnp}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        return data;
    };

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
        return data;
    };

    if (!isLoggedIn || role !== 'doctor') {
        return <p>Nu ești autorizat pentru această resursă.</p>;
    }

    // console.log(programari)
    return (
        <div className="lista-pacienti">
            <h1>Lista Pacienti</h1>
            <div className="pacienti">
                {programari && (
                    <>
                        {programari.map((programare, index) => (
                            <div key={index} className="pacient">
                                <p><strong>Nume:</strong> {programare.pacient ? `${programare.pacient.nume} ${programare.pacient.prenume}` : 'N/A'}</p>
                                <p><strong>Email:</strong> {programare.pacient.email}</p>
                                <p><strong>Telefon:</strong> {programare.pacient.telefon}</p>
                                <button>
                                    <Link to={`/lista-programari/${programare.pacient.cnp}`}>Vizualizează Lista de Programări</Link>
                                </button>
                            </div>
                        ))}
                    </>
                )}
            </div>
        </div>
    );
}

export default ListaPacienti;
