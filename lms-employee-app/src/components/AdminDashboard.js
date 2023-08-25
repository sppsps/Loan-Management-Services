import React from 'react'
import '../style/Dashboard.css'
import {useNavigate} from 'react-router-dom';


const Admin = () => {
    const customers = () => {
        history('/customers');
    };
    const loanCards = () => {
        history('/loan');
    };
    const items = () => {
        history('/items');
    };
    const history = useNavigate();

    return(
        <div className='admin'>
            <h1>Welcome Admin</h1>
            <section className="features">
                <div className="feature">
                    <img src="../images/cus.jpg" alt="Feature 1" />
                    <h2>Customer Data</h2>
                    <p>Effortlessly manage customer details, update details, and track inventory.</p>
                    <button className="btn-primary" onClick={customers}>View Customer Info</button>

                </div>
                <div className="feature">
                    <img src="../images/loans.jpg" alt="Feature 2" />
                    <h2>Loan Cards</h2>
                    <p>Add, edit, delete Loan cards</p>
                    <button className="btn-primary" onClick={loanCards}>View Loan Cards</button>

                </div>
                <div className="feature">
                    <img src="/images/items.jpg" alt="Feature 3" />
                    <h2>Item Master</h2>
                    <p>View, edit, delete items</p>
                    <button className="btn-primary" onClick={items}>View items</button>

                </div>
                </section>
              </div>

    );
}

export default Admin;