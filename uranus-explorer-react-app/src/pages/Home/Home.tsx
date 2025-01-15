import React from "react";
import styles from "./Home.module.css";

const Home: React.FC = () => {
  return (
    <div className={styles.container}>
      <header className={styles.header}>
        <h1>Welcome to Our Platform</h1>
        <nav className={styles.nav}>
          <a href="/signup" className={styles.link}>Sign Up</a>
          <a href="/login" className={styles.link}>Login</a>
        </nav>
      </header>
    </div>
  );
};

export default Home;
