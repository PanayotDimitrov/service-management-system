import React from "react";
import { Navbar, Nav, Container } from "react-bootstrap";
import { Link } from "react-router-dom";

const NavBar = () => {
  return (
    <Navbar
      expand="lg"
      style={{ backgroundColor: "#343a40" }} 
      variant="dark"
      className="shadow-sm"
    >
      <Container>
        <Navbar.Brand
          as={Link}
          to="/"
          style={{ fontWeight: "bold", color: "#FFC107" }} 
        >
          Car Management
        </Navbar.Brand>

        <Navbar.Toggle aria-controls="basic-navbar-nav" />
        <Navbar.Collapse id="basic-navbar-nav">
          <Nav className="ms-auto">
            <Nav.Link
              as={Link}
              to="/cars"
              style={{ color: "#FFFFFF", marginRight: "15px" }}
            >
              Cars
            </Nav.Link>
            <Nav.Link
              as={Link}
              to="/garages"
              style={{ color: "#FFFFFF", marginRight: "15px" }}
            >
              Garages
            </Nav.Link>
            <Nav.Link
              as={Link}
              to="/maintenance"
              style={{ color: "#FFFFFF" }}
            >
              Maintenance
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>
  );
};

export default NavBar;
