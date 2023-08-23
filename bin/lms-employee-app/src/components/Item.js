import React,{Component} from 'react';

export default class Item extends Component{
    render(){

        return(
            <div style ={{
                display:'flex',
                justifyContent:'center',
                color:'yellow',
                height:'100px'
                    }}>
                <h1> Items List</h1>
            </div>
        );
    }
}