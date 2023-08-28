import React from 'react'
import '../style/Home.css'
import { Link } from 'react-router-dom';


const Home = () => {
    return(
        <div style ={{
                
                // justifyContent:'center',
                color:'black',
                height:'100px',
                marginTop:'25px',
                marginLeft:'100px',
                marginRight:'100px',
                    }}>
            <h2 className='Heading'>
                    
                    Loan Management System (LMS) : Empowering Employee Financial Support 
            </h2>
            {/* <h2 className='Heading'>
                    Empowering Employee Financial Support */}
            {/* </h2> */}
            <div style =  {{alignItems:'left',marginLeft:0,marginRight:'30vh',marginBottom:'10vh',marginTop:'10vh'}}>
                <p className='Paragraph'>
                    The Loan Management System (LMS) is a robust application designed to facilitate efficient loan provisioning for employees within our organization. In the realm of finance, a loan signifies the transfer of funds from one party to another, encompassing an agreement for repayment. The recipient, or borrower, assumes a financial obligation, often accompanied by interest charges to compensate for fund usage.
                </p>
            </div>
                
            <div style={{alignItems:'right',marginLeft:'30vh',marginRight:0,marginBottom:'10vh'}}>
                <p className='Paragraph'>
                    The documentation substantiating this debt, such as a promissory note, outlines pivotal details, including the principal borrowed amount, the lender's applicable interest rate, and the stipulated repayment date. A loan entails the temporary reassignment of the involved asset(s) between the lender and borrower.

                </p>
            </div>
                


            <div style =  {{alignItems:'left',marginLeft:0,marginRight:'30vh',marginBottom:'10vh'}}>    
                <p className='Paragraph'>
                    Interest serves as an incentivizing factor for lenders to participate in loan transactions. Legal loans are governed by contractual obligations and constraints, which may impose supplementary restrictions referred to as loan covenants. Notably, this discourse primarily addresses financial loans, although, in practicality, virtually any tangible item could potentially be lent.
                </p>
            </div>
            <div style={{alignItems:'right',marginLeft:'30vh',marginRight:0,marginBottom:'10vh'}}>    
                <p className='Paragraph'>
                    Acting as loan providers constitutes a central function of financial entities, notably including banks and credit card companies. For diverse institutions, the issuance of debt instruments like bonds represents a conventional avenue for securing financial resources.
                </p>
            </div>
        </div>
    );
}

export default Home;