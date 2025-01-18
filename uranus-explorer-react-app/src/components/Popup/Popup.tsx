import React from "react";
import styles from "./Popup.module.css";
import Button from "../Button/Button";

interface PopupProps {
  message: string;
  type: "success" | "error"; // Popup types for styling
  onClose: () => void; // Callback to close the popup
}

const Popup: React.FC<PopupProps> = ({ message, type, onClose }) => {
  return (
    <div className={`${styles.popup} ${styles[type]}`}>
      <p>{message}</p>
      <Button label="Close" onClick={onClose} />
    </div>
  );
};

export default Popup;
