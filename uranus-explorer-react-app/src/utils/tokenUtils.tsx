import { jwtDecode } from "jwt-decode";
import { JwtPayload } from "../interfaces/JwtPayload";

export const isAuthenticated = (): boolean => {
  const token = sessionStorage.getItem("authToken");
  if (!token) return false;

  try {
    const decodedToken = jwtDecode<JwtPayload>(token);
    const currentTime = Math.floor(Date.now() / 1000); // Current time in seconds
    return decodedToken.exp > currentTime; // Token is valid if not expired
  } catch (error) {
    console.error("Token decoding failed:", error);
    return false;
  }
};

export const fetchWithAuth = async (url: string, options: RequestInit = {}) => {
  const authToken = sessionStorage.getItem("authToken"); // Retrieve token from session storage

  // Add Authorization header if authToken is present
  const headers = new Headers(options.headers || {});
  if (authToken) {
    headers.append("Authorization", `Bearer ${authToken}`);
  }

  try {
    const response = await fetch(url, {
      ...options,
      headers,
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    return await response.json();
  } catch (error) {
    console.error("Error in fetchWithAuth:", error);
    throw error; // Re-throw for further error handling
  }
};
