/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Category;
import entity.event;
import utils.DataSource;
import entity.user_categorie;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorsaf
 */
public class User_CategoryService implements InterfaceService<user_categorie>{
    
    
    private static User_CategoryService instance;
    private Statement st;
    private ResultSet rs;
    
    
     private User_CategoryService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(User_CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static User_CategoryService getInstance(){
        if(instance==null) 
            instance=new User_CategoryService();
        return instance;
    }
    
    

    @Override
    public void insert(user_categorie o) {
          String req="INSERT INTO user_categorie(category_id, user_id) VALUES ('"+o.getCategory_id()+"','"+o.getUser_id()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(user_categorie o) {
      String req="DELETE FROM user_categorie WHERE id="+o.getId();
        user_categorie p=DisplayByIdd(o.getCategory_id());
        
          if(p!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(User_CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    
    
    
    
     public user_categorie DisplayByIdd(int id) {
     String req="select * from user_categorie where id="+id;
           user_categorie cat=new user_categorie();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               cat.setId(rs.getInt("id"));
               cat.setUser_id(rs.getInt("user_id"));
               cat.setId(rs.getInt("category_id"));
              
            
        } catch (SQLException ex) {
            Logger.getLogger(User_CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }
    
    
    
    
  

    @Override
    public user_categorie DisplayById(int id) {
     String req="select * from user_categorie where id="+id;
           user_categorie cat=new user_categorie();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               cat.setId(rs.getInt("id"));
               cat.setUser_id(rs.getInt("user_id"));
               cat.setId(rs.getInt("category_id"));
              
            
        } catch (SQLException ex) {
            Logger.getLogger(User_CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }

    @Override
    public boolean update(user_categorie os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<user_categorie> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
     public ObservableList<user_categorie> DisplayAllUserCategory(int id) {
      String req="select * from user_categorie where user_id="+id;
       ObservableList<user_categorie> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                user_categorie u=new user_categorie();
           
               u.setId(rs.getInt("id"));
               u.setUser_id(rs.getInt("user_id"));
               u.setCategory_id(rs.getInt("category_id"));
               list.add(u);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
     
      public user_categorie DisplayUserCategory(int id ,int id2) {
      String req="select * from user_categorie where user_id="+id+"and category_id="+id2;
        
           user_categorie cat=new user_categorie();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               cat.setId(rs.getInt("id"));
               cat.setUser_id(rs.getInt("user_id"));
               cat.setCategory_id(rs.getInt("category_id"));
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }
      
      
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     

     }
     
    
    
    
