import React from "react";
import { useDispatch, useSelector } from "react-redux";
import { userLogout } from "../../redux/actions/authActions";
import styles from "./Profile.module.css"; // Importing the module CSS

const Profile: React.FC = () => {
  const dispatch = useDispatch();
  const user = useSelector((state: any) => state.auth.user);

  const handleLogout = () => {
    dispatch(userLogout());
  };

  return (
    <div className={styles.profileContainer}>
      <h2>Profile</h2>
      {user ? (
        <div>
          <p className={styles.greeting}>Welcome, {user.username}!</p>
          <button onClick={handleLogout} className={styles.logoutButton}>
            Logout
          </button>
        </div>
      ) : (
        <p>Please log in first.</p>
      )}
    </div>
  );
};

export default Profile;
