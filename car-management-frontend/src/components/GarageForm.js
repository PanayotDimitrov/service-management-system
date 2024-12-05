import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { FormControl, TextField, Button, Container, Card, CardContent, Typography } from '@mui/material';

function GarageForm() {
  const [garage, setGarage] = useState({
    name: '',
    location: '',
    capacity: 0,
    availableSpots: 0,
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      loadGarage();
    }
  }, [id]);

  const loadGarage = async () => {
    try {
      const response = await axios.get(`http://localhost:8088/garages/${id}`);
      setGarage(response.data);
    } catch (error) {
      console.error('Error loading garage:', error);
    }
  };

  const handleChange = (e) => {
    setGarage({ ...garage, [e.target.name]: e.target.value });
  };

  const saveGarage = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await axios.put(`http://localhost:8088/garages/${id}`, garage);
      } else {
        await axios.post('http://localhost:8088/garages', garage);
      }
      navigate('/garages');
    } catch (error) {
      console.error('Error saving garage:', error);
    }
  };

  return (
    <Container sx={{ mt: 4 }}>
      <Card>
        <CardContent>
          <Typography variant="h4" gutterBottom align="center">
            {id ? 'Edit Garage' : 'Add Garage'}
          </Typography>
          <form onSubmit={saveGarage}>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Garage Name"
                name="name"
                value={garage.name}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Location"
                name="location"
                value={garage.location}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Capacity"
                name="capacity"
                type="number"
                value={garage.capacity}
                onChange={handleChange}
                required
              />
            </FormControl>
            <FormControl fullWidth sx={{ mb: 3 }}>
              <TextField
                label="Available Spots"
                name="availableSpots"
                type="number"
                value={garage.availableSpots}
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

export default GarageForm;
