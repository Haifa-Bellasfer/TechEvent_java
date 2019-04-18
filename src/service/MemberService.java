/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.ClubUser;
import entity.Theme;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.DataSource;

/**
 *
 * @author mbare
 */
public class MemberService {
    
    private static MemberService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private MemberService() {
        DataSource m=DataSource.getInstance();
        try {
            st=m.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static MemberService getInstance(){
        if(instance==null) 
            instance=new MemberService();
        return instance;
    }
    
    
    public ObservableList<ClubUser> DisplayAll(int id)  {
        String req="select * from club_user where club_id="+id;
        ObservableList<ClubUser> list=FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                ClubUser c = new ClubUser();
                c.setSkills(rs.getString("skills"));   
                c.setWhy(rs.getString("why"));
                c.setYou_are(rs.getString("you_are"));
                c.setClub_user_status(rs.getString("club_user_status"));
                c.setId_club_user(rs.getInt("id_club_user"));
               c.setMember_id(rs.getInt("member_id")); 
               c.setClub_id(rs.getInt("club_id"));
            list.add(c);
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public ClubUser DisplayById(int id) {
        String req="select * from Club_User where id_club_user="+id;
           ClubUser c=new ClubUser();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
            c.setId_club_user(rs.getInt("id_club_user"));
               c.setMember_id(rs.getInt("member_id")); 
               c.setSkills(rs.getString("skills"));   
                c.setWhy(rs.getString("why"));
                c.setYou_are(rs.getString("you_are"));
                //c.setClub_user_status(rs.getString("club_user_status"));
           
             
        } catch (SQLException ex) {
            Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }
    
    public ObservableList<ClubUser> DisplayMember(int id) {
        String req="select * from Club_User where member_id="+id;
        ObservableList<ClubUser> list=FXCollections.observableArrayList();
           
        try {
            rs=st.executeQuery(req);
            
            while(rs.next()){
            ClubUser c=new ClubUser();
            c.setId_club_user(rs.getInt("id_club_user"));
            c.setClub_id(rs.getInt("club_id"));
               c.setMember_id(rs.getInt("member_id")); 
               c.setSkills(rs.getString("skills"));   
                c.setWhy(rs.getString("why"));
                c.setYou_are(rs.getString("you_are"));
                list.add(c);
                //c.setClub_user_status(rs.getString("club_user_status"));
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }
    
    public boolean update(ClubUser e) {
    String qry = "UPDATE Club_User SET club_user_status='"+e.getClub_user_status()+ "'WHERE id_club_user="+e.getId_club_user();
                
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    public void delete(ClubUser o) {
        String req="delete from club_user where Id_club_user="+o.getId_club_user();
        ClubUser c=DisplayById(o.getId_club_user());
        if(c!=null)
            try {
                
                st.executeUpdate(req);
                
            } catch (SQLException ex) {
                Logger.getLogger(MemberService.class.getName()).log(Level.SEVERE, null, ex);
            }else System.out.println("n'existe pas");
    }
    
    public void insert(ClubUser c) {
        String req="INSERT INTO `Club_user` (`why`,`you_are`,`skills`,`member_id`,`club_id`,`Club_user_status`) "
                + "VALUES ( '"+ c.getWhy()+ "', '" + c.getYou_are()+  "', '" + c.getSkills()
                + "', '"+ c.getMember_id()+ "', '"+ c.getClub_id()+ "', '"+ c.getClub_user_status()+ "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
