import React, { useState } from 'react';
import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { Link } from 'react-router-dom';
import '../style/NavBar.css';
import { IconContext } from 'react-icons';
import logo from '../logo.jpeg';

const NavBar = () => {
  const [sidebar, setSidebar] = useState(false);

  const showSidebar = () => setSidebar(!sidebar);

    return(
      <>
      <IconContext.Provider value={{ color: '#fff' }}>
      <div className='navbar'>
        <Link to='#' className='menu-bars'>
          <FaIcons.FaBars onClick={showSidebar} />
        </Link>
        <img src={logo} className="App-logo" alt="logo" />
        <h1 class="heading">Loan Management System</h1>
      </div>
      <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
          <ul className='nav-list' onClick={showSidebar}>
            <li className='navbar-toggle'>
              <Link to='#' className='menu-bars'>
                <AiIcons.AiOutlineClose />
              </Link>
            </li>
            <li className = "nav-item">
              <Link to="/" className = "nav-link">Home</Link>
            </li>
            <li className = "nav-item">
                    <Link to="/register" className = "nav-link">Register</Link>
                </li>

                <li className = "nav-item">
                    <Link to="/login" className = "nav-link">Login</Link>
                </li>

                <li className = "nav-item">
                    <Link to="/items" className = "nav-link">Item</Link>
                </li>
                <li className="nav-item">
                    <Link to="/customers" className="nav-link">Customers Info</Link>
                </li> 
          </ul>
        </nav>
      </IconContext.Provider>
    </>
    );
}

export default NavBar;