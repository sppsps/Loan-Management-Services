import React,{useState, useEffect} from "react";
import {useNavigate} from "react-router-dom"
import CustomerService from "../service/CustomerService";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import AuthenticationService from "../service/AuthenticationService";


function CustomerInfo(props){
    const history = useNavigate();

    // state Management using useState() react Hook
    const [Customers, setCustomers] = useState([]);
    const [message, setMessage] = useState('');
    const [user,setUser]=useState('');

    /*
    The useEffect hook in React is use to handle the side effects in React such as 
    fetching data, and updating DOM. This hook runs on every render but there is 
    also a way of using a dependency array using which we can control the effect of 
    rendering.

    The motivation behind the introduction of useEffect Hook is to eliminate the 
    side effects of using class-based components.

    Syntax: useEffect(<FUNCTION>, <DEPENDECY>)
     - To run useEffect on every render do not pass any dependency
     - To run useEffect only once on the first render pass any empty array in the dependecy
     - To run useEffect on change of a particular value. Pass the state and props in the dependency array
     */
    // handling componentDidMount()
    useEffect(()=>{  //react hook - runs only on firts render empty array
        fetchCustomers();
        setUser(AuthenticationService.getLoggedInUserName()
            )
    },[]);
   
    const fetchCustomers=()=>{
        CustomerService.getCustomers().then((response)=>{
            setCustomers(response.data);
        });
    };

    const addCustomer = () => {
        history('/addCustomer/_add'); // Load Component createproduct and pass '_add' as parameter
    };



    const editCustomer = (id) => {
        history(`/addCustomer/${id}`); // use back Quote operator
    };

    const deleteCustomer = (id) => {
            CustomerService.deleteCustomer(id).then(() => {
           // setProducts(products.filter(product => product.id !== id));
           fetchCustomers(); // Refresh products list
            setMessage('Customer deleted successfully.'); 
             // Clear the message after 3 seconds
             setTimeout(() => {
                setMessage('');
            }, 2000);
        });
    };




    /*We are using the map operator to loop over our products list and create the view
    */
            return(
            <div>
                <br/>

                <div className="container"> Welcome Admin</div>
                <h1 className="text-warning">Available Customers List</h1>
                <br/>
                    <div className = "row justify-content-center">
                      <button className="btn btn-info w-auto" onClick={addCustomer}>Add Customer</button>
                    </div>
                <br/>
                <div className="row justify-content-center" >
                    <table className="table table-success w-auto">
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
                {Customers.map(Customer => 
                <tr key={Customer.id}>
                  <td>{Customer.empId}</td>
                  <td>{Customer.fname} &nbsp; {Customer.lname}</td>
                  <td>{Customer.sex}</td>
                  <td>{Customer.desg}</td>
                  <td>{Customer.dept}</td>
                  <td>{Customer.dob}</td>
                  <td>{Customer.doj}</td>
                  <td>
          <button className="btn btn-success" onClick={() => editCustomer(Customer.empId)}>
                        <span>
                          <FontAwesomeIcon icon="edit"></FontAwesomeIcon>
                        </span> 
                    </button>
                    &nbsp;
                    <button className="btn btn-danger" onClick={() => deleteCustomer(Customer.id)}>
                        <span>
                          <FontAwesomeIcon icon="trash"></FontAwesomeIcon>
                        </span> 
                  </button>
                </td> 
            </tr>
        )
}
                    </tbody>
                    </table>
                </div>
                {message && <div className="alert alert-success">{message}</div>}
            </div>

            
        );
}


export default CustomerInfo;