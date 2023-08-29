import React, { Component } from 'react'

class Logout extends Component {
    
    render() {
        return (
            <>
                <br/>
                <h1 style={{color:'red'}}>You are Logged out</h1>
              
            <div>
                <img style={{
                    borderRadius:'20px',     
                }} src="/images/run.png" alt="log out" 
                height={400} width={400}></img>
                
            </div><br/>

            <div className="container">
                    Thank You for Using Our Application.
                </div>
            </>
        )
    }
}

export default Logout;