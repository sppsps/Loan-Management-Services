import React, { useState, useEffect } from "react";

import AuthenticationService from "../service/AuthenticationService";

import "../style/ItemsPurchased.css";

function ItemsPurchased() {

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
          const data = await AuthenticationService.getItemsPurchased();
          setViewItems(data);
        } catch (error) {
          console.error("Error fetching Items Purchased:", error);
        }
      };

      return (
        <> <br/>
        <div>
         <div className = "container">Welcome {user}</div>
          <h2 style={{color:'yellow'}}>Items Purchased</h2>
          <table className="items-purchased" cellPadding={10} cellSpacing={20}>
            <thead>
              <tr>
                <th>Issue Id</th>
                <th>Item Description</th>
                <th>Item Make</th>
                <th>Item Category</th>
                <th>Item Valuation</th>
                {/* Add more table headers as needed */}
              </tr>
            </thead>
            <tbody>
              {viewItems.map((dealer) => (
                <tr key={dealer.id}>
                  <td>{dealer.id}</td>
                  <td>{dealer.itemDescription}</td>
                  <td>{dealer.itemMake}</td>
                  <td>{dealer.itemCategory}</td>
                  <td>{dealer.itemValuation}</td>
                  {/* Add more table cells as needed */}
                </tr>
              ))}
            </tbody>
          </table>
        </div></>
      );
}

export default ItemsPurchased;