import { jwtDecode } from "jwt-decode";
import { JwtPayload } from "../interfaces/JwtPayload";

export const isAuthenticated = (): boolean => {
  const token = localStorage.getItem("token");
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
