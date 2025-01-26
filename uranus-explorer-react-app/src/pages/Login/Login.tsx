import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { userLogin, userAuthError } from "../../redux/actions/authActions";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import styles from "./Login.module.css";
import Button from "../../components/Button/Button";
import Popup from "../../components/Popup/Popup";

const Login: React.FC = () => {
  const [emailOrUsername, setEmailOrUsername] = useState("");
  const [password, setPassword] = useState("");
  const [isEmailLogin, setIsEmailLogin] = useState(true);
  const [showPopup, setShowPopup] = useState(false);
  const [popupMessage, setPopupMessage] = useState("");
  const [popupType, setPopupType] = useState<"success" | "error">("error");

  const dispatch = useDispatch();
  const navigate = useNavigate();
  const authError = useSelector((state: any) => state.auth.error);

  const handleLogin = async () => {
    try {
      const response = await axios.post(`${process.env.REACT_APP_API_URL}/auth/login`, {
        [isEmailLogin ? "email" : "username"]: emailOrUsername,
        password,
      });

      dispatch(userLogin({ username: response.data.username, token: response.data.token }));
      sessionStorage.setItem("authToken", response.data.token);
      setPopupMessage("Login successful!");
      setPopupType("success");
      setShowPopup(true);

      setTimeout(() => {
        navigate("/home");
        window.location.reload(); // Force full-page reload
      }, 2000); // 2 seconds delay for popup
    } catch (error) {
      dispatch(userAuthError("Invalid credentials"));
      setPopupMessage("Invalid credentials. Please try again.");
      setPopupType("error");
      setShowPopup(true);
    }
  };

  return (
    <div className={styles.container}>
      <h2>Login</h2>
      {authError && <p className={styles.error}>{authError}</p>}
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
      <input
        type="text"
        placeholder={isEmailLogin ? "Email" : "Username"}
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
      <Button label="Login" onClick={handleLogin} />
      <p className={styles.link}>
        Don't have an account yet?{" "}
        <span onClick={() => navigate("/signup")} className={styles.linkText}>
          Sign up
        </span>
      </p>
      {showPopup && (
        <Popup
          message={popupMessage}
          type={popupType}
          onClose={() => setShowPopup(false)}
        />
      )}
    </div>
  );
};

export default Login;
