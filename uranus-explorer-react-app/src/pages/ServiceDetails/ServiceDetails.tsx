import React, { useState } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Service } from '../../interfaces/Services'; // Ensure this is up-to-date with the interface changes
import Popup from '../../components/Popup/Popup'; // Adjust the import path as needed
import { renderInputField } from '../../utils/formUtils'; // Import the renderInputField utility
import styles from './ServicesDetails.module.css';

const ServiceDetails: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const service: Service | undefined = location.state as Service; // Access the service object

  const [showPopup, setShowPopup] = useState(false);
  const [popupMessage, setPopupMessage] = useState('');
  const [popupType, setPopupType] = useState<'success' | 'error'>('success');

  if (!service) {
    return <div className={styles.error}>Service data is missing or incomplete.</div>;
  }

  const handleSubmit = () => {
    setPopupMessage('Form Submitted Successfully!');
    setPopupType('success');
    setShowPopup(true);
  };

  const handleClosePopup = () => {
    setShowPopup(false);
  };

  return (
    <div className={styles.ServiceDetails}>
      <h2>{service.eServiceName}</h2> {/* Ensure category is correct from the updated interface */}

      <h3>Service Items</h3>
      <ul className={styles.serviceList}>
        {service.serviceItems.map((item, index) => ( // Ensure serviceItems exists
          <li key={index} className={styles.serviceItem}>
            {item.item} {/* Ensure item is the correct field */}
          </li>
        ))}
      </ul>

      <h3>Form Fields</h3>
      <ul className={styles.formFields}>
        {service.formFields.map((field, index) => ( // Ensure formFields exists
          <li key={index} className={styles.formField}>
            <label>{field.fieldName}</label>
            {renderInputField(field)} {/* Use the utility function here */}
          </li>
        ))}
      </ul>

      <button onClick={handleSubmit} className={styles.submitButton}>
        Submit
      </button>

      <button onClick={() => navigate('/services')} className={styles.goBack}>
        Back to Services
      </button>

      {showPopup && (
        <Popup message={popupMessage} type={popupType} onClose={handleClosePopup} />
      )}
    </div>
  );
};

export default ServiceDetails;
