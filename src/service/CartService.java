/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Cart;
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
 * @author PC ASUS
 */
public class CartService implements InterfaceService<Cart>{
    
    private static CartService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private CartService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CartService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CartService getInstance(){
        if(instance==null) 
            instance=new CartService();
        return instance;
    }

    @Override
    public void insert(Cart o) {
         String req="INSERT INTO cart(user_id,total) VALUES ('"+o.getUser_id()+"','"+o.getTotal()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(CartService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Cart o) {
   String req="delete from cart where cart="+o.getId_cart();
        Cart c=DisplayById(o.getId_cart());
        
          if(c!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");    }

    @Override
    public ObservableList<Cart> DisplayAll() {
  String req="select * from cart";
        ObservableList<Cart> list= FXCollections.observableArrayList()  ;
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Cart c=new Cart();
           
               c.setId_cart(rs.getInt("id_cart"));
               c.setUser_id(rs.getInt("user_id"));
               c.setTotal(rs.getDouble("double"));
                list.add(c);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    

    @Override
    public Cart DisplayById(int id) {

String req="select * from cart where cart="+id;
             Cart c=new Cart();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               c.setId_cart(rs.getInt("id_cart"));
               c.setUser_id(rs.getInt("user_id"));
               c.setTotal(rs.getDouble("total"));
              
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;    }

    @Override
    public boolean update(Cart os) {
 String qry = "UPDATE cart SET id_cart='"+os.getId_cart()+"',user_id='"+os.getUser_id()+"',total='"+os.getTotal()
         +"' WHERE id_cart='"+os.getId_cart();
      
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
        public Cart DisplayByIdCart(int id) {

        String req="select * from cart where uesr_id="+id;
             Cart c=new Cart();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               c.setId_cart(rs.getInt("id_cart"));
               c.setUser_id(rs.getInt("user_id"));
               c.setTotal(rs.getDouble("total"));
              
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;    }
    
    
}
