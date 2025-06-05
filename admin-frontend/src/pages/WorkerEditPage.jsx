// src/pages/WorkerEditPage.jsx
import React, { useEffect, useState } from 'react';
import {
  Box, Typography, Button, Drawer, Stack, Table, TableHead, TableRow,
  TableCell, TableBody, Dialog, DialogTitle, DialogContent, DialogActions,
  TextField
} from '@mui/material';
import LogoutIcon from '@mui/icons-material/Logout';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const drawerWidth = 240;

const WorkerEditPage = () => {
  const [workers, setWorkers] = useState([]);
  const [selectedWorker, setSelectedWorker] = useState(null);
  const [editForm, setEditForm] = useState({});
  const [editOpen, setEditOpen] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    fetchWorkers();
  }, []);

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

  const handleLogout = () => {
    localStorage.removeItem('authToken');
    navigate('/');
  };

  const handleEdit = (worker) => {
    setSelectedWorker(worker);
    setEditForm(worker);
    setEditOpen(true);
  };

  const handleEditChange = (e) => {
    setEditForm({ ...editForm, [e.target.name]: e.target.value });
  };

  const handleEditSubmit = async () => {
    try {
      const token = localStorage.getItem('authToken');
      // 수정 API 호출
      await axios.put(
        'http://localhost:8000/api/update_user',
        { ...editForm },
        { headers: { Authorization: `Bearer ${token}` } }
      );
      setEditOpen(false);
      fetchWorkers();
    } catch (error) {
      alert('수정 실패: ' + error);
    }
  };

  const handleDelete = async (worker) => {
    if (!window.confirm(`${worker.name} 사용자를 정말 삭제하시겠습니까?`)) return;
    try {
      const token = localStorage.getItem('authToken');
      await axios.delete('http://localhost:8000/api/delete_user', {
        headers: { Authorization: `Bearer ${token}` },
        data: { phone_number: worker.phone_number }
      });
      fetchWorkers();
    } catch (error) {
      alert('삭제 실패: ' + error);
    }
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
          <Button variant="outlined" onClick={() => navigate('/qr')}>QR code</Button>
          <Button variant="outlined" onClick={() => navigate('/settings')}>Settings</Button>
          <Button variant="contained" disabled>Worker Edit</Button>
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
        <Typography variant="h5" gutterBottom>Worker Edit</Typography>

        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Position</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {workers.map((worker, idx) => (
              <TableRow key={idx}>
                <TableCell>{worker.name}</TableCell>
                <TableCell>{worker.affiliation}</TableCell>
                <TableCell>{worker.phone_number}</TableCell>
                <TableCell>
                  <Button
                    variant="outlined"
                    startIcon={<EditIcon />}
                    size="small"
                    onClick={() => handleEdit(worker)}
                  >수정</Button>
                  <Button
                    variant="outlined"
                    color="error"
                    startIcon={<DeleteIcon />}
                    size="small"
                    onClick={() => handleDelete(worker)}
                    sx={{ ml: 1 }}
                  >삭제</Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>

        {/* 수정 다이얼로그 */}
        <Dialog open={editOpen} onClose={() => setEditOpen(false)}>
          <DialogTitle>사용자 정보 수정</DialogTitle>
          <DialogContent>
            <TextField
              margin="dense"
              label="이름"
              name="name"
              value={editForm.name || ''}
              onChange={handleEditChange}
              fullWidth
            />
            <TextField
              margin="dense"
              label="소속"
              name="affiliation"
              value={editForm.affiliation || ''}
              onChange={handleEditChange}
              fullWidth
            />
            <TextField
              margin="dense"
              label="전화번호"
              name="phone_number"
              value={editForm.phone_number || ''}
              onChange={handleEditChange}
              fullWidth
            />
            {/* 필요시 다른 필드도 추가 */}
          </DialogContent>
          <DialogActions>
            <Button onClick={() => setEditOpen(false)}>취소</Button>
            <Button onClick={handleEditSubmit} variant="contained">저장</Button>
          </DialogActions>
        </Dialog>
      </Box>
    </Box>
  );
};

export default WorkerEditPage;
