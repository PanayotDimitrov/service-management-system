import React, { useState, useEffect } from "react";
import axios from "axios";
import { Table, Button, Container, Card, Form, Row, Col } from "react-bootstrap";

const GarageList = () => {

  const [garages, setGarages] = useState([]);
  const [newGarage, setNewGarage] = useState({ name: "", location: "", capacity: 0 });
  const [isUpdating, setIsUpdating] = useState(false);
  const [garageToUpdate, setGarageToUpdate] = useState(null);
  const [filterCity, setFilterCity] = useState("");
  const [reportData, setReportData] = useState([]);
  const [reportFilter, setReportFilter] = useState({ garageId: "", startDate: "", endDate: "" });


  const fetchGarages = async () => {
    try {
      const response = await axios.get("http://localhost:8088/garages", {
        params: { city: filterCity || undefined },
      });
      setGarages(response.data);
    } catch (error) {
      console.error("Error fetching garages:", error);
    }
  };

  const handleAddGarage = async () => {
    try {
      const response = await axios.post("http://localhost:8088/garages", newGarage);
      alert("Garage added successfully!");
      setGarages([...garages, response.data]);
      setNewGarage({ name: "", location: "", city: "", capacity: 0 });
    } catch (error) {
      console.error("Error adding garage:", error);
      alert("An error occurred while adding the garage.");
    }
  };

  const handleUpdateGarage = async () => {
    if (!garageToUpdate || !garageToUpdate.id) {
      alert("No garage selected for update.");
      console.log("garageToUpdate is invalid:", garageToUpdate);
      return;
    }
    try {
      console.log("Updating garage with ID:", garageToUpdate.id);
      const response = await axios.put(
        `http://localhost:8088/garages/${garageToUpdate.id}`,
        garageToUpdate
      );
      alert("Garage updated successfully!");
      fetchGarages();
      setIsUpdating(false);
      setGarageToUpdate(null);
    } catch (error) {
      console.error("Error updating garage:", error);
      alert("An error occurred while updating the garage.");
    }
  };
  const handleDeleteGarage = async (garageName) => {
    if (window.confirm("Are you sure you want to delete this garage?")) {
      try {
        await axios.delete(`http://localhost:8088/garages/${garageName}`);
        alert("Garage deleted successfully!");
        fetchGarages();
      } catch (error) {
        console.error("Error deleting garage:", error);
        alert("An error occurred while deleting the garage.");
      }
    }
  };

  const fetchGarageReport = async () => {
    const { garageId, startDate, endDate } = reportFilter;

    if (!garageId || !startDate || !endDate) {
      alert("Please provide all fields for the report.");
      return;
    }

    try {
      const response = await axios.get("http://localhost:8088/garages/dailyAvailabilityReport", {
        params: { garageId, startDate, endDate },
      });

      const modifiedData = response.data.map((entry) => ({
        ...entry,
        requests: entry.requests > 0 ? `${entry.requests} requests` : "No Requests",
      }));

      console.log("Modified Data:", modifiedData);
      setReportData(modifiedData);
    } catch (error) {
      console.error("Error fetching garage report:", error);
      alert("An error occurred while fetching the report.");
    }
  };


  const handleChange = (e) => {
    const { name, value } = e.target;
    if (isUpdating) {
      setGarageToUpdate((prevGarage) => ({ ...prevGarage, [name]: value }));
    } else {
      setNewGarage((prevGarage) => ({ ...prevGarage, [name]: value }));
    }
  };

  const handleReportFilterChange = (e) => {
    const { name, value } = e.target;
    setReportFilter((prevFilter) => ({ ...prevFilter, [name]: value }));
  };

  useEffect(() => {
    fetchGarages();
  }, [filterCity]);

  return (
    <Container className="mt-4">
      <Card className="shadow-lg">
        <Card.Header style={{ backgroundColor: "#FFC107", color: "#000000" }}>
          <h3 className="text-center">Manage Garages</h3>
        </Card.Header>
        <Card.Body>
          <Form className="mb-3">
            <Row>
              <Col md={6}>
                <Form.Group>
                  <Form.Label>Filter by City</Form.Label>
                  <Form.Control
                    type="text"
                    placeholder="Enter city"
                    value={filterCity}
                    onChange={(e) => setFilterCity(e.target.value)}
                  />

                </Form.Group>
              </Col>
            </Row>
          </Form>
          <Table striped bordered hover responsive className="text-center">
            <thead style={{ backgroundColor: "#000000", color: "#FFC107" }}>
              <tr>
                <th>Name</th>
                <th>Location</th>
                <th>City</th>
                <th>Capacity</th>
                <th>Actions</th>
              </tr>
            </thead>
            <tbody>
              {garages.map((garage, index) => (
                <tr key={garage.id}>
                  <td>{garage.name}</td>
                  <td>{garage.location}</td>
                  <td>{garage.city}</td>
                  <td>{garage.capacity}</td>
                  <td>
                    <Button
                      style={{ backgroundColor: "#FFC107", border: "none", color: "#000000" }}
                      size="sm"
                      className="me-2"
                      onClick={() => setGarageToUpdate(garage) || setIsUpdating(true)}
                    >
                      Edit
                    </Button>
                    <Button
                      style={{ backgroundColor: "#000000", border: "none", color: "#FFC107" }}
                      size="sm"
                      onClick={() => handleDeleteGarage(garage.id)}
                    >
                      Delete
                    </Button>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
          <h4 className="mt-4">{isUpdating ? "Update Garage" : "Add a New Garage"}</h4>
          <Form>
            <Row>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Garage Name</Form.Label>
                  <Form.Control
                    type="text"
                    name="name"
                    placeholder="Enter name"
                    value={isUpdating ? garageToUpdate?.name || "" : newGarage.name}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Location</Form.Label>
                  <Form.Control
                    type="text"
                    name="location"
                    placeholder="Enter location"
                    value={isUpdating ? garageToUpdate?.location || "" : newGarage.location}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>City</Form.Label>
                  <Form.Control
                    type="text"
                    name="City"
                    placeholder="Enter City"
                    value={isUpdating ? garageToUpdate?.City || "" : newGarage.City}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
              <Col md={6}>
                <Form.Group className="mb-3">
                  <Form.Label>Capacity</Form.Label>
                  <Form.Control
                    type="number"
                    name="capacity"
                    placeholder="Enter capacity"
                    value={isUpdating ? garageToUpdate?.capacity || "" : newGarage.capacity}
                    onChange={handleChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Button
              style={{ backgroundColor: "#FFC107", border: "none", color: "#000000" }}
              className="w-100"
              onClick={isUpdating ? handleUpdateGarage : handleAddGarage}
            >
              {isUpdating ? "Update Garage" : "Add Garage"}
            </Button>
          </Form>
          <h4 className="mt-4">Garage Report</h4>
          <Form>
            <Row>
              <Col md={4}>

                <Form.Group className="mb-3">
                  <Form.Label>Garage</Form.Label>
                  <Form.Select
                    name="garageId"
                    value={reportFilter.garageId}
                    onChange={handleReportFilterChange}
                  >
                    <option value="">Select a Garage</option>
                    {garages.map((garage) => (
                      <option key={garage.id} value={garage.id}>
                        {garage.name}
                      </option>
                    ))}
                  </Form.Select>
                </Form.Group>
              </Col>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>Start Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="startDate"
                    value={reportFilter.startDate}
                    onChange={handleReportFilterChange}
                  />
                </Form.Group>
              </Col>
              <Col md={4}>
                <Form.Group className="mb-3">
                  <Form.Label>End Date</Form.Label>
                  <Form.Control
                    type="date"
                    name="endDate"
                    value={reportFilter.endDate}
                    onChange={handleReportFilterChange}
                  />
                </Form.Group>
              </Col>
            </Row>
            <Button
              style={{ backgroundColor: "#FFC107", border: "none", color: "#000000" }}
              className="w-100"
              onClick={fetchGarageReport}
            >
              Generate Report
            </Button>
          </Form>

          <Table striped bordered hover responsive className="text-center mt-4">
            <thead style={{ backgroundColor: "#000000", color: "#FFC107" }}>
              <tr>
                <th>Date</th>
                <th>Requests</th>
                <th>Available Capacity</th>
              </tr>
            </thead>
            <tbody>
              {reportData.length > 0 ? (
                reportData.map((report, index) => (
                  <tr key={index}>
                    <td>{report.date}</td>
                    <td>{report.requests}</td>
                    <td>{report.availableCapacity}</td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="3">No data available for the selected criteria.</td>
                </tr>
              )}
            </tbody>
          </Table>
        </Card.Body>
      </Card>
    </Container>
  );
};

export default GarageList;




