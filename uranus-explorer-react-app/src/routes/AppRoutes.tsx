import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Home from "../pages/Home";
import Dashboard from "../pages/Dashboard";
import Profile from "../pages/Profile";
import Login from "../pages/Login";
import Signup from "../pages/Signup";
import Services from "../pages/Services";
import HomeLayout from "../components/HomeLayout/HomeLayout";
import { isAuthenticated } from "../utils/tokenUtils";
import ServiceDetails from "../pages/ServiceDetails/ServiceDetails";

interface RouteProps {
  isAuthenticated: boolean;
}

// Define authenticated routes
const AuthenticatedRoutes: React.FC<RouteProps> = ({ isAuthenticated }) => {
  return (
    <HomeLayout isAuthenticated={isAuthenticated}>
      <Routes>
        <Route path="/dashboard" element={<Dashboard />} />
        <Route path="/profile" element={<Profile />} />
        <Route path="/services" element={<Services />} />
        <Route path="/service-details" element={<ServiceDetails />} />
        <Route path="*" element={<Home />} />
      </Routes>
    </HomeLayout>
  );
};

// Define unauthenticated routes
const PublicRoutes: React.FC<RouteProps> = ({ isAuthenticated }) => {
  return (
    <HomeLayout isAuthenticated={isAuthenticated}>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/signup" element={<Signup />} />
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </HomeLayout>
  );
};

const AppRoutes: React.FC = () => {
  const authenticated = isAuthenticated();

  return (
    <Router>
      {authenticated ? (
        <AuthenticatedRoutes isAuthenticated={authenticated} />
      ) : (
        <PublicRoutes isAuthenticated={authenticated} />
      )}
    </Router>
  );
};

export default AppRoutes;
