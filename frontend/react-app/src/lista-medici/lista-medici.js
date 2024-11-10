import React, { useState, useEffect } from 'react';
import './lista-medici.css';

function ListaMedici() {
    const [medici, setMedici] = useState([]);
    const [isLoggedIn,  setIsLoggedIn] = useState(true);
    const role=localStorage.getItem('role');

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

    return (
        <div className="lista-medici">
            <h1>Lista Medici</h1>
            <div className="medici">
                {medici.map((medic, index) => (
                    <div key={medic.id_user} className="medic">
                        <h3>{medic.prenume} {medic.nume}</h3>
                        <p><strong>Email:</strong> {medic.email}</p>
                        <p><strong>Specializare:</strong> {medic.specializare}</p>
                    </div>
                ))}
            </div>
        </div>
    );
}

export default ListaMedici;
