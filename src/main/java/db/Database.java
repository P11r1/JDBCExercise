package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
   public static Connection DbConn() {
       Connection connection = null;

       try {
           connection = DriverManager.getConnection(
                   "jdbc:postgresql://babar.db.elephantsql.com/nsuntueb",
                   "nsuntueb",
                   "WZQQ70aO88BFMYF-K5tZebJhk6tW3kmi");
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return connection;
   }
}
