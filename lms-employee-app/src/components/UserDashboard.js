import React,{ useState, useEffect } from 'react'
import '../style/Dashboard.css'
import {useNavigate} from 'react-router-dom';
import AuthenticationService from "../service/AuthenticationService";


const User = () => {

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
          // console.log(user.fname);
        } catch (error) {
          console.error("Error fetching Customer info:", error);
        }
      };
    const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
    const loanCards = () => {
        history('/loan');
    };
    const items = () => {
        history('/items_purchased');
    };
    const history = useNavigate();
    return(
      <>
      {isUserLoggedIn?(
        <div className='admin'>
            {/* <head er>Loan Management System</header> */}
            <h1>Hello {user.fname}&nbsp;{user.lname} </h1>

            <section className="features">
                <div className="feature">
                    <img src="../images/loans.jpg" alt="Feature 1" />
                    <h2>Loans</h2>
                    <p>Explore best available loans!!</p>
                    <button className="btn-primary" onClick={loanCards}>View Loans</button>

                </div>
                <div className="feature">
                    <img src="/images/items.jpg" alt="Feature 3" />
                    <h2>Items</h2>
                    <p>View the items purchased by you.</p>
                    <button className="btn-primary" onClick={items}>View items Purchased</button>

                </div>
                </section>
              </div>
      ):(
        <div>
        <h1>Login please</h1>
        </div>
      )
      }
    </>
    );
}

export default User;