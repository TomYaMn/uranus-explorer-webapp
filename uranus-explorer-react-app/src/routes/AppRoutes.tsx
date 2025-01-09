import React from "react";
import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Home from "../pages/Home";
import Dashboard from "../pages/Dashboard";
import Profile from "../pages/Profile";
import Login from "../pages/Login";
import Signup from "../pages/Signup";

// Define authenticated, unauthenticated, and public routes
const AuthenticatedRoutes: React.FC = () => (
  <Routes>
    <Route path="/dashboard" element={<Dashboard />} />
    <Route path="/profile" element={<Profile />} />
    <Route path="*" element={<Navigate to="/" />} />  {/* Catch-all route for invalid paths */}
  </Routes>
);

const UnauthenticatedRoutes: React.FC = () => (
  <Routes>
    <Route path="/login" element={<Login />} />
    <Route path="/signup" element={<Signup />} />
    <Route path="*" element={<Navigate to="/" />} />  {/* Catch-all route for invalid paths */}
  </Routes>
);

const PublicRoutes: React.FC = () => (
  <Routes>
    <Route path="/" element={<Home />} />
  </Routes>
);

const AppRoutes: React.FC = () => {
  const isAuthenticated = true; // Replace with your authentication state
  
  return (
    <Router>
      {isAuthenticated ? <AuthenticatedRoutes /> : <UnauthenticatedRoutes />}
      <PublicRoutes />
    </Router>
  );
};

export default AppRoutes;
