import React, { useEffect, useState } from 'react';
import { Link } from 'react-router-dom';
import './homePage.css';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';

function HomePage() {
    const history = useHistory();
    const [isLoggedIn, setIsLoggedIn] = useState(true);

    useEffect(() => {
        const role = localStorage.getItem('role');
        if (role) {
            setIsLoggedIn(true);
        } else {
            setIsLoggedIn(false);
        }
        console.log(isLoggedIn);
    }, []);
    const isPacient = () => {
        const role = localStorage.getItem('role');
        return role === "pacient";
    }

    const isDoctor = () => {
        const role = localStorage.getItem('role');
        return role === "doctor";
    }
    const isAdmin = () => {
        const role = localStorage.getItem('role');
        return role === "administrator";
    }
    const handleLogout = async() => {

        const jwtToken = localStorage.getItem('token');
        // await fetch('http://localhost:8000/logout', {
        //     method: 'POST',
        //     headers: {
        //         'Authorization': `Bearer ${jwtToken}`
        //     },
        //     body: JSON.stringify({
        //         jwtToken:jwtToken,
        //      }),
        // });

        await fetch('http://localhost:8000/logout', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${jwtToken}`
            },
            body: JSON.stringify({ jwtToken: jwtToken })
        });
        
        localStorage.removeItem('token');
        localStorage.removeItem('role');
        localStorage.removeItem('user_id');
        history.push('/login');
    };

    if (!isLoggedIn) {
        return <p>Nu ești autorizat pentru această resursă.</p>;
    }
    return (
        <div className="pacient-page">
            {isPacient() && (
                <p>Pacient id: {localStorage.getItem('user_id')}</p>
            )}
             {isDoctor() && (
                <p>Doctor id: {localStorage.getItem('user_id')}</p>
            )}
            {isAdmin() && (
                <p>Administrator page </p>
            )}
            <h1>HomePage</h1>
            {isLoggedIn && (
                <>
                    <div className="pacient-actions">
                                <button><Link to="/date-personale">Vizualizează Date Personale</Link></button>
                                {isPacient() && (
                            <>
                                <button><Link to="/istoric-medical">Vizualizează Istoricul Medical</Link></button>
                                <button><Link to="/lista-medici">Vizualizează Lista de Medici</Link></button>
                                <button><Link to="/solicita-programare">Solicită o Programare</Link></button>
                                <button><Link to="/lista-programari">Vizualizează Lista de Programări</Link></button>
                            </>
                        )}
                        {isDoctor() && (
                            <>
                                <button><Link to="/lista-pacienti">Vizualizează Lista de pacienți</Link></button>
                            </>
                        )}
                        {isAdmin() && (
                            <>
                                <button><Link to="/adauga-doctor">Adaugă doctor</Link></button>
                            </>
                        )}
                    </div>
                    <div className="logout-button">
                        <button onClick={handleLogout}>Logout</button>
                    </div>
                </>
            )
            }

        </div >
    );
}

export default HomePage;
