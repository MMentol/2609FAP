import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {
   static final String DB_URL = "jdbc:mysql://localhost/";
   static final String USER = "guest";
   static final String PASS = "guest123";

   public static void main(String[] args) {
      // Open a connection
      try(Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();
      ) {		      
         String sql = "CREATE DATABASE iceCoolDB;
USE iceCoolDB;

CREATE TABLE USERS (
USER_ID VARCHAR(10) NOT NULL,
USER_NAME VARCHAR(100) UNIQUE NOT NULL,
USER_EMAIL VARCHAR(100) UNIQUE NOT NULL,
USER_PASS VARCHAR(100) NOT NULL,
USER_ADDRESS VARCHAR(100) NOT NULL,
PRIMARY KEY(USER_ID)
);

CREATE TABLE STOCK(
STOCK_ID VARCHAR(10) NOT NULL,
STOCK_NAME VARCHAR(255) NOT NULL,
STOCK_PRICE DOUBLE(10,2) NOT NULL,
STOCK_IMG VARCHAR(255),
PRIMARY KEY(STOCK_ID)
);

CREATE TABLE ORDERS(
ORDER_ID INT(10) NOT NULL AUTO_INCREMENT,
STOCK_ID VARCHAR(10) NOT NULL,
ORDER_PAYMENT VARCHAR(10) NOT NULL DEFAULT 'cod', 
ORDER_INSTALL bool NOT NULL DEFAULT '0',
USER_ID VARCHAR(10) NOT NULL,
PRIMARY KEY(ORDER_ID),
CONSTRAINT FOREIGN KEY (STOCK_ID) REFERENCES STOCK(STOCK_ID),
CONSTRAINT FOREIGN KEY (USER_ID) REFERENCES USERS(USER_ID)
);

INSERT INTO STOCK VALUES('1001','Samsung AR13CYECABTNTC 1.5 HP Split Type Airconditioner
',42999,'samsung.webp');
INSERT INTO STOCK VALUES('1002','Panasonic CS/CU-XPU9WKQ 1.0 HP Split Type Air Conditioner
',32999,'panasonic1.png');
INSERT INTO STOCK VALUES('1003','LG Air Conditioner Split Type 1.5 HP HSN12IPX
',40999,'lg.webp');
INSERT INTO STOCK VALUES('1004','Fujidenzo PAC-150AIG 1.5 HP Portable Air Conditioner
',16999,'fuji.png');
INSERT INTO STOCK VALUES('1005','Carrier WCARJ012EEV 1.5 HP Window Type Air Conditioner
',32999,'carrier.webp');
INSERT INTO STOCK VALUES('1006','Panasonic S-24PB3Q6 3HP Floor Standing Tower Air Conditioner
',108999,'panasonic2.jpg');
";
         stmt.executeUpdate(sql);
         System.out.println("Database created successfully...");   	  
      } catch (SQLException e) {
         e.printStackTrace();
      } 
   }
}
