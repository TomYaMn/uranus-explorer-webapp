import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./SideNav.module.css";
import Button from "../Button/Button";

interface SideNavProps {
  isAuthenticated: boolean;
  className?: string;
}

const SideNav: React.FC<SideNavProps> = ({ isAuthenticated }) => {
  const navigate = useNavigate();
  const [isHovered, setIsHovered] = useState(false);

  const links = isAuthenticated
    ? [
      { label: "Home", path: "/" },
      { label: "Services", path: "/services" },
      { label: "Resources", path: "/resources" },
      { label: "Dashboard", path: "/dashboard" },
      { label: "Profile", path: "/profile" },
      { label: "Logout", path: "/logout" },
    ]
    : [
      { label: "Home", path: "/" },
      { label: "Services", path: "/services" },
      { label: "Resources", path: "/resources" },
      { label: "Sign Up", path: "/signup" },
    ];

  return (
    <>

      {/* Button for visual hover */}
      {/* <Button
        label="☰"
        className={styles.visualButton}
        color="var(--neutral-dark)"
      /> */}

      {/* The SideNav now appears based on hover state */}
      <nav
        className={`${styles.sideNav} ${isHovered ? styles.showNav : ""}`}
      >
        <ul className={styles.sideNavList}>
          {links.map((link) => (
            <li key={link.path} className={styles.sideNavItem}>
              <button
                onClick={() => navigate(link.path)}
                className={styles.sideNavButton}
              >
                {link.label}
              </button>
            </li>
          ))}
        </ul>
      </nav>
    </>
  );
};

export default SideNav;
