import React, { useEffect, useState } from 'react';
import './adauga-doctor.css';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

function AdaugaDoctor() {
    const [email, setEmail] = useState('');
    const [nume, setNume] = useState('');
    const [prenume, setPrenume] = useState('');
    const [specializare, setSpecializare] = useState('');
    const [tel, setTel] = useState('');
    const [data_nasterii, setDataNasterii] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();
    const [isLoggedIn, setIsLoggedIn] = useState(true);

    const isAdmin = () => {
        const role = localStorage.getItem('role');
        return role === "administrator";
    }
    const handleSubmitDoctorData = async (id_user) => {
        try {
            const jwtToken = localStorage.getItem('token');
            const doctorResponse = await fetch('http://localhost:8000/api/doctors', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${jwtToken}`
                },
                body: JSON.stringify({
                    idUser: id_user,
                    nume: nume,
                    prenume: prenume,
                    specializare:specializare,
                    telefon: tel,
                    data_nasterii: data_nasterii,
                    email:email,
                }),
            });
    
            if (doctorResponse.ok) {
                console.log('Datele doctorului au fost trimise cu succes');
            } else {
                throw new Error('Eroare la trimiterea datelor doctorului');
            }
        } catch (error) {
            console.error('Eroare la trimiterea datelor doctorului:', error);
        }
    }; 

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8000/addDoctor', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                   username:email,
                    password:password,
                    role:"doctor",
                }),
            });
            const data = await response.json();
            if (response.ok) {
                console.log('Autentificare reusita:', data);
                const id_user=data.id_user;
                handleSubmitDoctorData(id_user);
                history.push("/lista-medici");
            } else {
                throw new Error(data.message || 'Eroare la autentificare');
            }
        } catch (error) {
            console.error('Eroare:', error);
        }
    };
    useEffect(() => {
        const role = localStorage.getItem('role');
        if (role) {
            setIsLoggedIn(true);
        } else {
            setIsLoggedIn(false);
        }
        console.log(isLoggedIn);
    }, []);

    if (!isLoggedIn || !isAdmin()) {
        return <p>Nu ești autorizat pentru această resursă.</p>;
    }
    return (
        <div>
            <h1>Adaugă un nou doctor</h1>
            <div className="login-container">
                <form className="login-form" onSubmit={handleLogin}>
                    <input
                        type="string"
                        value={nume}
                        onChange={(e) => setNume(e.target.value)}
                        placeholder="Nume"
                    />
                    <input
                        type="string"
                        value={prenume}
                        onChange={(e) => setPrenume(e.target.value)}
                        placeholder="Prenume"
                    />
                    <input
                        type="string"
                        value={tel}
                        onChange={(e) => setTel(e.target.value)}
                        placeholder="Telefon"
                    />
                    <input
                        type="date"
                        value={data_nasterii}
                        onChange={(e) => setDataNasterii(e.target.value)}
                        placeholder="Data nasterii"
                    />
                    <input
                        type="email"
                        value={email}
                        onChange={(e) => setEmail(e.target.value)}
                        placeholder="Email"
                    />
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        placeholder="Password"
                    />
                      <input
                        type="string"
                        value={specializare}
                        onChange={(e) => setSpecializare(e.target.value)}
                        placeholder="Specializare"
                    />
                    <button type="submit">Adaugă doctor</button>
                </form>
            </div>
        </div>
    );
}

export default AdaugaDoctor;
