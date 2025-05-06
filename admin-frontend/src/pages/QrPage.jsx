// QrPage.jsx
import React, { useEffect, useState } from 'react';
import { Box, Typography, Button, CircularProgress, Drawer, Stack } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import LogoutIcon from '@mui/icons-material/Logout';
import axios from 'axios';

const drawerWidth = 240;

const QrPage = () => {
  const [qrData, setQrData] = useState(null);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const generateQr = async () => {
      try {
        const token = localStorage.getItem('authToken');
        const response = await axios.post('http://localhost:8000/api/generate_qr', {}, {
          headers: { Authorization: `Bearer ${token}` }
        });
        setQrData(response.data);
      } catch (err) {
        console.error('QR 생성 실패:', err);
        alert('QR 생성 실패');
      } finally {
        setLoading(false);
      }
    };
    generateQr();
  }, []);

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    navigate('/');
  };

  return (
    <Box sx={{ display: 'flex' }}>
      <Drawer
        variant="permanent"
        sx={{
          width: drawerWidth,
          flexShrink: 0,
          '& .MuiDrawer-paper': { width: drawerWidth, boxSizing: 'border-box', padding: 2 }
        }}
      >
        <Box sx={{ mb: 2 }}>
          <img src="/logo.png" alt="Safers" style={{ width: '100%' }} />
        </Box>

        <Stack spacing={1}>
          <Button variant="outlined" onClick={() => navigate('/dashboard')}>Dashboard</Button>
          <Button variant="contained" disabled>QR code</Button>
          <Button variant="outlined" onClick={() => navigate('/settings')}>Settings</Button>
          <Button variant="outlined" onClick={() => navigate('/worker-edit')}>Worker Edit</Button>
          <Button variant="outlined" startIcon={<LogoutIcon />} onClick={handleLogout}>Logout</Button>
        </Stack>
      </Drawer>

      <Box
        component="main"
        sx={{
          flexGrow: 1,
          p: 3,
          ml: `${drawerWidth}px`,
        }}
      >
        <Typography variant="h5" gutterBottom>QR Code</Typography>
        {loading ? (
          <CircularProgress />
        ) : qrData ? (
          <Box textAlign="center">
            <img
              src={`data:image/png;base64,${qrData.qr_image_base64}`}
              alt="QR Code"
              style={{ width: 250, border: '1px solid #ccc', padding: 8 }}
            />
            <Typography variant="caption" display="block">
              유효시간: {new Date(qrData.expires_at).toLocaleString()}
            </Typography>
          </Box>
        ) : (
          <Typography color="error">QR 생성 실패</Typography>
        )}
      </Box>
    </Box>
  );
};

export default QrPage;
