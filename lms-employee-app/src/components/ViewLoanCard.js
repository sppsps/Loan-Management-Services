import React,{useState, useEffect} from "react";
import {useNavigate,useParams} from "react-router-dom"
import LoanCardService from "../service/LoanCardService";

const ViewLoanCard=()=>{
const history = useNavigate();

    const { id } = useParams();
    const [loancard, setLoancard] = useState({});

     // componentDidUpdate usage
    useEffect(() => {
        LoanCardService.getLoancardById(id).then((res) => {
            setLoancard(res.data);
        });
    }, [id]);  // //values -id triggers re render whenever they are updated in your program,
                //you can add multiple values by separating them by commas

    const backLoancard = () => {
        history('/loan');
    };
    return (
        <div>
            <br />
            <div className="card col-md-6 offset-md-3">
                <h3 className="text-center">View Loan Card Details</h3><hr/>
                <div className="card-body">
                    <div className="row">
                        <label>Loan Id:</label>
                        <div class="text-success fw-bolder">{loancard.loanId}</div><hr/>
                    </div>
                    <div className="row">
                        <label>Type:</label>
                        <div class="text-success fw-bolder">{loancard.loanType}</div><hr/>
                    </div>
                    <div className="row">
                        <label>Duration :</label>
                        <div class="text-success fw-bolder">{loancard.duration} Years</div><hr/>
                    </div>
                </div>
                <div className = "row justify-content-center">
                        <button className="btn btn-info w-auto" onClick={backLoancard}>Back To Loan Cards</button>
                    </div>
            </div>
        </div>
    );
    }
export default ViewLoanCard;
