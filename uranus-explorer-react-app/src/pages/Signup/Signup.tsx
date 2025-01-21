import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { userSignUp } from "../../redux/actions/authActions";
import Popup from "../../components/Popup/Popup";
import Button from "../../components/Button/Button";
import styles from "./Signup.module.css";

const SignUp: React.FC = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [popupMessage, setPopupMessage] = useState("");
  const [popupType, setPopupType] = useState<"success" | "error" | null>(null);
  const [isLoading, setIsLoading] = useState(false); // To handle loading state
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSignUp = async () => {
    if (!username || !email || !password) {
      setPopupMessage("All fields are required.");
      setPopupType("error");
      return;
    }
    
    setIsLoading(true); // Start loading
    try {
      const response = await fetch(`${process.env.REACT_APP_API_URL}/auth/signup`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ username, email, password }),
      });

      if (!response.ok) {
        const errorData = await response.json();
        throw new Error(errorData.message || "Failed to create account.");
      }

      const data = await response.json();
      dispatch(userSignUp({ username: data.username, email: data.email, token: data.token }));
      setPopupMessage("Account created successfully!");
      setPopupType("success");

      setTimeout(() => {
        navigate("/login");
      }, 2000);
    } catch (error: any) {
      setPopupMessage(error.message || "There was a problem creating your account. Please try again.");
      setPopupType("error");
    } finally {
      setIsLoading(false); // Stop loading
    }
  };

  return (
    <div className={styles.container}>
      <h2>Sign Up</h2>
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        className={styles.inputField}
      />
      <input
        type="email"
        placeholder="Email"
        value={email}
        onChange={(e) => setEmail(e.target.value)}
        className={styles.inputField}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className={styles.inputField}
      />
      <Button label={isLoading ? "Signing Up..." : "Sign Up"} onClick={handleSignUp} disabled={isLoading} />

      {popupType && (
        <Popup
          message={popupMessage}
          type={popupType}
          onClose={() => setPopupType(null)}
        />
      )}

      <p className={styles.link}>
        Already have an account?{" "}
        <span onClick={() => navigate("/login")} className={styles.linkText}>
          Login
        </span>
      </p>
    </div>
  );
};

export default SignUp;
