/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Workshop;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author mbare
 */
public class WorkshopServices implements InterfaceService<Workshop>
{

    private static WorkshopServices instance;
    private Statement st;
    private ResultSet rs;
    
    
    private WorkshopServices() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static WorkshopServices getInstance(){
        if(instance==null) 
            instance=new WorkshopServices();
        return instance;
    }
    
    
    
    @Override
    public void insert(Workshop w) {
        String req="INSERT INTO `Workshop` (`title`, `location`,`Workshop_description`,`Nbr_places`,`Start_date`,`Id_workshop`,`club_id`) VALUES ( '"
                + w.getTitle() + "', '" + w.getLocation()+ "', '" + w.getWorkshop_description()+ "'"
                + ", '" + w.getNbr_places()+ "', '" + w.getStart_date()+ "', '" + w.getId_workshop()+ "', '" + w.getClub_id()+ "') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Workshop o) {
        String req="delete from Workshop where id_workshop="+o.getId_workshop();
        Workshop w=DisplayById(o.getId_workshop());
        
          if(w!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Workshop> DisplayAll() {
        String req="select * from Workshop";
        ObservableList<Workshop> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Workshop c = new Workshop();
                c.setId_workshop(rs.getInt("id_workshop"));   
                c.setTitle(rs.getString("title"));
                c.setLocation(rs.getString("location"));
                c.setNbr_places(rs.getInt("nbr_places"));
                c.setStart_date(rs.getDate("start_date"));
      
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<Workshop> DisplayWork(int id) {
        String req="select * from Workshop where club_id="+id;
        ObservableList<Workshop> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Workshop c = new Workshop();
                c.setId_workshop(rs.getInt("id_workshop"));   
                c.setTitle(rs.getString("title"));
                c.setLocation(rs.getString("location"));
                c.setNbr_places(rs.getInt("nbr_places"));
                c.setStart_date(rs.getDate("start_date"));
                c.setClub_id(rs.getInt("club_id"));
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    
    public ObservableList<Workshop> DisplayW() {
        String req="select title,id_workshop from Workshop";
        ObservableList<Workshop> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Workshop c = new Workshop();
                c.setId_workshop(rs.getInt("id_workshop"));
                c.setTitle(rs.getString("title"));
                
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Workshop DisplayById(int id) {
       
        String req="select * from Workshop where id_workshop="+id;
           Workshop c=new Workshop();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               c.setId_workshop(rs.getInt("id_workshop"));   
                c.setTitle(rs.getString("title"));
                c.setLocation(rs.getString("location"));
                c.setNbr_places(rs.getInt("nbr_places"));
                c.setStart_date(rs.getDate("start_date"));
                c.setClub_id(rs.getInt("club_id"));
                
           
             
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

    @Override
    public boolean update(Workshop w) {
        String qry = "UPDATE workshop SET id_workshop='"+w.getId_workshop()+"',title='"+w.getTitle()+"',nbr_places='"+w.getNbr_places()
                +"',workshop_description='"+w.getWorkshop_description()+"',start_date='"+w.getStart_date()+"',location='"+w.getLocation()+"' WHERE id_workshop='"+w.getId_workshop()+"'";
                
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(WorkshopServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
}
