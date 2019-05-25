/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Order_Line;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author PC ASUS
 */
public class Order_LineService implements InterfaceService<Order_Line>{

    private static Order_LineService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private Order_LineService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Order_LineService getInstance(){
        if(instance==null) 
            instance=new Order_LineService();
        return instance;
    }

    @Override
    public void insert(Order_Line o) {
         String req = "INSERT INTO order_line(ticket_id,cart_id,quantity,price) VALUES ('"+o.getTicket_id()+"','"+o.getCart_id()+"','"+o.getQuantity()+"','"+o.getPrice()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 

    @Override
    public void delete(Order_Line o) {
   String req="delete from order_line where id_line="+o.getId_line();
        Order_Line ol=DisplayById(o.getId_line());
        
          if(ol!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }    

    @Override
    public ObservableList<Order_Line> DisplayAll() {
    
        String req="select * from order_line";
        ObservableList<Order_Line> list= FXCollections.observableArrayList(); 
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Order_Line ol=new Order_Line();
           
               ol.setId_line(rs.getInt("id_line"));
               ol.setTicket_id(rs.getInt("ticket_id"));
               ol.setCart_id(rs.getInt("cart_id"));
               ol.setQuantity(rs.getInt("quantity"));
               ol.setPrice(rs.getDouble("price"));
                list.add(ol);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Order_Line DisplayById(int id) {

String req="select * from order_line where id_line="+id;
             Order_Line ol=new Order_Line();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               ol.setId_line(rs.getInt("id_line"));
               ol.setTicket_id(rs.getInt("ticket_id"));
               ol.setCart_id(rs.getInt("cart_id"));
               ol.setQuantity(rs.getInt("quantity"));
               ol.setPrice(rs.getDouble("price"));
             
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ol;
}

    @Override
    public boolean update(Order_Line os) {
 String qry = "UPDATE order_line SET id_line='"+os.getId_line()+"',ticket_id='"+os.getTicket_id()+"',cart_id='"
         +os.getCart_id()+"',quantity='"+os.getQuantity()+"',price='"+os.getPrice()+"' WHERE id_line="+os.getId_line();
      
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }      
    
    
}
