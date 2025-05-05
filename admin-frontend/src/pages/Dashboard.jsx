import React, { useEffect, useState } from 'react';
import { Box, Typography, Table, TableHead, TableRow, TableCell, TableBody, Button, Drawer, List, ListItem, ListItemText, TextField, InputAdornment, IconButton, Select, MenuItem, Stack } from '@mui/material';
import axios from 'axios';
import SearchIcon from '@mui/icons-material/Search';
import InfoIcon from '@mui/icons-material/Info';
import LocationOnIcon from '@mui/icons-material/LocationOn';
import LogoutIcon from '@mui/icons-material/Logout';
import { useNavigate } from 'react-router-dom';

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

    const getStatusColor = (heart_rate) => {
        const bpm = parseInt(heart_rate.replace('bpm', ''));
        if (bpm >= 160) return 'error';
        return 'success';
    };

    return (
        <Box sx={{ display: 'flex' }}>
            <Drawer
                variant="permanent"
                sx={{
                    width: 240,
                    flexShrink: 0,
                    '& .MuiDrawer-paper': { width: 240, boxSizing: 'border-box' },
                }}
            >
                <Box sx={{ p: 2 }}>
                    <img src="/logo.png" alt="Safers" style={{ width: '100%' }} />
                </Box>
                <List>
                    {['Dashboard', 'Access QR code', 'Manage Menu', 'Settings', 'Accounts'].map((text) => (
                        <ListItem button key={text}>
                            <ListItemText primary={text} />
                        </ListItem>
                    ))}
                    <ListItem button onClick={handleLogout}>
                        <LogoutIcon sx={{ mr: 1 }} />
                        <ListItemText primary="Logout" />
                    </ListItem>
                </List>
                <Box sx={{ p: 2 }}>
                    <img src="/qrcode.png" alt="QR Code" style={{ width: '100%' }} />
                    <Typography variant="caption" display="block" align="center">share</Typography>
                </Box>
            </Drawer>

            <Box component="main" sx={{ flexGrow: 1, p: 3 }}>
                <Typography variant="h5" gutterBottom>All Workers</Typography>
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
                            <TableCell>Customer Name</TableCell>
                            <TableCell>Position</TableCell>
                            <TableCell>Phone Number</TableCell>
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
                                        <Button variant="outlined" size="small" color="primary" startIcon={<InfoIcon />}>
                                            More
                                        </Button>
                                        <Button variant="contained" size="small" color="secondary" startIcon={<LocationOnIcon />}>
                                            GPS
                                        </Button>
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
