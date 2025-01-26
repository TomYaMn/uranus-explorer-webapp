import React from "react";
import styles from "./HomeLayout.module.css";
import SideNav from "../SideNav/SideNav";

interface HomeLayoutProps {
  isAuthenticated: boolean;
  children: React.ReactNode;
}

const HomeLayout: React.FC<HomeLayoutProps> = ({ isAuthenticated, children }) => {

  return (
    <div className={styles.HomeLayout}>
      <SideNav isAuthenticated={isAuthenticated}/>
      <main className={styles.mainContent}>{children}</main>
    </div>
  );
};

export default HomeLayout;
