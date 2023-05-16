package model;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletContext;


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