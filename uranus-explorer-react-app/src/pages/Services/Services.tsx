import React from "react";
import styles from "./Services.module.css";
import { isAuthenticated } from "../../utils/tokenUtils";
import Button from "../../components/Button/Button";

const Services: React.FC = () => {
    const services = [
      {
        category: "Registration and Booking",
        items: [
          "Sign-Up for Exploration: Register for the Uranus exploration program.",
          "Seat Selection: Choose your preferred seat on the spacecraft.",
          "Package Customization: Tailor your exploration package based on your interests.",
          "Secure Payment Portal: Make payments securely for registrations, upgrades, and extras.",
        ],
      },
      {
        category: "Information and Education",
        items: [
          "Mission Overview: Access detailed information about the exploration mission.",
          "Virtual Uranus Tour: Explore Uranus and its moons in 3D or VR.",
          "Astronomy Learning Hub: Learn about Uranus and space exploration through videos, articles, and webinars.",
        ],
      },
      {
        category: "Pre-Departure Preparations",
        items: [
          "Health Assessment Submission: Upload medical records and receive clearance for space travel.",
          "Training Modules: Access e-learning resources to prepare for space travel.",
          "Gear and Equipment Store: Purchase exploration essentials, such as space suits and gadgets.",
        ],
      },
      {
        category: "Communication and Community",
        items: [
          "Live Chat Support: Get real-time assistance for your queries.",
          "Explorer Forums: Connect with fellow participants and share experiences.",
          "Event Notifications: Stay updated with mission schedules and announcements.",
        ],
      },
      {
        category: "Personalization Options",
        items: [
          "Mission Customization: Select your preferred exploration theme (e.g., research, adventure, luxury).",
          "Travel Journals: Document your journey with photos and stories.",
          "Custom Souvenirs: Order personalized merchandise, such as patches or certificates.",
        ],
      },
      {
        category: "On-Mission Features",
        items: [
          "Live Mission Tracking: Monitor spacecraft progress and conditions in real time.",
          "Daily Mission Updates: Receive news and milestones about the journey.",
          "Family Live Streaming: Share launch and mission updates with loved ones.",
        ],
      },
      {
        category: "Post-Mission Services",
        items: [
          "Completion Certificates: Download your official mission participation certificate.",
          "Feedback Portal: Provide feedback to help improve future missions.",
          "Alumni Network Access: Join the exclusive community of Uranus explorers for discounts and special events.",
        ],
      },
    ];
  
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
