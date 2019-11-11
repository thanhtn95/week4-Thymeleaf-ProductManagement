package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBDao {
    protected static Connection initializeDatabase() throws Exception {
        String dbName, dbUsername,dbPassword;
        Connection conn = null;
        try {
            String dbDriver = "com.mysql.jdbc.Driver";
            String dbURL = "jdbc:mysql://localhost:3306/";

            dbName = "Product_test";
            dbUsername = "zonesama";
            dbPassword = "06A380617";

            Class.forName(dbDriver);
            conn = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);
        }catch( Exception e){
            System.out.println(e.getMessage());
        }
        return conn;
    }
}
