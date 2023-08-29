import React,{useState, useEffect} from "react";
import {useNavigate} from "react-router-dom"
import ItemService from "../service/ItemService";

import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import AuthenticationService from "../service/AuthenticationService";


 /*

 Used onlyk in functional component 
    The useNavigate() hook is introduced in the React Router v6 
    to replace the useHistory() hook.
    the React Router’s new navigation API provides a useNavigate() 
    hook which is an imperative version to perform the navigation actions 
    with better compatibility. 

    The useNavigate hook lets you navigate programmatically within your React code.
*/

function Item(props){
    const history = useNavigate();

    // state Management using useState() react Hook
    const [items, setItems] = useState([]);
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
        fetchItems();
        setUser(AuthenticationService.getLoggedInUserName()
            )
    },[]);
   
    const fetchItems=()=>{
        ItemService.getItems().then((response)=>{
            setItems(response.data);
        });
    };

    const addItem = () => {
        history('/addItem/_add'); // Load Component createproduct and pass '_add' as parameter
    };



    const editItem = (id) => {
        history(`/addItem/${id}`); // use back Quote operator
    };

    const deleteItem = (id) => {
            ItemService.deleteItem(id).then(() => {
           // setProducts(products.filter(product => product.id !== id));
           fetchItems(); // Refresh products list
            setMessage('Item deleted successfully.'); 
             // Clear the message after 3 seconds
             setTimeout(() => {
                setMessage('');
            }, 2000);
        });
    };

    const viewItem = (id) => {
        history(`/viewItem/${id}`);
    };


    /*We are using the map operator to loop over our products list and create the view
    */
            return(
            <div>
                <br/>

                <div className="container"> Welcome {user}</div>
                <h1 className="text-warning">Available Items List</h1>
                <br/>
                    <div className = "row justify-content-center">
                      <button className="btn btn-info w-auto" onClick={addItem}>Add Item</button>
                    </div>
                <br/>
                <div className="row justify-content-center" >
                    <table className="table table-success w-auto">
                     <thead>
                        <tr className="table-danger">
                            <th>Item Id</th>
                            <th> Description</th>
                            
                            <th> Make</th>
                            <th> Category</th>
                            <th> Valuation</th>
                            <th> Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                            {items.map(
                                    item => 
                                    <tr key={item.id}>
                                        <td> {item.itemId} </td>
                                        <td> {item.itemDescription} </td>
                                        
                                        <td> {item.itemMake} </td>
                                        <td> {item.itemCategory} </td>
                                        <td> {item.itemValuation} </td>
                                        <td>
                                        <button className="btn btn-success" onClick={() => editItem(item.id)}>
                                                <span>
                                                  <FontAwesomeIcon icon="edit"></FontAwesomeIcon>
                                                </span> 
                                           </button>
                                           &nbsp;
                                           <button className="btn btn-danger" onClick={() => deleteItem(item.id)}>
                                                <span>
                                                  <FontAwesomeIcon icon="trash"></FontAwesomeIcon>
                                                </span> 
                                          </button>
                                          &nbsp;
                                          <button className="btn btn-primary" onClick={() => viewItem(item.id)}>
                                               <span>
                                                  <FontAwesomeIcon icon="list"></FontAwesomeIcon>
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


export default Item;