import React from 'react'
import '../style/Dashboard.css'
import {useNavigate} from 'react-router-dom';


const User = () => {
    const loanCards = () => {
        history('/applied_loan');
    };
    const items = () => {
        history('/items_purchased');
    };
    const buy_items=()=>{
        history('/apply_loan');
    }
    const history = useNavigate();

    return(
        <div className='admin'>
            {/* <header>Loan Management System</header> */}
            <h1>Hello Customer</h1>

            <section className="features">
                <div className="feature">
                    <img src="../images/loans.jpg" alt="Feature 1" />
                    <h2>Loans</h2>
                    <p>Explore best available loans!!</p>
                    <button className="btn-primary" onClick={loanCards}>View Applied Loans</button>

                </div>
                <div className="feature">
                    <img src="/images/items.jpg" alt="Feature 3" />
                    <h2>Items</h2>
                    <p>View the items purchased by you.</p>
                    <button className="btn-primary" onClick={items}>View items Purchased</button>

                </div>
                <div className="feature">
                    <img src="/images/items.jpg" alt="Feature 3" />
                    <h2>Purchase</h2>
                    <p>Apply for a loan here</p>
                    <button className="btn-primary" onClick={buy_items}>Apply for Loan</button>

                </div>
                </section>
              </div>

    );
}

export default User;