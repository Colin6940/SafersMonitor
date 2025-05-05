import React, { useState } from 'react';
import { TextField, Button, Box, Typography } from '@mui/material';
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

const LoginForm = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async () => {
        try {
            const response = await axios.post('http://localhost:8000/api/login_admin', { username, password });
            localStorage.setItem('authToken', response.data.token);
            navigate('/dashboard');
        } catch (error) {
            alert('로그인 실패');
        }
    };

    return (
        <Box sx={{ width: 300, margin: 'auto', textAlign: 'center', mt: 10 }}>
            {/* ✅ 로고 추가 */}
            <img src="/logo.png" alt="Safers Logo" style={{ width: '150px', marginBottom: '20px' }} />

            <Typography variant="h5" gutterBottom>관리자 로그인</Typography>
            <TextField
                label="Username"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                fullWidth
                margin="normal"
            />
            <TextField
                label="Password"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                fullWidth
                margin="normal"
            />
            {error && <Typography color="error">{error}</Typography>}
            <Button variant="contained" fullWidth onClick={handleLogin}>Login</Button>
        </Box>
    );
};

export default LoginForm;
