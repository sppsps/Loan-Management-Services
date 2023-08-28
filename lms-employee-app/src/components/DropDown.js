import '../style/DropDown.css';
import React, { useState,useEffect } from 'react';
import AuthenticationService from "../service/AuthenticationService";
import { Link } from 'react-router-dom';

const Dropdown = () => {

  const [isOpen, setIsOpen] = useState(false);
  const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
  const handleLogout = () => {
      AuthenticationService.logout();
     
    };
  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="profile-dropdown">
      <div className="profile-icon" onClick={toggleDropdown}>
        {/* Replace with your profile icon */}
        <img src="../images/user-icon.png" alt="Profile Icon" />
      </div>
      {isOpen && (
        <div className="dropdown-content">
          <ul>
            <li><Link to='/profile' className='dropdown-item'>Profile</Link></li>
            <li><Link to='/addCustomer:id' className='dropdown-item'>Update Profile</Link></li>
            <li>
            <Link to="/logout" className='dropdown-item' onClick={handleLogout}>Logout</Link>
            </li>
          </ul>
        </div>
      )}
    </div>
  );
};

export default Dropdown;
