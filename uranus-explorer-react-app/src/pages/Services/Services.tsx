import React, { useEffect, useState } from "react";
import styles from "./Services.module.css";
import Button from "../../components/Button/Button";
import { fetchWithAuth } from "../../utils/tokenUtils";
import { Service } from "../../interfaces/Services"; // Ensure this is up-to-date with the interface changes
import { useNavigate } from 'react-router-dom';
import Loading from "../../components/Loading/Loading";

const Services: React.FC = () => {
  const [services, setServices] = useState<Service[]>([]);
  const [loading, setLoading] = useState<boolean>(true);
  const navigate = useNavigate();

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

  const handleApplyClick = (service: Service) => {
    navigate('/service-details', { state: service }) // Pass service data to the ServiceDetails page
  };

  return (
    <div className={styles.Services}>
      {loading && <Loading />} {/* Show loading spinner while fetching data */}

      {services.length === 0 ? (
        <div className={styles.noServices}>No services available</div>
      ) : (
        services.map((service, index) => (
          <div key={index} className={styles.card}>
            <div className={styles.serviceContent}>
              <div className={styles.columnOne}>
                <h2 className={styles.categoryTitle}>{service.eServiceName}</h2> {/* Ensure this field exists in the updated interface */}
                <Button label="Apply"
                  onClick={() => handleApplyClick(service)}  // Pass service data to the ServiceDetails page
                />
              </div>
              <div className={styles.columnTwo}>
                <ul className={styles.serviceList}>
                  {service.serviceItems.map((item, itemIndex) => ( // Ensure serviceItems exists
                    <li key={itemIndex} className={styles.serviceItem}>
                      {item.item} {/* Ensure item is the correct field */}
                    </li>
                  ))}
                </ul>
              </div>
            </div>
          </div>
        ))
      )}
    </div>
  );
};

export default Services;
