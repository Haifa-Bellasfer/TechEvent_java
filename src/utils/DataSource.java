package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ihèb
 */
public class DataSource {
    private String url="jdbc:mysql://127.0.0.1:8111/phoenix";
    private String login="root";
    private String pwd="root";
    private Connection cnx;
    private static DataSource instance;

    public Connection getCnx() {
        return cnx;
    }
       
    private DataSource() {
        try {
            cnx=DriverManager.getConnection(url, login, pwd);
            System.out.println("connx etablie");
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   public static DataSource getInstance(){
       
       if(instance==null)
           instance=new DataSource();
       return instance;
   }
    
}
