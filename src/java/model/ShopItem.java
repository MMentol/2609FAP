package model;

public class ShopItem {
    double itemPrice;
    String itemName;
    String imgSource;
    String itemID;
    
    /**
     *
     * @param id
     * @param name
     * @param price
     * @param src
     */
    public ShopItem(String id, String name, double price, String src){
        this.itemID = id;
        this.itemName = name;
        this.itemPrice = price;
        this.imgSource = src;
    }
    
    // Setters
    public void setPrice(double givenPrice){
        this.itemPrice = givenPrice;
    }
    
    public void setName(String itemInfo){
        this.itemName = itemInfo;
    }
    
    public void setSource(String givenImg){
        this.imgSource = givenImg;
    }
    
    // Getters
    
    public String getPrice() {
        return Double.toString(itemPrice);
    }
    
    public String getID() {
        return itemID;
    }
    
    public String getName() {
        return itemName;
    }
    
    public String getPic() {
        return imgSource;
    }    
}