
import React,{useState} from "react";
import {useNavigate} from "react-router-dom"
import AuthenticationService from "../service/AuthenticationService";
import '../style/Register.css';

const Register = () => {
  const history = useNavigate();
  const [dealer, setDealer] = useState({
    empId: '',
    fname: '',
    lname: '',
    dob: '',
    dept:'',
    desg: '',
    doj:'',
    sex:'',
    password: '',
    
  });

  const [errors, setErrors] = useState({});
  const [successMessage, setSuccessMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (name.includes('.')) {
      const [parent, child] = name.split('.');
      setDealer((prevDealer) => ({
        ...prevDealer,
        [parent]: {
          ...prevDealer[parent],
          [child]: value
        }
      }));
    } else {
      setDealer((prevDealer) => ({
        ...prevDealer,
        [name]: value
      }));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    const validationErrors = validateForm();
    if (Object.keys(validationErrors).length === 0) {
      try {
        await AuthenticationService.registerCustomer(dealer);
        setSuccessMessage('Registration successful!');
        // Clear form or navigate to another page

        setTimeout(() =>{
            history('/login');
        },3000);

      } catch (error) {
        console.error('Registration error', error);
        setSuccessMessage('An error occurred during registration.');
      }
    } else {
      setErrors(validationErrors);
    }
  };

  const validateForm = () => {
    let validationErrors = {};

    

    if (!dealer.fname) {
        validationErrors.fname = 'First name is required.';
      }
      else if (!/^[a-zA-Z]*$/.test(dealer.fname)) {
        validationErrors.fname = 'Enter Alphabets Only';
      }
      
    if (!dealer.lname) {
    validationErrors.lname = 'Last name is required.';
    }
    else if (!/^[a-zA-Z]*$/.test(dealer.lname)) {
      validationErrors.lname = 'Enter Alphabets Only';
    }


    // Add more validation rules for other fields
    // Password must be at least 8 characters
    
  if (dealer.password.length < 8) {
    validationErrors.password = "Password must be atleast 8 characters";
  }

  // Password must contain at least one numeric character
  if (!/\d/.test(dealer.password)) {
    validationErrors.password = "Password must contain at least one numeric character";
  }

  // Password must contain at least one special character
  if (!/[!@#$%^&*()_+{}\[\]:;<>,.?~\\/-]/.test(dealer.password)) {
    validationErrors.password = "Password must contain at least one special character";
  }

  // Password must start with a capital letter
  if (!/^[A-Z]/.test(dealer.password)) {
    validationErrors.password = "Password must start with a capital letter";
  }
  
  
      

    return validationErrors;
  };

  return (
    <div className="registration-container">
      <h2>User Registration</h2>
      {successMessage && <p className="success-message">{successMessage}</p>}
      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Employee ID:</label>
          <input
            type="text"
            name="empId"
            value={dealer.empId}
            onChange={handleChange}
            className={errors.empId && 'error'}
          />
          {errors.empId && <p className="error-message">{errors.empId}</p>}
        </div>
        <div className="form-group">
          <label>First Name:</label>
          <input
            type="text"
            name="fname"
            value={dealer.fname}
            onChange={handleChange}
            className={errors.fname && 'error'}
          />
          {errors.fname && <p className="error-message">{errors.fname}</p>}
        </div>

        <div className="form-group">
          <label>Last Name:</label>
          <input
            type="text"
            name="lname"
            value={dealer.lname}
            onChange={handleChange}
            className={errors.lname && 'error'}
          />
          {errors.lname && <p className="error-message">{errors.lname}</p>}
        </div>

        <div className="form-group">
          <label>Password:</label>
          <input
            type="password"
            name="password"
            value={dealer.password}
            onChange={handleChange}
            className={errors.password && 'error'}
          />
          {errors.password && <p className="error-message">{errors.password}</p>}
        </div>

        <div className="form-group">
          <label>Date of Birth:</label>
          <input
          type="date"
          name="dob"
          value={dealer.dob}
          onChange={handleChange}
          className={errors.dob && 'error'}
        />
        {errors.dob && <p className="error-message">{errors.dob}</p>}
      </div>

      <div className="form-group">
          <label>Date of Joining:</label>
          <input
          type="date"
          name="doj"
          value={dealer.doj}
          onChange={handleChange}
          className={errors.doj && 'error'}
        />
        {errors.doj && <p className="error-message">{errors.doj}</p>}
      </div>

      <div className="form-group">
          <label>Department:</label>
          <input
            type="text"
            name="dept"
            value={dealer.dept}
            onChange={handleChange}
            className={errors.dept && 'error'}
          />
          {errors.dept && <p className="error-message">{errors.dept}</p>}
        </div>

        <div className="form-group">
          <label>Designation:</label>
          <input
            type="text"
            name="desg"
            value={dealer.desg}
            onChange={handleChange}
            className={errors.desg && 'error'}
          />
          {errors.desg && <p className="error-message">{errors.desg}</p>}
        </div>

        <div className="form-group">
          <label>Gender:</label>
          <select id="sex" name="sex" value={dealer.sex}
            onChange={handleChange} className={errors.sex && 'error'}>
            <option value="F">F</option>
            <option value="M">M</option>
          </select>
          {/* <input
            type="text"
            name="sex"
            value={dealer.sex}
            onChange={handleChange}
            className={errors.sex && 'error'}
          /> */}
          {errors.sex && <p className="error-message">{errors.sex}</p>}
        </div>

        {/* Add more form fields with similar structure */}
        
        
       
        <div className="form-group">
          <button type="submit" className="submit-button">
            Register
          </button>
        </div>
      </form>
    </div>
  );
};

export default Register;
