import React,{useState, useEffect} from "react";
import {useNavigate,useParams} from "react-router-dom"
import ItemService from "../service/ItemService";

function CreateItem(){
    const navigate = useNavigate();
    /*The useParams hook returns an object of key/value pairs of the dynamic params 
    from the current URL that were matched by the <Route path>. Child routes inherit 
    all params from their parent routes.
    */
    const { id } = useParams(); // It fetches id from URL
    // state Management
    const [itemId, setItemId] = useState('');
    const [itemDescription, setItemDescription] = useState('');
    const [issueStatus, setIssueStatus] = useState('');
    const [itemMake, setItemMake] = useState('');
    const [itemCategory, setItemCategory] = useState('');
    const [itemValuation, setItemValuation] = useState('');

     // componentDidUpdate usage
    useEffect(() => {
        if (id !== '_add') {
            ItemService.getItemById(id).then((response) => {
                const item = response.data;
                setItemId(item.itemId);
                setItemDescription(item.itemDescription);
                setIssueStatus(item.issueStatus);
                setItemMake(item.itemMake);
                setItemCategory(item.itemCategory);
                setItemValuation(item.itemValuation);
                
            });
        }
    }, [id]); // //values -id triggers re render whenever they are updated in your program,
                //you can add multiple values by separating them by commas

    const saveOrUpdateItem = (event) => {
        event.preventDefault();
        const item = { itemId, 
            itemDescription,
            issueStatus, 
            itemMake, 
            itemCategory,
            itemValuation };

        if (id === '_add') {
            ItemService.createItem(item).then(() => {
                navigate('/items');
            });
        } else {
            console.log("caretae");
            ItemService.updateItem(item, id).then(() => {

                navigate('/items');
            });
        }
    };

        //handlers

    const changeItemIdHandler = (event) => {
        setItemId(event.target.value);
    };


    const changeItemDescriptionHandler = (event) => {
        setItemDescription(event.target.value);
    };

    const changeIssueStatusHandler = (event) => {
        setIssueStatus(event.target.value);
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
        navigate('/items');
    };

    const getTitle = () => {
        if (id === '_add') {
            return <h1 className="text-center">Add New item</h1>;
        } else {
            return <h1 className="text-center">Update Item Details</h1>;
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
                                        <label> Issue Status: </label>
                                        <input placeholder="Issue Status" name="issueStatus" className="form-control" 
                                            value={issueStatus} onChange={changeIssueStatusHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Make: </label>
                                        <input placeholder="Make" name="itemMake" className="form-control" 
                                            value={itemMake} onChange={changeItemMakeHandler}/>
                                    </div>
                                    <div className = "form-group">
                                        <label> Category: </label>
                                        <input placeholder="category" name="itemCategory" className="form-control" 
                                            value={itemCategory} onChange={changeItemCategoryHandler}/>
                                    </div>
                                    
                                    <div className = "form-group">
                                        <label> Valuation </label>
                                        <input placeholder="valuation" name="itemValuation" className="form-control" 
                                            value={itemValuation} onChange={changeItemValuationHandler}/>
                                    </div>

                                    <button className="btn btn-success" onClick={saveOrUpdateItem}>Save</button>
                                    <button className="btn btn-danger" onClick={cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>

               </div>
        </div>

    );
}

export default CreateItem;