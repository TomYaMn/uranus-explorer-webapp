import React from "react";
import classNames from "classnames";
import styles from "./Button.module.css";

interface ButtonProps {
  label: string;
  onClick?: () => void;
  className?: string; // For additional styling
  color?: string; // Optional color prop for background color
}

const Button: React.FC<ButtonProps> = ({ label, onClick, className, color }) => {
  return (
    <button
      className={classNames(styles.button, className)}
      onClick={onClick}
      style={{ backgroundColor: color || "var(--primary-color)" }} // Default to primary color
    >
      {label}
    </button>
  );
};

export default Button;
