/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Club;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
public class ClubService implements InterfaceService<Club>
{
    
private static ClubService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private ClubService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ClubService getInstance(){
        if(instance==null) 
            instance=new ClubService();
        return instance;
    }

    @Override
    public void insert(Club c) {
        String req="INSERT INTO `Club` (`club_name`, `club_description`,`Email`,`Facebook`,`theme_id`) VALUES ( '"
                + c.getClub_name() + "', '" + c.getClub_description() + "', '" + c.getEmail() + "'"
                + ", '" + c.getFacebook() + "', '" + c.getTheme()+ "') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Club o) {
        String req="delete from club where id_club="+o.getId_club();
        Club c=DisplayById(o.getId_club());
        if(c!=null)
            try {
                
                st.executeUpdate(req);
                
            } catch (SQLException ex) {
                Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
            }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Club> DisplayAll()  {
        
        String req="select * from club ";
        ObservableList<Club> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Club c = new Club();
                c.setId_club(rs.getInt("id_club"));   
                c.setClub_name( rs.getString("club_name"));
                c.setClub_description(rs.getString("club_description"));
                c.setLogo(rs.getString("logo"));
                c.setEmail(rs.getString("email"));
                c.setClub_status(rs.getString("club_status"));
                c.setTheme(rs.getInt("theme_id"));
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<Club> DisplayAccapted()  {
        String sta="Accepted";
        String req="select * from club where club_status='"+sta+"'";
        ObservableList<Club> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Club c = new Club();
                c.setId_club(rs.getInt("id_club"));   
                c.setClub_name( rs.getString("club_name"));
                c.setClub_description(rs.getString("club_description"));
                c.setLogo(rs.getString("logo"));
                c.setEmail(rs.getString("email"));
                c.setClub_status(rs.getString("club_status"));
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ObservableList<Club> DisplayAll(int id)  {
        String req="select * from club where owner_id="+id;
        ObservableList<Club> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Club c = new Club();
                c.setId_club(rs.getInt("id_club"));   
                c.setClub_name( rs.getString("club_name"));
                c.setClub_description(rs.getString("club_description"));
                c.setLogo(rs.getString("logo"));
                c.setEmail(rs.getString("email"));
      
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
public ObservableList<String> DisplayName(int id)  {
        String req="select club_name from club where owner_id="+id;
        ObservableList<String> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                String ss;
                ss=rs.getString("club_name");   
                
      
            list.add(ss);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    @Override
    public Club DisplayById(int id) {
        String req="select * from Club where id_club="+id;
           Club c=new Club();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
            
               c.setId_club(rs.getInt("id_club"));
               c.setClub_name(rs.getString("club_name"));
               c.setLogo(rs.getString("logo"));
               c.setClub_description(rs.getString("club_description"));
               c.setEmail(rs.getString("email"));
               c.setFacebook(rs.getString("facebook"));
           
             
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

    @Override
    public boolean update(Club e) {
        String qry = "UPDATE club SET club_name='"+e.getClub_name()+"',club_description='"
                +e.getClub_description()+"',email='"+e.getEmail()+"',logo='"+e.getLogo()
                + "',facebook='"+e.getFacebook()+"',club_status='"+e.getClub_status()+ "',theme_id='"+e.getTheme()+ "'WHERE id_club="+e.getId_club();
                
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }  
    
    
    
}
