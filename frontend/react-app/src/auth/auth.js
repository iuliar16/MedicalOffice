import React, { useState } from 'react';
import './auth.css';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

function Auth() {
    const [email, setEmail] = useState('');
    const [nume, setNume] = useState('');
    const [prenume, setPrenume] = useState('');
    const [cnp, setCnp] = useState('');
    const [tel, setTel] = useState('');
    const [data_nasterii, setDataNasterii] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();
    const handleSubmitPatientData = async (id_user) => {
        try {
            const patientResponse = await fetch('http://localhost:8000/api/patients', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({
                    idUser: id_user,
                    nume: nume,
                    prenume: prenume,
                    cnp: cnp,
                    telefon: tel,
                    data_nasterii: data_nasterii,
                    email:email,
                    is_active:true
                }),
            });
    
            if (patientResponse.ok) {
                console.log('Datele pacientului au fost trimise cu succes');
            } else {
                throw new Error('Eroare la trimiterea datelor pacientului');
            }
        } catch (error) {
            console.error('Eroare la trimiterea datelor pacientului:', error);
        }
    }; 

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8000/autentificare', { 
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                   username:email,
                    password:password,
                    role:"pacient",
                }),
            });
            const data = await response.json();
            if (response.ok) {
                console.log('Autentificare reusita:', data);
                const id_user=data.id_user;
                handleSubmitPatientData(id_user);
                history.push("/login");
            } else {
                throw new Error(data.message || 'Eroare la autentificare');
            }
        } catch (error) {
            console.error('Eroare:', error);
        }
    };

    return (
        <div>
            <h1>Authentication Page</h1>
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
                        type="string"
                        value={cnp}
                        onChange={(e) => setCnp(e.target.value)}
                        placeholder="CNP"
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
                    <button type="submit">Create account</button>
                </form>
            </div>
        </div>
    );
}

export default Auth;
