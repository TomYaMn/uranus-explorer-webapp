import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import styles from "./SideNav.module.css";
// import Button from "../Button/Button";

interface SideNavProps {
  isAuthenticated: boolean;
  className?: string;
}

const SideNav: React.FC<SideNavProps> = ({ isAuthenticated }) => {
  const navigate = useNavigate();
  const [isHovered, setIsHovered] = useState(false);

  const handleLogout = () => {
    sessionStorage.removeItem("authToken"); // Clear the token
    navigate("/"); // Redirect to the home or login page
    window.location.reload(); // Optionally reload to reset application state
  };

  const links = isAuthenticated
    ? [
        { label: "Home", path: "/" },
        { label: "Services", path: "/services" },
        { label: "Resources", path: "/resources" },
        { label: "Dashboard", path: "/dashboard" },
        { label: "Profile", path: "/profile" },
        { label: "Logout", path: "#" }, // No navigation path for logout
      ]
    : [
        { label: "Home", path: "/" },
        { label: "Services", path: "/services" },
        { label: "Resources", path: "/resources" },
        { label: "Sign Up", path: "/signup" },
      ];

  return (
    <>
      <nav
        className={`${styles.sideNav} ${isHovered ? styles.showNav : ""}`}
        onMouseEnter={() => setIsHovered(true)}
        onMouseLeave={() => setIsHovered(false)}
      >
        <ul className={styles.sideNavList}>
          {links.map((link) =>
            link.label === "Logout" ? (
              <li key={link.label} className={styles.sideNavItem}>
                <button
                  onClick={handleLogout} // Call the logout handler
                  className={styles.sideNavButton}
                >
                  {link.label}
                </button>
              </li>
            ) : (
              <li key={link.path} className={styles.sideNavItem}>
                <button
                  onClick={() => navigate(link.path)}
                  className={styles.sideNavButton}
                >
                  {link.label}
                </button>
              </li>
            )
          )}
        </ul>
      </nav>
    </>
  );
};

export default SideNav;
