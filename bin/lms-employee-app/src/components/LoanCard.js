import React from 'react';

const LoanCard = ({type, duration }) => {
  return (
    <div className="card" style={{margin:"10px"}}>
      
      <img src={'../images/pms1.jpg'} className="card-img-top" alt={"image"} />
      <div className="card-body">
        <h5 className="card-title">{type}</h5>
        <p className="card-text">${duration} Years</p>
        <a href="#" className="btn btn-primary">
          Get Now
        </a>
      </div>
    </div>
  );
};

export default LoanCard;