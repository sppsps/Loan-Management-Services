import React, { useState, useEffect } from "react";
import {useNavigate,useParams} from "react-router-dom";

import AuthenticationService from "../service/AuthenticationService";

// import "../style/ItemsPurchased.css";

function ItemsPurchased() {

  const history = useNavigate();
    // state mgmt
    const [viewItems, setViewItems] = useState([]);
    const [user,setUser] = useState('');
  
    // run each time once , when component is rendered
    useEffect(() => {
      fetchItemsPurchased();
      setUser(AuthenticationService.getLoggedInUserName());
    }, []);

    const fetchItemsPurchased = async () => {
        try {
          const emp=AuthenticationService.getLoggedInUserName();
          console.log(emp);
          // const data = await AuthenticationService.getItemsPurchased(emp);
          // setViewItems(data);
          AuthenticationService.getItemsPurchased(emp).then((response) => {
            setViewItems(response.data);
            console.log(response.data,"purch");
          });

        } catch (error) {
          console.error("Error fetching Items Purchased:", error);
        }
      };

      const backItem = () => {
        history('/user');
    };

      return (
        <> 
        <br/>

        <div className="container"> Welcome {user}</div>
        <h1 className="text-warning">Purchased Items List</h1>
        <br></br>
        <div className="row justify-content-center" >
            <table className="table table-success w-auto">
             <thead>
                <tr className="table-danger">
                    <th>Item Id</th>
                    <th> Description</th>
                    
                    <th> Make</th>
                    <th> Category</th>
                    <th> Valuation</th>
                    
                </tr>
            </thead>
            <tbody>
              {viewItems.map((dealer) => (
                <tr key={dealer[0]}>
                  <td>{dealer[0]}</td>
                  <td>{dealer[1]}</td>
                  <td>{dealer[2]}</td>
                  <td>{dealer[3]}</td>
                  <td>{dealer[4]}</td>
                  {/* Add more table cells as needed */}
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <div className = "row justify-content-center">
                        <button className="btn btn-info w-auto" onClick={backItem}>Back To User Dashboard</button>
                    </div>
        </>
      );
}

export default ItemsPurchased;