import React, { useState } from "react";
import { useDispatch } from "react-redux";
import { userSignUp } from "../../redux/actions/authActions";
import styles from "./Signup.module.css"; // Importing the module CSS

const SignUp: React.FC = () => {
  const [username, setUsername] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();

  const handleSignUp = () => {
    // Simulate API call for user registration
    dispatch(userSignUp({ username, email, token: "newToken" }));
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
      <button onClick={handleSignUp} className={styles.button}>
        Sign Up
      </button>
    </div>
  );
};

export default SignUp;
