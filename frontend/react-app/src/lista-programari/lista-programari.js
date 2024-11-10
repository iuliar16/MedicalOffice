import React, { useEffect, useState } from 'react';
import './lista-programari.css';
import { Link } from 'react-router-dom';
import { useParams } from 'react-router-dom/cjs/react-router-dom.min';

function ListaProgramari() {
    const [programari, setProgramari] = useState([]);
    const { cnp } = useParams();
    console.log(cnp);

    const fetchPatientData = async () => {
        const user_id = localStorage.getItem('user_id');
        const jwtToken = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8000/api/patients/?id=${user_id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        return data;
    };
    const fetchPhysicianData = async (id) => {
        const jwtToken = localStorage.getItem('token');
        const response = await fetch(`http://localhost:8000/api/doctors/?id_doctor=${id}`, {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${jwtToken}`
            }
        });
        const data = await response.json();
        return data;
    };
    const showProgramari = async () => {
        try {
            const datePacient = await fetchPatientData();
            console.log(datePacient)
            const cnp = datePacient.cnp;
            const response = await fetch(`http://localhost:8000/api/patients/${cnp}/programari`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            const programari = await response.json();

            const programariCuDoctori = await Promise.all(programari.map(async (programare) => {
                console.log(programari)
                console.log(programare.programareKey.idDoctor);
                const doctor = await fetchPhysicianData(programare.programareKey.idDoctor);
                return { ...programare, doctor: doctor };
            }));

            setProgramari(programariCuDoctori);
            console.log(programariCuDoctori);
        } catch (error) {
            console.error('Eroare la preluarea datelor:', error);
        }
    };

    const showProgramariByDoctorView = async (cnp) => {
        try {
            const response = await fetch(`http://localhost:8000/api/patients/${cnp}/programari`, {
                headers: {
                    'Authorization': `Bearer ${localStorage.getItem('token')}`
                }
            });
            const programari = await response.json();

            const programariCuDoctori = await Promise.all(programari.map(async (programare) => {
                console.log(programari)
                console.log(programare.programareKey.idDoctor);
                const doctor = await fetchPhysicianData(programare.programareKey.idDoctor);
                return { ...programare, doctor: doctor };
            }));

            setProgramari(programariCuDoctori);
            console.log(programariCuDoctori);
        } catch (error) {
            console.error('Eroare la preluarea datelor:', error);
        }
    };

    useEffect(() => {
        const role = localStorage.getItem('role');
        if (role === "pacient")
            showProgramari();
        else {
            console.log("esti doctor si vrei sa vezi lista de programari a unui pacient");
            showProgramariByDoctorView(cnp);
        }
    }, []);
    const isDoctor = () => {
        return localStorage.getItem('role') === "doctor";
    }


    return (
        <div className="programari">
            {programari.map((programare, index) => (
                <div key={index} className="programare">
                    <p><strong>Doctor:</strong> {programare.doctor ? `${programare.doctor.nume} ${programare.doctor.prenume}` : 'N/A'}</p>
                    <p><strong>Data:</strong> {programare.programareKey.data}</p>
                    <p><strong>Status:</strong> {programare.status}</p>
                    {isDoctor() && (
                        <>
                            <button>
                                <Link to={`/adauga-consultatie/${programare.programareKey.idDoctor}/${programare.programareKey.idPacient}`}>Adaugă consultație</Link>
                            </button>
                            <button><Link to={`/lista-consultatii/${programare.programareKey.idDoctor}/${programare.programareKey.idPacient}`}>Vezi consultații</Link></button>
                        </>)}
                </div>
            ))}
        </div>


    );
}

export default ListaProgramari;
