import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { useParams, useNavigate } from 'react-router-dom';
import { Form, Button, Container, Card } from 'react-bootstrap';

function CarForm() {
  const [car, setCar] = useState({
    make: '',
    model: '',
    productionYear: '',
    licensePlate: '',
    garageId: ''
  });

  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    if (id) {
      loadCar();
    }
  }, [id]);

  const loadCar = async () => {
    try {
      const response = await axios.get(`http://localhost:8088/cars/${id}`);
      setCar(response.data);
    } catch (error) {
      console.error('Error loading car:', error);
    }
  };

  const handleChange = (e) => {
    setCar({ ...car, [e.target.name]: e.target.value });
  };

  const saveCar = async (e) => {
    e.preventDefault();
    try {
      if (id) {
        await axios.put(`http://localhost:8088/cars/${id}`, car);
      } else {
        await axios.post('http://localhost:8088/cars', car);
      }
      navigate('/');
    } catch (error) {
      console.error('Error saving car:', error);
    }
  };

  return (
    <Container className="mt-5">
      <Card>
        <Card.Header className="text-center">
          <h2>{id ? 'Edit Car' : 'Add Car'}</h2>
        </Card.Header>
        <Card.Body>
          <Form onSubmit={saveCar}>
            <Form.Group className="mb-3">
              <Form.Label>Make</Form.Label>
              <Form.Control
                type="text"
                name="make"
                value={car.make}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Model</Form.Label>
              <Form.Control
                type="text"
                name="model"
                value={car.model}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Production Year</Form.Label>
              <Form.Control
                type="number"
                name="productionYear"
                value={car.productionYear}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>License Plate</Form.Label>
              <Form.Control
                type="text"
                name="licensePlate"
                value={car.licensePlate}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Form.Group className="mb-3">
              <Form.Label>Garage ID</Form.Label>
              <Form.Control
                type="number"
                name="garageId"
                value={car.garageId}
                onChange={handleChange}
                required
              />
            </Form.Group>
            <Button variant="primary" type="submit" className="w-100">
              Save
            </Button>
          </Form>
        </Card.Body>
      </Card>
    </Container>
  );
}

export default CarForm;
