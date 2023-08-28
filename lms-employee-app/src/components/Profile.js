import React,{ useState, useEffect } from 'react';
import '../style/Profile.css';
import AuthenticationService from "../service/AuthenticationService";

const Profile = () => {
    
    const [user,setUser]=useState({});

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
  
  return (
    <div className="profile-page">
      <div className="profile-header">
        <img src='../images/user-icon.png' alt="Profile" />
        <h1>{user.fname}&nbsp;{user.lname}</h1>
        <p>Employee Id: {user.empId}</p>
        <p>Date of Joining: {user.doj}</p>
        <p>Department: {user.dept}</p>
        <p>Designation: {user.desg}</p>
        <h3>Personal Details</h3>
        <p>Date of Birth: {user.dob}</p>
        <p>Gender: {user.sex}</p>
      </div>
      {/* Other profile content */}
    </div>
  );
};

export default Profile;