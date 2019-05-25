/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import controller.MyBCrypt;
import entity.Story;
import entity.User;
import com.mysql.jdbc.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.paint.Color;
import utils.Session;

/**
 *
 * @author Dalli
 */
public class UserService implements InterfaceService<Object> {
 private Statement st;
    private ResultSet rs;
    private static UserService instance;
    
    
     private UserService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static UserService getInstance(){
        if(instance==null) 
            instance=new UserService();
        return instance;
    }
    
    
    
    public boolean isAdmin(int id){
    String req="select status from fos_user where id="+id;
    boolean bool=false;
    
     try {
         rs=st.executeQuery(req);
            while(rs.next()){
         if("Admin".equals(rs.getString("status"))){
             bool=true;}
            
         }
                 
                 
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
    return bool;
    
    }
            
            
            
    
    public void insert(User user) {
    
    
    
            
String pw_hash = MyBCrypt.hashpw(user.getPassword(), MyBCrypt.gensalt());
            String req = "INSERT INTO fos_user ( first_name, last_name, email,email_canonical, username,username_canonical, address,phone,password,enabled,roles,status)"
                    + " VALUES ('"+user.getFirst_name()+"','"+user.getLast_name()+"','"+user.getEmail()+"','"+user.getEmail()+"','"+user.getUsername()+"','"+user.getUsername()+"','"+user.getAddress()+"','"+user.getPhone()+"','"+pw_hash+"','1','a:0:{}','Member')";
      
           
     try {
         
            st.executeUpdate(req);
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
     
    
    
    
    
    
    
    
    
    
    
    
    
    }

    @Override
    public void delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getId(String username){
        
         int id = 0;
                String req ="SELECT id FROM fos_user Where username ='"+username+"'";
               
     try {
         rs=st.executeQuery(req);
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
     try {
         for (; rs.next();) {
             id =  rs.getInt(1);
         }    } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        return id;
    }
    
    
    
    
     public String getEmail(String username){
        
         String email=null;
                String req ="SELECT email FROM fos_user Where username ='"+username+"'";
               
     try {
         rs=st.executeQuery(req);
         while(rs.next())
         {
             email =  rs.getString(1);
         }
         
         
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
        
        return email;
    }
    
    
    
    
    public ObservableList<User> DisplayAllUsers() {
    
        ObservableList<User> list=FXCollections.observableArrayList();
        
        
       String req="select * from fos_user ";
           
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                User user = new User();
               user.setAddress(rs.getString("address"));
               user.setEmail(rs.getString("email"));
               user.setFirst_name(rs.getString("first_name"));
               user.setUsername(rs.getString("username"));
               user.setLast_name(rs.getString("last_name"));
               user.setStatus(rs.getString("status"));
               user.setPhone(rs.getString("phone"));
               list.add(user);
               
              
            }  
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
        
        
        
        
        
        
        
        
    }

    @Override
    public User DisplayById(int id) {
        
        
        
        ObservableList<String> list=FXCollections.observableArrayList();
        
        
       String req="select * from fos_user where id="+id;
           User user = new User();
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               user.setId_user(rs.getInt("id"));
               user.setUsername(rs.getString("username"));
               user.setFirst_name(rs.getString("first_name"));
               user.setLast_name(rs.getString("last_name"));
               user.setEmail(rs.getString("email"));
               user.setAddress(rs.getString("address"));
               user.setPhone(rs.getString("phone"));
               user.setStatus(rs.getString("status"));
               user.setRoles(rs.getString("roles"));
               
              
            }  
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return user;
   }

    
  int id;  
  String bo;
    
    public int getIdByUsername (String username){
        String req="select * from fos_user where username='"+username+"'";
        id=0;
                   
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                id =  ((Number) rs.getObject(1)).intValue();
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            
        }System.out.println("idddddddddd"+id);
        return id;
        
    } 
            
     public String getPass (String username){
           String req= "Select * from fos_user WHERE username ='" +username+"'";
        String password=null;
        
     try {
         rs = st.executeQuery(req);
         while(rs.next())
         {
             
                password=(rs.getString("password"));
              
         }
         
     } catch (SQLException ex) {
         Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
     }
          return password; 
     }
            
    
    public void UpdateUser(User user,int id) {
        String req="UPDATE fos_user SET first_name='"+user.getFirst_name()+"', last_name='"+user.getLast_name()+"', email='"+user.getEmail()+"',email_canonical='"+user.getEmail()+"', username='"+user.getUsername()+"',username_canonical='"+user.getUsername()+"', address='"+user.getAddress()+"',phone='"+user.getPhone()+"'" +
                "WHERE id="+id;
       
        
        try {
        user.setPassword(user.getPassword());
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
}
    
     public void UpdatePassword(String newpass,int id) {
        String req="UPDATE fos_user SET password='"+newpass +
                "' WHERE id="+id;
        
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
}

    @Override
    public void insert(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<Object> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
