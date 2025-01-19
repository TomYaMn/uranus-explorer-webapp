import React from "react";
import HomeLayout from "../../components/HomeLayout/HomeLayout";
import styles from "./Home.module.css";
import { isAuthenticated } from "../../utils/tokenUtils";

const Home: React.FC = () => {
  const authenticated = isAuthenticated();  

  return (
    <HomeLayout isAuthenticated={authenticated}>
      <div className={styles.homeContainer}>
        <header className={styles.homeHeader}>
          <h1>Welcome to Our Platform</h1>
        </header>
        <section className={styles.homeContent}>
          <p>Explore our services and resources tailored just for you!</p>
        </section>
      </div>
    </HomeLayout>
  );
};

export default Home;
