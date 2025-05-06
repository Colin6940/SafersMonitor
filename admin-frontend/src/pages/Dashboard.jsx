// Dashboard.jsx
import React, { useEffect, useState } from 'react';
import {
  Box, Typography, Table, TableHead, TableRow, TableCell, TableBody, Button,
  Drawer, Stack, TextField, InputAdornment, Select, MenuItem
} from '@mui/material';
import SearchIcon from '@mui/icons-material/Search';
import InfoIcon from '@mui/icons-material/Info';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const drawerWidth = 240;

const Dashboard = () => {
  const [workers, setWorkers] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchWorkers = async () => {
      try {
        const token = localStorage.getItem('authToken');
        const response = await axios.get('http://localhost:8000/api/users_status', {
          headers: { Authorization: `Bearer ${token}` }
        });
        setWorkers(response.data);
      } catch (error) {
        console.error('Error fetching workers:', error);
        if (error.response && error.response.status === 401) {
          localStorage.removeItem('authToken');
          navigate('/login');
        }
      }
    };
    fetchWorkers();
  }, [navigate]);

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
          <Button variant="contained" disabled>Dashboard</Button>
          <Button variant="outlined" onClick={() => navigate('/qr')}>QR code</Button>
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
        <Typography variant="h5" gutterBottom>Dashboard</Typography>
        <Typography variant="subtitle1" color="green" gutterBottom>Active Members</Typography>

        <Box sx={{ display: 'flex', justifyContent: 'space-between', mb: 2 }}>
          <TextField
            variant="outlined"
            placeholder="Search"
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
          />
          <Select defaultValue="Newest">
            <MenuItem value="Newest">Newest</MenuItem>
            <MenuItem value="Oldest">Oldest</MenuItem>
          </Select>
        </Box>

        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Position</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Heartrate</TableCell>
              <TableCell>Inactivity</TableCell>
              <TableCell>Steps/min</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workers.map((worker, index) => (
              <TableRow key={index}>
                <TableCell>{worker.name}</TableCell>
                <TableCell>{worker.affiliation}</TableCell>
                <TableCell>{worker.phone_number}</TableCell>
                <TableCell>{worker.heart_rate}</TableCell>
                <TableCell>{worker.inactivity_time}</TableCell>
                <TableCell>{worker.steps_per_minute}</TableCell>
                <TableCell>
                  <Stack direction="row" spacing={1}>
                    <Button variant="outlined" size="small" startIcon={<InfoIcon />}>More</Button>
                    <Button variant="contained" size="small" startIcon={<LocationOnIcon />}>GPS</Button>
                  </Stack>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>

        <Typography variant="caption" sx={{ mt: 2, display: 'block' }}>
          Showing {workers.length} of {workers.length} entries
        </Typography>
      </Box>
    </Box>
  );
};

export default Dashboard;
