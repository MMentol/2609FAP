package model;

import java.sql.ResultSet;
import java.util.ArrayList;


public class ShopInitializer {
    public ArrayList<ShopItem> initStock(ResultSet rs){
        ArrayList<ShopItem> stonk = new ArrayList();
        
        try{
            //Loops until all items have been loaded.
            while(rs.next()){
            stonk.add(new ShopItem(
                    rs.getString("STOCK_ID"),
                    rs.getString("STOCK_NAME"),
                    Double.parseDouble(rs.getString("STOCK_PRICE")),
                    rs.getString("STOCK_IMG")));
            }
        }
        catch (Exception e){
            System.out.println("Result Set does not exist");
        }
        return stonk;
    }
}