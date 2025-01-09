import React, { useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { userLogin, userAuthError } from "../../redux/actions/authActions";
import styles from "./Login.module.css"; // Importing the module CSS

const Login: React.FC = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const dispatch = useDispatch();
  
  const authError = useSelector((state: any) => state.auth.error);

  const handleLogin = () => {
    // Simulate an API call for authentication
    if (username === "test" && password === "password") {
      dispatch(userLogin({ username: "test", token: "12345" }));
    } else {
      dispatch(userAuthError("Invalid credentials"));
    }
  };

  return (
    <div className={styles.container}>
      <h2>Login</h2>
      {authError && <p className={styles.error}>{authError}</p>}
      <input
        type="text"
        placeholder="Username"
        value={username}
        onChange={(e) => setUsername(e.target.value)}
        className={styles.inputField}
      />
      <input
        type="password"
        placeholder="Password"
        value={password}
        onChange={(e) => setPassword(e.target.value)}
        className={styles.inputField}
      />
      <button onClick={handleLogin} className={styles.button}>
        Login
      </button>
    </div>
  );
};

export default Login;
