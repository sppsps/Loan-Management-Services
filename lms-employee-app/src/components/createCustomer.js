import React,{useState, useEffect} from "react";
import {useNavigate,useParams} from "react-router-dom"
import CustomerService from "../service/CustomerService";

function CreateCustomer(){
    const navigate = useNavigate();
    /*The useParams hook returns an object of key/value pairs of the dynamic params 
    from the current URL that were matched by the <Route path>. Child routes inherit 
    all params from their parent routes.
    */
    const { id } = useParams(); // It fetches id from URL
    // state Management
    const [empId, setCustomerEmpId] = useState('');
    const [fname, setCustomerFname] = useState('');
    const [lname, setCustomerLname] = useState('');
    const [dept, setCustomerDepartment] = useState('');
    const [desg, setCustomerDesignation] = useState('');
    const [sex, setCustomerGender] = useState('');
    const [dob, setCustomerDob] = useState('');
    const [doj, setCustomerDoj] = useState('');

     // componentDidUpdate usage
    useEffect(() => {
        if (id !== '_add') {
            CustomerService.getCustomerById(id).then((response) => {
                const Customer = response.data;
                setCustomerEmpId(Customer.empId);
                setCustomerFname(Customer.fname);
                setCustomerLname(Customer.lname);
                setCustomerGender(Customer.sex);
                setCustomerDepartment(Customer.dept);
                setCustomerDesignation(Customer.desg);
                setCustomerDob(Customer.dob);
                setCustomerDoj(Customer.doj);
            });
        }
    }, [id]); // //values -id triggers re render whenever they are updated in your program,
                //you can add multiple values by separating them by commas

    const saveOrUpdateCustomer = (event) => {
        event.preventDefault();
        const Customer = { empId, 
            fname,
            lname, 
            sex, 
            dept,
            desg,
            dob,
            doj };

        if (id === '_add') {
            CustomerService.createCustomer(Customer).then(() => {
                navigate('/customers');
            });
        } else {
            // console.log("caretae");
            CustomerService.updateCustomer(Customer, id).then(() => {
                if (Customer.empId==='000000'){
                navigate('/customers');
                }
                else{
                    navigate('/profile')
                }
            });
        }
    };


    const changeCustomerIdHandler = (event) => {
        setCustomerEmpId(event.target.value);
    };


    const changeCustomerFnameHandler = (event) => {
        setCustomerFname(event.target.value);
    };

    const changeCustomerLnameHandler = (event) => {
        setCustomerLname(event.target.value);
    };

    const changeCustomerGenderHandler = (event) => {
        setCustomerGender(event.target.value);
    };


    const changeCustomerDepartmentHandler = (event) => {
        setCustomerDepartment(event.target.value);
    };

    const changeCustomerDesignationHandler = (event) => {
        setCustomerDesignation(event.target.value);
    };

    const changeCustomerDobHandler = (event) => {
        setCustomerDob(event.target.value);
    };

    const changeCustomerDojHandler = (event) => {
        setCustomerDoj(event.target.value);
    };
    
    const cancel = () => {
        navigate('/customers');
    };

    const getTitle = () => {
        if (id === '_add') {
            return <h1 className="text-center">Add New Customer</h1>;
        } else {
            return <h1 className="text-center">Update Customer Details</h1>;
        }
    };

    return(

        <div>
            <br></br>
            <div className = "container">
                    <div className = "row">
                        <div className = "form-outline mb-4">
                            {getTitle()}
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Customer Id: </label>
                                        <input placeholder="Customer Id" name="empId" className="form-control" 
                                            value={empId} onChange={changeCustomerIdHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> First Name: </label>
                                        <input placeholder="Customer First name" name="fname" className="form-control" 
                                            value={fname} onChange={changeCustomerFnameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Last Name: </label>
                                        <input placeholder="Customer Last name" name="lnamee" className="form-control" 
                                            value={lname} onChange={changeCustomerLnameHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Gender: </label>
                                        <input placeholder="Customer Gender" name="sex" className="form-control" 
                                            value={sex} onChange={changeCustomerGenderHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Department: </label>
                                        <input placeholder="Customer Department" name="dept" className="form-control" 
                                            value={dept} onChange={changeCustomerDepartmentHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Designation: </label>
                                        <input placeholder="Customer Designation" name="desg" className="form-control" 
                                            value={desg} onChange={changeCustomerDesignationHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Dob: </label>
                                        <input placeholder="Customer Dob" name="dob" className="form-control" 
                                            value={dob} onChange={changeCustomerDobHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Doj: </label>
                                        <input placeholder="Customer Doj" name="doj" className="form-control" 
                                            value={doj} onChange={changeCustomerDojHandler}/>
                                    </div>
                                    <button className="btn btn-success" onClick={saveOrUpdateCustomer}>Save</button>
                                    <button className="btn btn-danger" onClick={cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

               </div>
        </div>

    );
}

export default CreateCustomer;