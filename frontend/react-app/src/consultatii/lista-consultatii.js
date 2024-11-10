import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import './lista-consultatii.css';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

function ListaConsultatii() {
    const [consultatii, setConsultatii] = useState([]);
    const { idDoctor, idPacient } = useParams();

    const showConsultatii = async () => {
        try {
            const response = await fetch(`http://localhost:8000/api/consultatii/patients/${idPacient}/physicians/${idDoctor}`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            const cons = await response.json();
            setConsultatii(cons);
            console.log(cons);
        } catch (error) {
            console.error('Eroare la preluarea datelor:', error);
        }
    };

    useEffect(() => {
        showConsultatii();
    }, []);

    return (
        <div className="lista-consultatii">
            <h1>Lista Consultațiilor</h1>
            <div className="consultatii">
                {consultatii.length > 0 ? (
                    consultatii.map((consultatie, index) => (
                        <div key={index} className="consultatie">
                            <p><strong>Data:</strong> {consultatie.data}</p>
                            <p><strong>Diagnostic:</strong> {consultatie.diagnostic}</p>
                            <p><strong>Investigații:</strong></p>
                            <ul>
                                {consultatie.investigatii.map((investigatie, idx) => (
                                    <li key={idx}>
                                        Denumire: {investigatie.denumire}, Durata: {investigatie.durata_procesare} zile, Rezultat: {investigatie.rezultat}
                                    </li>
                                ))}
                            </ul>
                            <Link to={`/update-consultatie/${consultatie.id}`}>Modificare consultație</Link>
                        </div>
                    ))
                ) : (
                    <p>Nu există consultații pentru această programare.</p>
                )}
            </div>
        </div>
    );
}
export default ListaConsultatii;
