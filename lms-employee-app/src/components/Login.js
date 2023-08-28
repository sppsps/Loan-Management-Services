import React,{useState} from "react";
import {useNavigate} from "react-router-dom"
import AuthenticationService from "../service/AuthenticationService";
import '../style/Login.css';


const Login = () => {
    const history = useNavigate();  // Object to navigate 
  
    ///defining a state
    const [empId, setempId] = useState('');
    const [password, setPassword] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    const [successMessage, setSuccessMessage] = useState('');
  
    const handleLogin = async () => {
      if (!empId || !password) {
        setErrorMessage('Please enter both empId and password.');
        return;
      }
  
      const dealer = {
        empId,
        password
      };
  
    /*await is usually used to unwrap promises by passing a Promise as the expression. 
  Using await pauses the execution of its surrounding async function until the promise is 
  settled (that is, fulfilled or rejected).
  When execution resumes, the value of the await expression becomes that of the 
  fulfilled promise.
  
  The Promise object represents the eventual completion (or failure) of an asynchronous 
  operation and its resulting value
     */
  try {
      const loginSuccess = await AuthenticationService.login(dealer);
      console.log('API response:', loginSuccess.data); // Add this line
      console.log(loginSuccess);
      AuthenticationService.registerSuccessfulLogin(empId);
      if (loginSuccess!=2) {
        setSuccessMessage('Login successful. Redirecting...');
        setTimeout(() => {
          if (loginSuccess==1){
          history('/user'); // navigates to product Component
          }
          else{
            history('/admin')
          }
        }, 2000);
      } else {
        setErrorMessage('Invalid empId or password.');
      }
    } catch (error) {
      console.error('Login error', error);
      setErrorMessage('An error occurred during login.');
    }
    
    };
  
    return (
      <div className="container">
          <br></br>
      <h2>Login</h2>
        <div className="form-group">
          <label>Employee Id:</label>
          <input type="text" className="form-control" value={empId} onChange={(e) => setempId(e.target.value)} />
        </div>
        <div className="form-group">
          <label>Password:</label>
          <input type="password" className="form-control" value={password} onChange={(e) => setPassword(e.target.value)} />
        </div>
        <button className="btn btn-primary" onClick={handleLogin}>Login</button>
        {errorMessage && <p className="error-message">{errorMessage}</p>}
        {successMessage && <p className="success-message">{successMessage}</p>}
     
      </div>
    );
  };
  
  export default Login;