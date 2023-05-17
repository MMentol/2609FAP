package model;

import java.sql.ResultSet;
import java.util.HashMap;

public class ShopInitializer {
    
    public HashMap<String,ShopItem> initStock(ResultSet rs){
        HashMap<String,ShopItem> stonk = new HashMap<>();
        
        try{
            //Loops until all items have been loaded.
            while(rs.next()){
                stonk.put(rs.getString("STOCK_ID"), new ShopItem(
                        rs.getString("STOCK_ID"),
                        rs.getString("STOCK_NAME"),
                        Double.parseDouble(rs.getString("STOCK_PRICE")),
                        rs.getString("STOCK_IMG")));
            }
        }
        catch (Exception e){
            System.out.println("Result Set does not exist!");
        }
        return stonk;
    }
}