import axios from 'axios';

//make call to rest api
const API_URL='http://localhost:8090/paydayloans/api/';
export const USER_NAME_SESSION_ATTRIBUTE_NAME='authenticatedUser';

//plain java service class
class AuthenticationService {
    static setSessionAttribute(key,value)
    {
        //key- attribute (email: shanthiapapri@gmail.com)
        localStorage.setItem(key,value);
    }
    static async login(dealer) {
        try {
            const response = await axios.post('http://localhost:8090/paydayloans/api/login', dealer);
            console.log('SAPI response:', response.data +"Hello"+response.data.success); 
            if (response.data !== 2) {
                // Call the setSessionAttribute method to store the session token or user info
                this.setSessionAttribute('sessionToken', response.data.sessionToken); // Adjust as needed
                return response.data; // Return true for successful login
            } else {
                return 2; // Return false for unsuccessful login
            }
            } catch (error) {
                console.error('Login error', error);
                throw new Error('An error occurred during login.');
            }
        }
    
    
    
    
    /*
    Axios, which is a popular library is mainly used to send asynchronous 
    HTTP requests(GET,POST,PUT,DELETE) to REST endpoints. 
  This library is very useful to perform CRUD operations.
  This popular library is used to communicate with the backend. 
  Axios supports the Promise API, native to JS ES6.
  Using Axios we make API requests in our application. 
  Once the request is made we get the data in Return, and then we use this data in our React APPL. 
  
  > npm install axios
  
  */
  // Service class interacts with REST API


static async registerCustomer(dealer) {
    try {
        console.log("hi");
        const response = await axios.post('http://localhost:8090/paydayloans/api/register', dealer); // Adjust the API endpoint
        return response.data;
    } catch (error) {
        console.error('Registration error', error);
        throw new Error('An error occurred during registration.');
    }
}
// Session
  /*
 * A session is a group of user interactions with your website that take place 
  within a given time frame. 
 * For example a single session can contain multiple page views, events, 
  social interactions, and ecommerce transactions.
 * */
  static registerSuccessfulLogin(username) {   
    sessionStorage.setItem(USER_NAME_SESSION_ATTRIBUTE_NAME, username);
    // console.log("First"+username);
   
 }
 static async getCustomerInfo() {
    return axios.get('http://localhost:8090/paydayloans/api/customers')
      .then((response) => response.data)
      .catch((error) => {
        console.error("Error fetching customer info:", error);
        throw error;
      });
  }

  static async getItemsPurchased() {
    return axios.get('http://localhost:8090/paydayloans/api/items_purchased/12')
      .then((response) => response.data)
      .catch((error) => {
        console.error("Error fetching Items purchased info:", error);
        throw error;
      });
  }

 static isUserLoggedIn() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    if (user === null) return false
    return true
}
static isAdmin() {
  let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
  if (user === '000000') return 1
  return 0
}

static getLoggedInUserName() {
    let user = sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
  // console.log("Second"+user);
    if (user === null) return ''
    return user
  }

static async getLoggedInUserDetails(){
    let userId=sessionStorage.getItem(USER_NAME_SESSION_ATTRIBUTE_NAME)
    try {
        const response = await axios.get('http://localhost:8090/paydayloans/api/customers/'+userId); // Adjust the API endpoint
        // console.log(response.data);
        return response.data;
    } catch (error) {
        console.error('Error feching data', error);
        throw new Error('Error in fetching user details ');
    }
}

 static logout() {
     
    sessionStorage.removeItem("authenticatedUser");
}
  
  
}
//create a object
export default AuthenticationService;