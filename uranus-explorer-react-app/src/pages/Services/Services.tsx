import React, { useEffect, useState } from "react";
import styles from "./Services.module.css";
import Button from "../../components/Button/Button";

interface Service {
    category: string;
    items: string[];
}

const Services: React.FC = () => {
    const [services, setServices] = useState<Service[]>([]);

    useEffect(() => {
        fetch(`${process.env.REACT_APP_API_URL}/services`)
            .then((response) => response.json())
            .then((data) => setServices(data))
            .catch((error) => console.error("Error fetching services:", error));
    }, []);

    return (
        <div className={styles.Services}>
            <h1 className={styles.title}>Our Services</h1>
            {services.map((service, index) => (
                <div key={index} className={styles.card}>
                    <div className={styles.serviceContent}>
                        <div className={styles.columnOne}>
                            <h2 className={styles.categoryTitle}>{service.category}</h2>
                            <Button label="Apply" />
                        </div>
                        <div className={styles.columnTwo}>
                            <ul className={styles.serviceList}>
                                {service.items.map((item, itemIndex) => (
                                    <li key={itemIndex} className={styles.serviceItem}>
                                        {item}
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

// useEffect(() => {
//   fetch(`http://localhost:8080/api/services/${category}`)
//       .then((response) => response.json())
//       .then((data: Service) => {
//           setFormFields(data.formFields);
//           setFormTitle(data.formTitle);
//       })
//       .catch((error) => console.error("Error fetching form fields:", error));
// }, [category]);