import React, { useState, useEffect } from 'react';
import './update-consultatie.css';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

function UpdateConsultatie() {
    const [diagnostic, setDiagnostic] = useState('');
    const [dataConsultatie, setDataConsultatie] = useState('');
    const [investigatii, setInvestigatii] = useState([]);
    const [consultatii, setConsultatii] = useState([]);
    const consultatieId= useParams();
    //console.log(consultatieId);

    const getConsultatii = async () => {
        try {
            console.log(consultatieId);

            const response = await fetch(`http://localhost:8000/api/consultatii/?id=${consultatieId.consultatieId}`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            const cons = await response.json();
            setConsultatii(cons);
            console.log(cons)
            if (cons) {
                setDiagnostic(cons.diagnostic);
                setDataConsultatie(cons.data);
                setInvestigatii(cons.investigatii || []);
            }
            console.log(cons);
        } catch (error) {
            console.error('Eroare la preluarea datelor:', error);
        }
    };

    useEffect(() => {
        getConsultatii();
        console.log(consultatii);
    }, [consultatieId]);

    const handleUpdate = (event) => {
        event.preventDefault();
        alert(`Consultatie actualizata: ${diagnostic}, ${dataConsultatie}, investigatii: ${JSON.stringify(investigatii)}`);
    };

    const handleInvestigatieChange = (index, field, value) => {
        const updatedInvestigatii = [...investigatii];
        updatedInvestigatii[index][field] = value;
        setInvestigatii(updatedInvestigatii);
    };

    const handleAdaugaInvestigatie = () => {
        setInvestigatii([...investigatii, { denumire: '', durataProcesare: '', rezultat: '' }]);
    };

    return (
        <div className="update-consultatie">
            <h1>Actualizează Consultația</h1>
            <form onSubmit={handleUpdate}>
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
                                type="text"
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
                <button type="submit">Actualizează Consultație</button>
            </form>
        </div>
    );
}

export default UpdateConsultatie;
