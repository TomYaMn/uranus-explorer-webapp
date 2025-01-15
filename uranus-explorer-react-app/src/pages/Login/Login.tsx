import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { userLogin, userAuthError } from "../../redux/actions/authActions";
import axios from "axios";
import styles from "./Login.module.css"; // Importing the module CSS
import Button from "../../components/Button/Button"; // Import the Button component

const Login: React.FC = () => {
  const [emailOrUsername, setEmailOrUsername] = useState(""); // State for input
  const [password, setPassword] = useState("");
  const [isEmailLogin, setIsEmailLogin] = useState(true); // Flag to toggle between email and username login
  const dispatch = useDispatch();

  const authError = useSelector((state: any) => state.auth.error);

  const handleLogin = async () => {
    try {
      const response = await axios.post("http://localhost:8080/api/auth/login", {
        [isEmailLogin ? "email" : "userName"]: emailOrUsername, // Conditional key based on login type
        password,
      });

      // Dispatching action to save user info and token after successful login
      dispatch(userLogin({ username: response.data.username, token: response.data.token }));
      localStorage.setItem('authToken', response.data.token); // Save token to localStorage
      // const token = localStorage.getItem('authToken');

    } catch (error) {
      dispatch(userAuthError("Invalid credentials"));
    }
  };

  return (
    <div className={styles.container}>
      <h2>Login</h2>
      {authError && <p className={styles.error}>{authError}</p>}
      
      {/* Toggle button to switch between email/username */}
      <div>
        <button 
          onClick={() => setIsEmailLogin(true)} 
          className={`${styles.toggleButton} ${isEmailLogin ? styles.active : ""}`}
        >
          Use Email
        </button>
        <button 
          onClick={() => setIsEmailLogin(false)} 
          className={`${styles.toggleButton} ${!isEmailLogin ? styles.active : ""}`}
        >
          Use Username
        </button>
      </div>

      {/* Input field changes based on selected login method */}
      <input
        type="text"
        placeholder={isEmailLogin ? "Email" : "Username"} // Conditional placeholder
        value={emailOrUsername}
        onChange={(e) => setEmailOrUsername(e.target.value)}
        className={styles.inputField}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className={styles.inputField}
      />
      
      {/* Using the custom Button component */}
      <Button label="Login" onClick={handleLogin} />
    </div>
  );
};

export default Login;
