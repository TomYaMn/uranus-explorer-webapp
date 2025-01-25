import React, { useEffect, useState } from "react";
import styles from "./Services.module.css";
import Button from "../../components/Button/Button";
import { fetchWithAuth } from "../../utils/tokenUtils";
import { Service } from "../../interfaces/Services";

const Services: React.FC = () => {
  const [services, setServices] = useState<Service[]>([]);
  const [loading, setLoading] = useState<boolean>(true);

  useEffect(() => {
    const fetchServices = async () => {
      try {
        const data = await fetchWithAuth(`${process.env.REACT_APP_API_URL}/services`);
        console.log("Fetched data:", data); // Debugging log
        if (Array.isArray(data)) {
          setServices(data);
        } else {
          console.error("Unexpected API response format:", data);
          setServices([]);
        }
      } catch (error) {
        console.error("Error fetching services:", error);
        setServices([]);
      } finally {
        setLoading(false);
      }
    };

    fetchServices();
  }, []);

  return (
    <div className={styles.Services}>
      {services.length === 0 ? (
        <div className={styles.loading}>Loading...</div>
      ) : (
        services.length === 0 && (
          <div className={styles.noServices}>No services available</div>
        )
      )}

      {services.map((service, index) => (
        <div key={index} className={styles.card}>
          <div className={styles.serviceContent}>
            <div className={styles.columnOne}>
              <h2 className={styles.categoryTitle}>{service.category}</h2>
              <Button label="Apply" />
            </div>
            <div className={styles.columnTwo}>
              <ul className={styles.serviceList}>
                {service.serviceItems.map((item, itemIndex) => (
                  <li key={itemIndex} className={styles.serviceItem}>
                    {item.item}
                  </li>
                ))}
              </ul>
            </div>
          </div>
        </div>
      ))}
    </div>
  );
};

export default Services;
