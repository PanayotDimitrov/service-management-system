import React from "react";
import { BrowserRouter as Router, Routes, Route, useLocation } from "react-router-dom";
import Home from "./components/Home";
import CarList from "./components/CarList";
import GarageList from "./components/GarageList";
import MaintenanceList from "./components/MaintenanceList";
import NavBar from "./components/NavBar";

const App = () => {
  const location = useLocation(); 

  return (
    <>{location.pathname !== "/" && <NavBar />}
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/cars" element={<CarList />} />
        <Route path="/garages" element={<GarageList />} />
        <Route path="/maintenance" element={<MaintenanceList />} />
      </Routes>
    </>
  );
};

const AppWrapper = () => (
  <Router>
    <App />
  </Router>
);

export default AppWrapper;

