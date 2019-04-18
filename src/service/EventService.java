/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Category;
import entity.event;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class EventService implements InterfaceService<event> {
    
    private static EventService instance;
    private Statement st;
    private ResultSet rs;
    
    
     private EventService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static EventService getInstance(){
        if(instance==null) 
            instance=new EventService();
        return instance;
    }
    

    @Override
    public void insert(event o){
          String req="INSERT INTO event(category_id,organizer_id,event_name,description,nb_participant,"
                  + " photo, status, start_date,end_date , archive, price_ticket, nb_like, address) VALUES ('"+o.getCategory_id()+"','"
                  +o.getOrganizer_id()+"','"+o.getEvent_name()+"','"+o.getDescription()+"','"+o.getNb_participant()+"','"+o.getPhoto()+"'"
                  + ",'"+o.getStatus()+"','"+o.getStart_date()+"','"+o.getEnd_date()+"','"+o.getArchive()+"','"+o.getPrice_ticket()+"'"
                  + ",'"+o.getNb_like()+"','"+o.getAddress()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    @Override
    public void delete(event o) {
        String req="delete from event where id_event="+o.getId_event();
        event ev=DisplayById(o.getId_event());
        
          if(ev!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<event> DisplayAll() {
    String req="select * from event";
       ObservableList<event> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                event ev=new event();
           
               ev.setId_event(rs.getInt("id_event"));
               ev.setOrganizer_id(rs.getInt("organizer_id"));
               ev.setEvent_name(rs.getString("event_name"));
               ev.setCategory_id(rs.getInt("category_id"));
               ev.setDescription(rs.getString("description"));
               ev.setNb_participant(rs.getInt("nb_participant"));
               ev.setPhoto(rs.getString("photo"));
               ev.setStatus(rs.getString("status"));
               ev.setStart_date(rs.getDate("start_date"));
               ev.setEnd_date(rs.getDate("end_date"));
               ev.setArchive(rs.getInt("archive"));
               ev.setPrice_ticket(rs.getDouble("price_ticket"));
               ev.setNb_like(rs.getInt("nb_like"));
               ev.setAddress(rs.getString("address"));
               list.add(ev);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public event DisplayById(int id) {
         String req="select * from event where id_event="+id;
             event ev=new event();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
               ev.setId_event(rs.getInt("Id_event"));
               ev.setOrganizer_id(rs.getInt("organizer_id"));
               ev.setEvent_name(rs.getString("event_name"));
               ev.setCategory_id(rs.getInt("category_id"));
               ev.setDescription(rs.getString("description"));
               ev.setNb_participant(rs.getInt("nb_participant"));
               ev.setPhoto(rs.getString("photo"));
               ev.setStatus(rs.getString("status"));
               ev.setStart_date(rs.getDate("start_date"));
               ev.setEnd_date(rs.getDate("end_date"));
               ev.setArchive(rs.getInt("archive"));
               ev.setPrice_ticket(rs.getDouble("price_ticket"));
               ev.setNb_like(rs.getInt("nb_like"));
               ev.setAddress(rs.getString("address"));
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return ev;
    }

    @Override
    public boolean update(event e) {
        String qry = "UPDATE event SET category_id='"+e.getCategory_id()+"',organizer_id='"+e.getOrganizer_id()+"',event_name='"+e.getEvent_name()+"',"
                + "description='"+e.getDescription()+"',nb_participant='"+e.getNb_participant()+"',photo='"+e.getPhoto()+"',status='"+e.getStatus()+"',start_date='"+e.getStart_date()+"',end_date='"+e.getEnd_date()+"',"
                + "archive='"+e.getArchive()+"',price_ticket='"+e.getPrice_ticket()+"',nb_like='"+e.getNb_like()+"',address='"+e.getAddress()+"'WHERE id_event="+
                e.getId_event();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
    
    
     public boolean updateLike(event e ,int like) {
         int nb=e.getNb_like()+like;
        String qry = "UPDATE event SET nb_like='"+nb+"',address='"+e.getAddress()+"'WHERE id_event="+
                e.getId_event();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
     
    
        public ObservableList<event> DisplayAllEvent(int cat) {
    String req="select * from event where category_id="+cat;
       ObservableList<event> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                event ev=new event();
           
               ev.setId_event(rs.getInt("id_event"));
               ev.setOrganizer_id(rs.getInt("organizer_id"));
               ev.setEvent_name(rs.getString("event_name"));
               ev.setCategory_id(rs.getInt("category_id"));
               ev.setDescription(rs.getString("description"));
               ev.setNb_participant(rs.getInt("nb_participant"));
               ev.setPhoto(rs.getString("photo"));
               ev.setStatus(rs.getString("status"));
               ev.setStart_date(rs.getDate("start_date"));
               ev.setEnd_date(rs.getDate("end_date"));
               ev.setArchive(rs.getInt("archive"));
               ev.setPrice_ticket(rs.getDouble("price_ticket"));
               ev.setNb_like(rs.getInt("nb_like"));
               ev.setAddress(rs.getString("address"));
               list.add(ev);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
        
        public boolean updateArchive(event p) {
         String qry = "UPDATE event SET  Archive = 1  WHERE  id_event= "+p.getId_event();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
           public boolean updateStatus(event p) {
         String qry = "UPDATE event SET  status = 'ACCEPTED'  WHERE  id_event= "+p.getId_event();
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CategoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
        
        
        
        
        
    public int DisplayCountrep() {
          String req="select Count(id_event) from event where status='WAITING'";
            int nb = 0;
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
    
              nb=rs.getInt(1);
             
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(EventService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nb;}
        
        
        
        
        
        
    
    }
    

