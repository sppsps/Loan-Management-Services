import axios from "axios";

const Item_REST_API_URL="http://localhost:8090/paydayloans/api/item_master"


class ItemService{
    
    static getItems(){
        return axios.get(Item_REST_API_URL);
    }

    static createItem(Item){
        return axios.post(Item_REST_API_URL+'/admin',Item);
    }

    static getItemById(ItemId){
        return axios.get(Item_REST_API_URL+'/'+ItemId);
    }

    static updateItem(Item,ItemId){
        console.log("service")
        return axios.put(Item_REST_API_URL+'/'+ItemId,Item);
    }

    static deleteItem(ItemId){
        return axios.delete(Item_REST_API_URL+'/'+ItemId);
    }


    static apply(requestData)
    {
        
        return axios.post(Item_REST_API_URL,requestData);
        
    }
}


export default ItemService;