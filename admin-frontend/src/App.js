// App.js
import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import LoginForm from './components/LoginForm';
import Dashboard from './pages/Dashboard';
import QrPage from './pages/QrPage';
import SettingsPage from './pages/SettingsPage';
import WorkerEditPage from './pages/WorkerEditPage';

const App = () => {
    return (
        <Router>
            <Routes>
                <Route path="/" element={<LoginForm />} />
                <Route path="/dashboard" element={<Dashboard />} />
                <Route path="/qr" element={<QrPage />} />
                <Route path="/settings" element={<SettingsPage />} />
                <Route path="/worker-edit" element={<WorkerEditPage />} />
            </Routes>
        </Router>
    );
};

export default App;
