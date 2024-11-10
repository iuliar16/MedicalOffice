import React, { useState } from 'react';
import './login.css';
import { Link } from 'react-router-dom';
import { useHistory } from 'react-router-dom/cjs/react-router-dom.min';
import { jwtDecode } from "jwt-decode";


function Login() {
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');
    const history = useHistory();

    const handleLogin = async (event) => {
        event.preventDefault();
        try {
            const response = await fetch('http://localhost:8000/login', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify({
                    username: email,
                    password: password,
                }),
            });
            const data = await response.json();
            if (response.ok) {
                const decoded = jwtDecode(data.jwtToken);

                localStorage.setItem('token', data.jwtToken);
                localStorage.setItem('user_id', decoded.sub);
                localStorage.setItem('role', decoded.role);
                
                console.log('Logare reusita:', data);
                history.push("/home");
            } else {
                throw new Error(data.message || 'Eroare la logare');
            }
        } catch (error) {
            console.error('Eroare:', error);
        }
    };

    return (

        <div>
            <h1>Login Page</h1>
            <div className="login-container">
                <form className="login-form" onSubmit={handleLogin}>
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
                    <button type="submit">Login</button>
                </form>
                <p>Don't have an account? <Link to="/auth">Sign Up</Link></p>
            </div>
        </div>
    );
}

export default Login;