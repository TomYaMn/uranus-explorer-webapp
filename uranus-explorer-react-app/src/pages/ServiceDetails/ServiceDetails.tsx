import React from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { Service } from '../../interfaces/Services'; // Import the Service interface
import styles from './ServicesDetails.module.css';

const ServiceDetails: React.FC = () => {
  const location = useLocation();
  const navigate = useNavigate();

  const service: Service | undefined = location.state as Service;

  if (!service) {
    return <div className={styles.error}>Service data is missing or incomplete.</div>;
  }

  const renderInputField = (field: Service['formFields'][number]) => {
    switch (field.fieldType) {
      case 'text':
        return <input type="text" required={field.required} />;
      case 'email':
        return <input type="email" required={field.required} />;
      case 'tel':
        return <input type="tel" required={field.required} />;
      case 'file':
        return <input type="file" required={field.required} />;
      case 'dropdown':
        return (
          <select required={field.required}>
            <option value="">Select an option</option>
            {/* Replace with dynamic options if applicable */}
            <option value="Option 1">Option 1</option>
            <option value="Option 2">Option 2</option>
          </select>
        );
      case 'checkbox':
        return <input type="checkbox" required={field.required} />;
      default:
        return <input type="text" required={field.required} />;
    }
  };

  return (
    <div className={styles.ServiceDetails}>
      <h2>{service.category}</h2>

      <h3>Service Items</h3>
      <ul className={styles.serviceList}>
        {service.serviceItems.map((item, index) => (
          <li key={index} className={styles.serviceItem}>
            {item.item}
          </li>
        ))}
      </ul>

      <h3>Form Fields</h3>
      <ul className={styles.formFields}>
        {service.formFields.map((field, index) => (
          <li key={index} className={styles.formField}>
            <label>{field.fieldName}</label>
            {renderInputField(field)}
          </li>
        ))}
      </ul>

      <button onClick={() => navigate('/services')} className={styles.goBack}>
        Back to Services
      </button>
    </div>
  );
};

export default ServiceDetails;
