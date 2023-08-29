import '../style/DropDown.css';
import React, { useState,useEffect } from 'react';
import AuthenticationService from "../service/AuthenticationService";
import { Link } from 'react-router-dom';
// import '../service/AuthenticationService';

const Dropdown = () => {
  const [user,setUser]=useState({});
  const isAdmin= AuthenticationService.isAdmin();
  
  useEffect(() => {
      fetchCustomerInfo();
      // setUser(AuthenticationService.getLoggedInUserName());
    }, []);
    const fetchCustomerInfo = async () => {
      try {
        const data = await AuthenticationService.getLoggedInUserDetails();
        // console.log("data"+data.fname);
        setUser(data);
        console.log(user);
      } catch (error) {
        console.error("Error fetching Customer info:", error);
      }
    };
  const [isOpen, setIsOpen] = useState(false);
  const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
  const handleLogout = () => {
      AuthenticationService.logout();
     
    };
  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };
  const path='/addCustomer/'+user.empId;
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
            {isAdmin?(
              <li><Link to='/admin' className='dropdown-item'>Admin Dashboard</Link></li>
            ):(
              <>
              <li><Link to='/user' className='dropdown-item'>User Dashboard</Link></li>
              <li><Link to={path} className='dropdown-item'>Update Profile</Link></li>
              </>
            )}
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
