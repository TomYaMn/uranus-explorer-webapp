import React from "react";
import classNames from "classnames";
import styles from "./Button.module.css";

interface ButtonProps {
  label: string;
  onClick?: () => void;
  className?: string;
  color?: string; // Optional background color
  isLoading?: boolean; // Indicates if button is in loading state
  disabled?: boolean; // Optional disabled state
}

const Button: React.FC<ButtonProps> = ({ label, onClick, className, color, isLoading, disabled }) => {
  return (
    <button
      className={classNames(styles.button, className, { [styles.loading]: isLoading, [styles.disabled]: disabled })}
      onClick={onClick}
      style={{ backgroundColor: color || "var(--primary-color)" }}
      disabled={disabled || isLoading} // Disable button if loading or explicitly disabled
    >
      {isLoading ? (
        <span className={styles.spinner}></span> // Spinner when loading
      ) : (
        label
      )}
    </button>
  );
};

export default Button;
