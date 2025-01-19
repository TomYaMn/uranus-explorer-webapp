import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Home from "../pages/Home";
import Dashboard from "../pages/Dashboard";
import Profile from "../pages/Profile";
import Login from "../pages/Login";
import Signup from "../pages/Signup";
import { isAuthenticated } from "../utils/tokenUtils";

// Define authenticated, unauthenticated, and public routes
const AuthenticatedRoutes: React.FC = () => (
  <Routes>
    <Route path="/dashboard" element={<Dashboard />} />
    <Route path="/profile" element={<Profile />} />
    <Route path="*" element={<Navigate to="/dashboard" />} /> {/* Redirect to Dashboard */}
  </Routes>
);

const PublicRoutes: React.FC = () => (
  <Routes>
    <Route path="/" element={<Home />} />
    <Route path="/login" element={<Login />} />
    <Route path="/signup" element={<Signup />} />
    <Route path="*" element={<Navigate to="/" />} /> {/* Redirect to Home */}
  </Routes>
);

const AppRoutes: React.FC = () => {
  const authenticated = isAuthenticated(); 
  
  return (
    <Router>
      {authenticated ? <AuthenticatedRoutes /> : <PublicRoutes />}
      
    </Router>
  );
};

export default AppRoutes;
