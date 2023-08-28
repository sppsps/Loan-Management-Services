import React, { useState } from 'react';
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import * as FaIcons from 'react-icons/fa';
import * as AiIcons from 'react-icons/ai';
import { Link } from 'react-router-dom';
import '../style/NavBar.css';
import { IconContext } from 'react-icons';
import {useNavigate} from 'react-router-dom';
import AuthenticationService from "../service/AuthenticationService";
import Dropdown from './DropDown';

const NavBar = () => {
  const [sidebar, setSidebar] = useState(false);
  const LoginPage = () => {
    history('/login');
};
const history = useNavigate();
const isUserLoggedIn = AuthenticationService.isUserLoggedIn();
const handleLogout = () => {
    AuthenticationService.logout();
   
  };
  const showSidebar = () => setSidebar(!sidebar);

    return(
      <>

      <IconContext.Provider value={{ color: '#fff' }}>
      <div className='navbar'>
        <Link to='#' className='menu-bars'>
          <FaIcons.FaBars onClick={showSidebar} />
        </Link>
        <img src="../images/payday.png" className="App-logo" alt="logo" />
        <h1 class="heading">Payday Loans</h1>
        {isUserLoggedIn?(
        <div className='dropdown'><Dropdown></Dropdown></div>
        ):(
          <button onClick={LoginPage}>Sign in</button>
        )}
      </div>
      <nav className={sidebar ? 'nav-menu active' : 'nav-menu'}>
          <ul className='nav-list' onClick={showSidebar}>
          {isUserLoggedIn?(<>
            <li className='navbar-toggle'>
              <Link to='#' className='menu-bars'>
                <AiIcons.AiOutlineClose />
              </Link>
            </li>
            <li className = "nav-item">
              <Link to="/" className = "nav-link">                   
              <span><FontAwesomeIcon icon = "home"></FontAwesomeIcon></span> &nbsp; Home</Link>
            </li>
            <li><Link to="/user" className = "nav-link">
            <span><FontAwesomeIcon icon = "coffee"></FontAwesomeIcon></span> &nbsp;Dashboard</Link>                   
            </li>
                <li className="nav-item">
                    <Link to="/logout" className="nav-link" onClick={handleLogout}>
                    <span>
                        <FontAwesomeIcon icon="sign-out"></FontAwesomeIcon>
                    </span> 
                    &nbsp;Logout</Link>
                </li> 
                </>
                ):(<>

            <li className='navbar-toggle'>
              <Link to='#' className='menu-bars'>
                <AiIcons.AiOutlineClose />
              </Link>
            </li>
            <li className = "nav-item">
              <Link to="/" className = "nav-link">                   
              <span><FontAwesomeIcon icon = "home"></FontAwesomeIcon></span> &nbsp; Home</Link>
            </li>
            <li className = "nav-item">
                    <Link to="/register" className = "nav-link">
                    <span><FontAwesomeIcon icon = "coffee"></FontAwesomeIcon></span> &nbsp; Register
                    </Link>
                </li>

                <li className = "nav-item">
                    <Link to="/login" className = "nav-link">
                    <span><FontAwesomeIcon icon = "sign-in"></FontAwesomeIcon></span> &nbsp; Login

                    </Link>
                </li>

                <li className = "nav-item">
                    <Link to="/items" className = "nav-link">
                    <span><FontAwesomeIcon icon = "camera-retro"></FontAwesomeIcon></span> &nbsp; Items List
                    </Link>
                </li>
                <li className = "nav-item">
                    <Link to="/loan" className = "nav-link">
                    <span><FontAwesomeIcon icon = "bomb"></FontAwesomeIcon></span> &nbsp; Loan Cards
                    </Link>
                </li>
        
                <li className="nav-item">
                  {/* <img src="../images/payday.png"></img> */}
                    <button style={{'backgroundColor':'blue',
                    'color':'blue'}}><Link to="/login" className="nav-link">Admin</Link></button>
                </li> 
                </>
                )}
          </ul>
        </nav>
      </IconContext.Provider>
    </>
    );
}

export default NavBar;