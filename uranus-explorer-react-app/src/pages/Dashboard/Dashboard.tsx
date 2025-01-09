import React from "react";
import styles from "./Dashboard.module.css";

const Dashboard: React.FC = () => {
  return (
    <div className={styles.dashboard}>
      <h1>Welcome to the Dashboard</h1>
      <p>This is the main dashboard view for authenticated users.</p>
    </div>
  );
};

export default Dashboard;
