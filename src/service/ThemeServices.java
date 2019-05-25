/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Theme;
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
public class ThemeServices implements InterfaceService<Theme>
{
    
    private static ThemeServices instance;
    private Statement st;
    private ResultSet rs;
    
    
    private ThemeServices() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ThemeServices getInstance(){
        if(instance==null) 
            instance=new ThemeServices();
        return instance;
    }

    
    public boolean Exist(int  id) {
        int nb=0;
        String req="select * from club where theme_id="+id;
                
        try {
           rs= st.executeQuery(req);
            while(rs.next()){
                
                nb++;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nb!=0;
    }
    
    
    
    @Override
    public void insert(Theme t) {
        String req="INSERT INTO `Theme` (`theme_name`) "
                + "VALUES ( '"+ t.getTheme_name() + "') ";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Theme o) {
    String req="delete from theme where id_theme="+o.getId_theme();
        Theme c=DisplayById(o.getId_theme());
        
          if(c!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");    
    }

    @Override
    public ObservableList<Theme> DisplayAll() {
        String req="select * from theme";
        ObservableList<Theme> list= FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Theme t = new Theme();
                t.setTheme_name(rs.getString("theme_name"));   
                t.setId_theme(rs.getInt("id_theme"));
                
      
            list.add(t);
            }
            } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    public List<Theme> Display() {
        String req="select theme_name from theme";
        ObservableList<Theme> list= FXCollections.observableArrayList();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Theme t = new Theme();
                t.setTheme_name(rs.getString("theme_name"));   
                
      
            list.add(t);
            }
            } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Theme DisplayById(int id) {
        String req="select * from theme where id_theme="+id;
           Theme c=new Theme();
        try {
            rs=st.executeQuery(req);
            
            rs.next();
               c.setId_theme(rs.getInt("id_theme"));
               c.setTheme_name(rs.getString("theme_name"));
               
           
             
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
    return c;
    }

    @Override
    public boolean update(Theme os) {
    String qry = "UPDATE theme SET theme_name='"+os.getTheme_name() +"'WHERE id_theme="+os.getId_theme();
                
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ThemeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;    
    }
    
    
}
