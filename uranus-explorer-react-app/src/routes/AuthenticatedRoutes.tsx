import React from "react";
import { Routes, Route } from "react-router-dom";
import Dashboard from "../pages/Dashboard";
import Profile from "../pages/Profile";
import ProtectedRoute from "./ProtectedRoute";

// Routes for logged-in users.
const AuthenticatedRoutes: React.FC = () => {
  const isAuthenticated = true; // Replace with actual authentication logic

  return (
    <Routes>
      <Route
        path="/dashboard"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <Dashboard />
          </ProtectedRoute>
        }
      />
      <Route
        path="/profile"
        element={
          <ProtectedRoute isAuthenticated={isAuthenticated}>
            <Profile />
          </ProtectedRoute>
        }
      />
    </Routes>
  );
};

export default AuthenticatedRoutes;
