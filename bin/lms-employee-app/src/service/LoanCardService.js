import axios from "axios";

const LOANCARD_REST_API_URL="http://localhost:8090/paydayloans/api/loan_card"


class LoanCardService{
    
    static getLoancards(){
        return axios.get(LOANCARD_REST_API_URL);
    }

    static createLoancard(Loancard){
        return axios.post(LOANCARD_REST_API_URL,Loancard);
    }

    static getLoancardById(LoancardId){
        return axios.get(LOANCARD_REST_API_URL+'/'+LoancardId);
    }

    static updateLoancard(Loancard,LoancardId){
        return axios.put(LOANCARD_REST_API_URL+'/'+LoancardId,Loancard);
    }

    static deleteLoancard(LoancardId){
        return axios.delete(LOANCARD_REST_API_URL+'/'+LoancardId);
    }
}


export default LoanCardService;