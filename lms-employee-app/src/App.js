import logo from './logo.jpeg';
import './App.css';
import  {BrowserRouter as Router, Routes, Route} from 'react-router-dom';

import Home from './components/Home';
import Register from './components/Register';
import Login from './components/Login';
import Item from './components/Item';
import NavBar from './components/NavBar';
import {library} from '@fortawesome/fontawesome-svg-core';
import { faTrash, faEdit, faList, faHome,faSignIn, faSignOut, faCameraRetro, faBomb, faCoffee, faPeopleGroup} from '@fortawesome/free-solid-svg-icons';
import LoanCard from './components/LoanCard';
import Loan from './components/Loan';
import CustomerInfo from './components/Customer';
import Admin from './components/AdminDashboard';
import User from './components/UserDashboard';
import CreateLoanCard from './components/CreateLoanCard';
import ViewLoanCard from './components/ViewLoanCard';
import CreateItem from './components/CreateItem';
import ViewItem from './components/ViewItem';
import Logout from './components/Logout';
import AppliedLoans from './components/AppliedLoans';
import ApplyLoan from './components/ApplyLoan';
import Profile from './components/Profile';
import CreateCustomer from './components/createCustomer';

library.add(faTrash,faEdit,faList,faHome,faSignIn, faSignOut, faCameraRetro, faBomb, faCoffee, faPeopleGroup);



/*
	React Router is a standard library for routing in React. 
	It enables the navigation among views of various components in a React Application, allows changing the browser URL, 
	and keeps the UI in sync with the URL. 
	React Router is a JavaScript framework that lets us handle client and server-side routing in React applications. 
  It enables the creation of single-page web or mobile apps that allow navigating without refreshing the page. 
  It also allows us to use browser history features while preserving the right application view.

  Use version-6 of the Router
  > npm install react-router-dom --save
*/  
function App() {
  return (
    <div className="App">
      {/* <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <h1>Payday Loans</h1>
      </header> */}

      <section>
        <div></div>
          <div style={{ backgroundImage: "url(/images/background.png)",
                      backgroundRepeat: 'repeat-y',
                      // display:'flex',
                      backgroundSize:'cover', minHeight:'250vh',minWidth:'100vw'}}>
            <Router>
              <NavBar>

              </NavBar>

            <Routes>
              <Route path = '/' exact Component={Home}></Route>
              <Route path = 'register' Component={Register}></Route>
              <Route path = 'login' Component={Login}></Route>
              <Route path = 'items' Component={Item}></Route>
              <Route path='/loan' Component={Loan}></Route>
              <Route path = '/customers' Component={CustomerInfo}></Route>
              <Route path = '/admin' Component={Admin}></Route>
              <Route path = '/user' Component={User}></Route>
              <Route path = '/applied_loan' Component={AppliedLoans}></Route>
              <Route path = '/apply_loan' Component={ApplyLoan}></Route>
              <Route path = '/profile' Component={Profile}></Route>
              <Route path = '/logout' Component={Logout}></Route>
              {/* <Route path='/viewLoanCard/:id' element={<ViewProduct/>}></Route> */}
              <Route path='/addLoanCard/:id' element={<CreateLoanCard/>}></Route>
              <Route path='/viewLoanCard/:id' element={<ViewLoanCard/>}></Route>
              <Route path='/addCustomer/:id' element={<CreateCustomer/>}></Route>
              {/* <Route path='/addCustomer/_add' element={<CreateCustomer/>}></Route> */}

              <Route path='/addItem/:id' element={<CreateItem/>}></Route>
              <Route path='/viewitem/:id' element={<ViewItem/>}></Route>

              </Routes>
            </Router>
          </div>
        </section>

        <footer className='footer'>
          
          <p>&copy; All rights reserved to Wells Fargo</p>
        </footer>
      </div>
  );
}

export default App;