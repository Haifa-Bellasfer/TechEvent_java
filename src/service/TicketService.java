/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Tickett;
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
public class TicketService implements InterfaceService<Tickett>{
    
    private static TicketService instance;
    private Statement st;
    private ResultSet rs;

      private TicketService() {
          DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static TicketService getInstance(){
        if(instance==null) 
            instance=new TicketService();
        return instance;
    }

    @Override
    public void insert(Tickett o) {
         String req ="INSERT INTO ticket(user_id,event_id,time_booked,status,qr_code,code) VALUES ('"+o.getUser_id()+"','"+o.getEvent_id()+"','"+o.getTime_boocked()+"','"+o.getStatus()+"','"+o.getQr_code()+"','"+o.getCode()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  

    @Override
    public void delete(Tickett o) {
   String req="delete from ticket where id_line="+o.getId_ticket();
        Tickett t=DisplayById(o.getId_ticket());
        
          if(t!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(Order_LineService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");   
    }    

    @Override
    public ObservableList<Tickett> DisplayAll() {
  String req="select * from ticket";
        ObservableList<Tickett> list= FXCollections.observableArrayList(); 
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Tickett t=new Tickett();
           
               t.setId_ticket(rs.getInt("id_ticket"));
               t.setUser_id(rs.getInt("user_id"));
               t.setEvent_id(rs.getInt("event_id"));
               t.setTime_boocked(rs.getDate("time_booked"));
               t.setStatus(rs.getInt("status"));
               t.setQr_code(rs.getString("qr_code"));
               t.setCode(rs.getInt("code"));
               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    

    @Override
    public Tickett DisplayById(int id) {
    String req="select * from ticket where id_ticket="+id;
             Tickett t=new Tickett();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
               t.setId_ticket(rs.getInt("id_ticket"));
               t.setUser_id(rs.getInt("user_id"));
               t.setEvent_id(rs.getInt("event_id"));
               t.setTime_boocked(rs.getDate("time_booked"));
               t.setStatus(rs.getInt("status"));
               t.setQr_code(rs.getString("qr_code"));
               t.setCode(rs.getInt("code"));

            
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return t;    }

    @Override
    public boolean update(Tickett os) {
 String qry = "UPDATE ticket SET  status = 0 WHERE id_ticket = "+os.getId_ticket();
      
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    public Tickett DisplayByIdEventTicket(int id) {
    String req="select * from ticket where event_id="+id;
             Tickett t=new Tickett();
        try {
            rs=st.executeQuery(req);
           
            rs.next();
               t.setId_ticket(rs.getInt("id_ticket"));
               t.setUser_id(rs.getInt("user_id"));
               t.setEvent_id(rs.getInt("event_id"));
               t.setTime_boocked(rs.getDate("time_booked"));
               t.setStatus(rs.getInt("status"));
               t.setQr_code(rs.getString("qr_code"));
               t.setCode(rs.getInt("code"));

            
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return t;    }
    
      
     public ObservableList<Tickett> DisplayByEventId(int id) {
    String req="select * from ticket where event_id="+id;
        ObservableList<Tickett> list= FXCollections.observableArrayList(); 
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Tickett t=new Tickett();
           
               t.setId_ticket(rs.getInt("id_ticket"));
               t.setUser_id(rs.getInt("user_id"));
               t.setEvent_id(rs.getInt("event_id"));
               t.setTime_boocked(rs.getDate("time_booked"));
               t.setStatus(rs.getInt("status"));
               t.setQr_code(rs.getString("qr_code"));
               t.setCode(rs.getInt("code"));
               list.add(t);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(TicketService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }    
    
}
