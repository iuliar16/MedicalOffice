import React, { useState } from 'react';
import './adauga-consultatie.css';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

function AdaugaConsultatie() {
    const [diagnostic, setDiagnostic] = useState('');
    const [dataConsultatie, setDataConsultatie] = useState('');
    const [investigatii, setInvestigatii] = useState([{ denumire: '', durataProcesare: '', rezultat: '' }]);
    const { idDoctor, idPacient } = useParams();

    const handleAdauga = async (event) => {
        event.preventDefault();
        const consultatie = {
            idPacient: idPacient,
            idDoctor: idDoctor,
            data: dataConsultatie,
            diagnostic: diagnostic,
            investigatii: investigatii
        };
        const jwtToken = localStorage.getItem('token');
        const requestOptions = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify(consultatie)
        };
        try {
            const response = await fetch('http://localhost:8000/api/consultatii', requestOptions);
            if (response.ok) {
                const responseData = await response.json();
                console.log(responseData);
                alert('Consultatie adaugata cu succes!');
            } else {
                alert('Eroare la adaugarea consultatiei.');
            }
        } catch (error) {
            console.error('Eroare la trimiterea datelor:', error);
        }
    
    };
    

    const handleInvestigatieChange = (index, field, value) => {
        const updatedInvestigatii = [...investigatii];
        updatedInvestigatii[index][field] = field === 'durataProcesare' ? parseInt(value, 10) : value;
        setInvestigatii(updatedInvestigatii);
    };

    const handleAdaugaInvestigatie = () => {
        setInvestigatii([...investigatii, { denumire: '', durataProcesare: '', rezultat: '' }]);
    };

    return (
        <div className="adauga-consultatie">
            <h1>Adaugă o Consultație</h1>
            <form onSubmit={handleAdauga}>
                <label>
                    Diagnostic:
                    <input
                        type="text"
                        value={diagnostic}
                        onChange={(e) => setDiagnostic(e.target.value)}
                    />
                </label>
                <label>
                    Data Consultației:
                    <input
                        type="date"
                        value={dataConsultatie}
                        onChange={(e) => setDataConsultatie(e.target.value)}
                    />
                </label>
                <div className="investigatii">
                    <h3>Investigații</h3>
                    {investigatii.map((inv, index) => (
                        <div key={index} className="investigatie">
                            <input
                                type="text"
                                placeholder="Denumire"
                                value={inv.denumire}
                                onChange={(e) => handleInvestigatieChange(index, 'denumire', e.target.value)}
                            />
                            <input
                                type="number"
                                placeholder="Durata Procesare"
                                value={inv.durataProcesare}
                                onChange={(e) => handleInvestigatieChange(index, 'durataProcesare', e.target.value)}
                            />
                            <input
                                type="text"
                                placeholder="Rezultat"
                                value={inv.rezultat}
                                onChange={(e) => handleInvestigatieChange(index, 'rezultat', e.target.value)}
                            />
                        </div>
                    ))}
                    <button type="button" onClick={handleAdaugaInvestigatie}>Adaugă Investigatie</button>
                </div>
                <button type="submit">Adaugă Consultație</button>
            </form>
        </div>
    );
}

export default AdaugaConsultatie;
