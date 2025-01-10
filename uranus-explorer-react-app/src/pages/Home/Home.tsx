import React from "react";
import styles from "./Home.module.css";

const Home: React.FC = () => {
  return (
    <div className={styles.container}>
      <div className={styles.scene}>
        <div className={styles.galaxy}>
          <img src="/images/galaxy_spiral_1.jpg" alt="Galaxy Spiral" />
        </div>
        <div className={styles.uranus}>
          <img src="/images/uranus.png" alt="Uranus" />
        </div>
      </div>
    </div>
  );
};

export default Home;
