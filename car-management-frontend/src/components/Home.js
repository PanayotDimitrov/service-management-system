import React from "react";
import { Link } from "react-router-dom";
import { Container, Card, Button, Row, Col } from "react-bootstrap";

const Home = () => {
  return (
    <div
      style={{
        height: "100vh",
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
      }}
    >
      <Container>
        <Card className="shadow-lg text-center">
          {/* Заглавие с жълто-черна тема */}
          <Card.Header
            style={{
              backgroundColor: "#FFC107", // Жълт фон
              color: "#000000", // Черен текст
            }}
          >
            <h2>Welcome to Car Management System</h2>
          </Card.Header>
          <Card.Body>
            <Row>
              {/* Cars Section */}
              <Col md={4}>
                <Card
                  className="mb-3 border-0 shadow"
                  style={{ backgroundColor: "#FFC107", color: "#000000" }}
                >
                  <Card.Body>
                    <h4>Cars</h4>
                    <p>View, add, and manage cars.</p>
                    <Button
                      as={Link}
                      to="/cars"
                      style={{
                        backgroundColor: "#000000", // Черен бутон
                        border: "none",
                        color: "#FFC107", // Жълт текст
                      }}
                      size="lg"
                    >
                      Go to Cars
                    </Button>
                  </Card.Body>
                </Card>
              </Col>

              {/* Garages Section */}
              <Col md={4}>
                <Card
                  className="mb-3 border-0 shadow"
                  style={{ backgroundColor: "#FFC107", color: "#000000" }}
                >
                  <Card.Body>
                    <h4>Garages</h4>
                    <p>Manage garage information.</p>
                    <Button
                      as={Link}
                      to="/garages"
                      style={{
                        backgroundColor: "#000000", // Черен бутон
                        border: "none",
                        color: "#FFC107", // Жълт текст
                      }}
                      size="lg"
                    >
                      Go to Garages
                    </Button>
                  </Card.Body>
                </Card>
              </Col>

              {/* Maintenance Section */}
              <Col md={4}>
                <Card
                  className="mb-3 border-0 shadow"
                  style={{ backgroundColor: "#FFC107", color: "#000000" }}
                >
                  <Card.Body>
                    <h4>Maintenance</h4>
                    <p>Track and schedule maintenance.</p>
                    <Button
                      as={Link}
                      to="/maintenance"
                      style={{
                        backgroundColor: "#000000", // Черен бутон
                        border: "none",
                        color: "#FFC107", // Жълт текст
                      }}
                      size="lg"
                    >
                      Go to Maintenance
                    </Button>
                  </Card.Body>
                </Card>
              </Col>
            </Row>
          </Card.Body>
        </Card>
      </Container>
    </div>
  );
};

export default Home;
