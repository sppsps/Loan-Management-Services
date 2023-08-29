import React,{useState, useEffect} from "react";
import {useNavigate,useParams} from "react-router-dom"
import ItemService from "../service/ItemService";
import AuthenticationService from "../service/AuthenticationService";

function ApplyLoan(){
    const navigate = useNavigate();
    /*The useParams hook returns an object of key/value pairs of the dynamic params 
    from the current URL that were matched by the <Route path>. Child routes inherit 
    all params from their parent routes.
    */
    // const { id } = useParams(); // It fetches id from URL
    // state Management
    const [itemId, setItemId] = useState('');
    const [itemDescription, setItemDescription] = useState('');
    const [issueStatus, setIssueStatus] = useState('');
    const [itemMake, setItemMake] = useState('');
    const [itemCategory, setItemCategory] = useState('');
    const [itemValuation, setItemValuation] = useState('');

    
    const saveOrUpdateItem = (event) => {
        event.preventDefault();
        const empId=AuthenticationService.getLoggedInUserName();
        console.log(empId);
        const itemMaster ={ itemId, 
            itemDescription,
            issueStatus, 
            itemMake, 
            itemCategory,
            itemValuation };
        let loanId='';
        if(itemCategory==='Furniture')
        {
            loanId='L001';
        }
        else if(itemCategory==='Crockery')
        {
            loanId='L002';
        }
        else if(itemCategory==='Automobile')
        {
            loanId='L003';
        }
        else if(itemCategory==='Ornaments')
        {
            loanId='L004';
        }
        else{
            loanId='L005';
        }
        

        const requestData={
            empId,
            itemMaster,
            loanId
        }
        console.log("Hi");
        ItemService.apply(requestData).then((res) => {
                    console.log(res);
                    navigate('/user');
                });
        
        // if (id === '_add') {
        //     ItemService.createItem(item).then(() => {
        //         navigate('/items');
        //     });
        // } else {
        //     console.log("caretae");
        //     ItemService.updateItem(item, id).then(() => {

        //         navigate('/items');
        //     });
        // }
    };

        //handlers

    const changeItemIdHandler = (event) => {
        setItemId(event.target.value);
    };


    const changeItemDescriptionHandler = (event) => {
        setItemDescription(event.target.value);
    };

    const changeIssueStatusHandler = (event) => {
        setIssueStatus(true);
    };

    const changeItemMakeHandler = (event) => {
        setItemMake(event.target.value);
    };


    const changeItemCategoryHandler = (event) => {
        setItemCategory(event.target.value);
    };

    const changeItemValuationHandler = (event) => {
        setItemValuation(event.target.value);
    };

    
    const cancel = () => {
        navigate('/user');
    };

    

    return(

        <div>
            <br></br>
            <div className = "container">
                    <div className = "row">
                        <div className = "form-outline mb-4">
                            Apply Loan
                            <div className = "card-body">
                                <form>
                                    <div className = "form-group">
                                        <label> Item Id: </label>
                                        <input placeholder="Item Id" name="itemId" className="form-control" 
                                            value={itemId} onChange={changeItemIdHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Description: </label>
                                        <input placeholder="Item Description" name="itemDescription" className="form-control" 
                                            value={itemDescription} onChange={changeItemDescriptionHandler}/>
                                    </div>
                                    
                                    
                                    <div className = "form-group">
                                        <label> Make: </label>
                                        <input placeholder="Make" name="itemMake" className="form-control" 
                                            value={itemMake} onChange={changeItemMakeHandler}/>
                                    </div>

                                    <div className = "form-group">
                                        <label for='Category'>Select Category: </label>
                                        <select name="itemCategory" className="form-control" 
                                             onChange={changeItemCategoryHandler}>
                                            
                                             <option value="Furniture">Furniture</option>
                                             <option value="Crockery">Crockery</option>
                                             <option value="Automobile">Automobile</option>
                                             <option value="Medical">Medical</option>
                                             <option value="Ornaments">Jewellery</option>

                                             </select>
                                    </div>
                                    {
                                        // changeIssueStatusHandler()
                                    }
                                    <div className = "form-group">
                                        <label> Valuation </label>
                                        <input placeholder="valuation" name="itemValuation" className="form-control" 
                                            value={itemValuation} onChange={changeItemValuationHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={saveOrUpdateItem}>Apply</button>
                                    <button className="btn btn-danger" onClick={cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

               </div>
        </div>

    );
}

export default ApplyLoan;