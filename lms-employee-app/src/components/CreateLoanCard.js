import React,{useState, useEffect} from "react";
import {useNavigate,useParams} from "react-router-dom"
import LoanCardService from "../service/LoanCardService";

function CreateLoanCard(){
    const navigate = useNavigate();
    /*The useParams hook returns an object of key/value pairs of the dynamic params 
    from the current URL that were matched by the <Route path>. Child routes inherit 
    all params from their parent routes.
    */
    const { id } = useParams(); // It fetches id from URL
    // state Management
    const [loanId, setLoanId] = useState('');
    const [loanType, setType] = useState('');
    const [duration, setDuration] = useState('');
    // const [madein, setMadein] = useState('');
    // const [price, setPrice] = useState('');

     // componentDidUpdate usage
    useEffect(() => {
        if (id !== '_add') {
            LoanCardService.getLoancardById(id).then((response) => {
                const loancard = response.data;
                setLoanId(loancard.loanId);
                setType(loancard.loanType);
                setDuration(loancard.duration);
                
            });
        }
    }, [id]); // //values -id triggers re render whenever they are updated in your program,
                //you can add multiple values by separating them by commas

    const saveOrUpdateLoanCard = (event) => {
        event.preventDefault();
        const loancard = { loanId,loanType,duration };

        if (id === '_add') {
            LoanCardService.createLoancard(loancard).then(() => {
                navigate('/loan');
            });
        } else {
            LoanCardService.updateLoancard(loancard, id).then(() => {
                navigate('/loan');
            });
        }
    };

        //handlers
    const changeTypeHandler = (event) => {
        setType(event.target.value);
    };

    const changeDurationHandler = (event) => {
        setDuration(event.target.value);
    };

    
    const cancel = () => {
        navigate('/loan');
    };

    const getTitle = () => {
        if (id === '_add') {
            return <h1 className="text-center">Add Loan Card</h1>;
        } else {
            return <h1 className="text-center">Update Loan Card</h1>;
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
                                        <label> Loan Id: </label>
                                        <input placeholder="Loan Id" name="loanId" className="form-control" 
                                            value={loanId}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Type: </label>
                                        <input placeholder="Loan Type" name="loanType" className="form-control" 
                                            value={loanType} onChange={changeTypeHandler}/>
                                    </div>
                                    
                                    <div className = "form-group">
                                        <label> Duration: </label>
                                        <input placeholder="Duration" name="duration" className="form-control" 
                                            value={duration} onChange={changeDurationHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={saveOrUpdateLoanCard}>Save</button>
                                    <button className="btn btn-danger" onClick={cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

               </div>
        </div>

    );
}

export default CreateLoanCard;