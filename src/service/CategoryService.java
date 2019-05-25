/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Category;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorsaf
 */
public class CategoryService implements InterfaceService<Category>{
    
    private static CategoryService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private CategoryService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CategoryService getInstance(){
        if(instance==null) 
            instance=new CategoryService();
        return instance;
    }
    
    
    

    @Override
    public void insert(Category o) {
         String req="INSERT INTO category(category_name) VALUES ('"+o.getCategory_name()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Category o) {
        String req="delete from category where id_category="+o.getId_category();
        Category p=DisplayById(o.getId_category());
        
          if(p!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Category> DisplayAll() {
       String req="select * from category";
      ObservableList<Category> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Category cat=new Category();
           
               cat.setId_category(rs.getInt("id_category"));
               cat.setCategory_name(rs.getString("category_name"));
                list.add(cat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
     public ObservableList<Category> DisplayAllUser() {
       String req="select * from category";
      ObservableList<Category> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Category cat=new Category();
           
               cat.setId_category(rs.getInt("id_category"));
               cat.setCategory_name(rs.getString("category_name"));
                list.add(cat);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    

    @Override
    public Category DisplayById(int id) {
       String req="select * from category where id_category="+id;
           Category cat=new Category();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               cat.setId_category(rs.getInt("id_category"));
               cat.setCategory_name(rs.getString("category_name"));
              
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return cat;
    }
       
    @Override
    public boolean update(Category p) {
         String qry = "UPDATE category SET  category_name = '"+p.getCategory_name()+"' WHERE id_category = "+p.getId_category();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
