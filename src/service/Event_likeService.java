/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import controller.Vars;
import utils.DataSource;
import entity.event_likes;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *
 * @author Dorsaf
 */
public class Event_likeService implements InterfaceService<event_likes>{
    
    private static Event_likeService instance;
    private Statement st;
    private ResultSet rs;
    
    
     private Event_likeService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(Event_likeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static Event_likeService getInstance(){
        if(instance==null) 
            instance=new Event_likeService();
        return instance;
    }
    
    
    
    
    

    @Override
    public void insert(event_likes o) {
           String req="INSERT INTO event_likes(user_id,event_id) VALUES ('"+o.getUser_id()+"','"+o.getEvent_id()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(Event_likeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(event_likes o) {
      String req="delete from event_likes where event_id="+Vars.current_event;
        event_likes p=DisplayById(o.getId_like());
        
          if(p!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Event_likeService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

  

    @Override
    public event_likes DisplayById(int id) {
    String req="select * from event_likes WHERE event_id="+id;
           event_likes ev=new event_likes();
        try {
            rs=st.executeQuery(req);
          
               rs.next();
               ev.setId_like(rs.getInt("id_like"));
               ev.setUser_id(rs.getInt("user_id"));
               ev.setEvent_id(rs.getInt("event_id"));
               
              
             
        } catch (SQLException ex) {
            Logger.getLogger(Event_likeService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ev;
    }

    @Override
    public boolean update(event_likes os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<event_likes> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
