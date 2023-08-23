import React, { useState, useEffect } from "react";

import AuthenticationService from "../service/AuthenticationService";
import "../style/Customer.css";

function CustomerInfo() {

    // state mgmt
    const [CustomerInfo, setCustomerInfo] = useState([]);
    const [user,setUser]=useState('');
  
    // run each time once , when component is rendered
    useEffect(() => {
      fetchCustomerInfo();
      setUser(AuthenticationService.getLoggedInUserName());
            

    }, []);

    const fetchCustomerInfo = async () => {
        try {
          const data = await AuthenticationService.getCustomerInfo();
          setCustomerInfo(data);
        } catch (error) {
          console.error("Error fetching Customer info:", error);
        }
      };

      return (
        <> <br/>
        <div>
        <div className="container"> Welcome {user}</div>
                
          <h2 style={{color:'black'}}>Customers Information</h2>
          <table className="Customer-table" cellPadding={10} cellSpacing={20}>
            <thead>
              <tr>
                <th>Customer Id</th>
                <th>Full Name</th>
                <th>Gender</th>
                <th>Designation</th>
                <th>Department</th>
                <th>dob</th>
                <th>doj</th>
                {/* Add more table headers as needed */}
              </tr>
            </thead>
            <tbody>
              {CustomerInfo.map((Customer) => (
                <tr key={Customer.id}>
                  <td>{Customer.empId}</td>
                  <td>{Customer.fname} &nbsp; {Customer.lname}</td>
                  <td>{Customer.sex}</td>
                  <td>{Customer.desg}</td>
                  <td>{Customer.dept}</td>
                  <td>{Customer.dob}</td>
                  <td>{Customer.doj}</td>
                  {/* Add more table cells as needed */}
                </tr>
              ))}
            </tbody>
          </table>
        </div></>
      );
}

export default CustomerInfo;