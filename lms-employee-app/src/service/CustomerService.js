import axios from "axios";

const Customer_REST_API_URL="http://localhost:8090/paydayloans/api/customers"


class CustomerService{
    
    static getCustomers(){
        return axios.get(Customer_REST_API_URL);
    }

    static createCustomer(Customer){
        return axios.post('http://localhost:8090/paydayloans/api/register',Customer);
    }

    static getCustomerById(CustomerId){
        return axios.get(Customer_REST_API_URL+'/'+CustomerId);
    }

    static updateCustomer(Customer,CustomerId){
        console.log("service")
        return axios.put(Customer_REST_API_URL+'/'+CustomerId,Customer);
    }

    static deleteCustomer(CustomerId){
        return axios.delete(Customer_REST_API_URL+'/'+CustomerId);
    }
}


export default CustomerService;