import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import axios from 'axios';
import {
  Container,
  Card,
  CardContent,
  Typography,
  FormControl,
  TextField,
  Button,
} from '@mui/material';

function MaintenanceForm() {
  const [maintenance, setMaintenance] = useState({
    carId: '',
    serviceType: '',
    scheduledDate: '',
    status: '',
    garageId: '',
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      loadMaintenance();
    }
  }, [id]);

  const loadMaintenance = async () => {
    try {
      const response = await axios.get(`http://localhost:8088/maintenance/${id}`);
      setMaintenance(response.data);
    } catch (error) {
      console.error('Error loading maintenance record:', error);
    }
  };

  const handleChange = (e) => {
    setMaintenance({ ...maintenance, [e.target.name]: e.target.value });
  };

  const saveMaintenance = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await axios.put(`http://localhost:8088/maintenance/${id}`, maintenance);
      } else {
        await axios.post('http://localhost:8088/maintenance', maintenance);
      }
      navigate('/maintenance');
    } catch (error) {
      console.error('Error saving maintenance record:', error);
    }
  };

  return (
    <Container sx={{ mt: 4 }}>
      <Card>
        <CardContent>
          <Typography variant="h4" gutterBottom align="center">
            {id ? 'Edit Maintenance Record' : 'Add Maintenance Record'}
          </Typography>
          <form onSubmit={saveMaintenance}>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Car ID"
                name="carId"
                type="number"
                value={maintenance.carId}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Service Type"
                name="serviceType"
                value={maintenance.serviceType}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Scheduled Date"
                name="scheduledDate"
                type="date"
                value={maintenance.scheduledDate}
                onChange={handleChange}
                InputLabelProps={{
                  shrink: true,
                }}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Garage ID"
                name="garageId"
                type="number"
                value={maintenance.garageId}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Status"
                name="status"
                value={maintenance.status}
                onChange={handleChange}
                required
              />
            </FormControl>
            <Button variant="contained" color="primary" type="submit" fullWidth>
              Save
            </Button>
          </form>
        </CardContent>
      </Card>
    </Container>
  );
}

export default MaintenanceForm;
