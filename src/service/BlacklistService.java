/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Blacklist;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import utils.DataSource;

/**
 *
 * @author khaled
 */
public class BlacklistService implements InterfaceService<Blacklist>{
    
    
    private static BlacklistService instance ; 
    private Statement st;
    private ResultSet rs;
      
    
     private BlacklistService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(BlacklistService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public static BlacklistService getInstance(){
        if(instance==null) 
            instance=new BlacklistService();
        return instance;
    }
 
 
 

    @Override
    public void insert(Blacklist o) {
        
        String req="INSERT INTO blacklist (id_blacklist)VALUES ('"+o.getId_blacklist()+"')";
          try { 
            st.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(BlacklistService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Blacklist o) {
       
          String req="DELETE FROM blacklist WHERE id_blacklist="+o.getId_blacklist();
           Blacklist rep =DisplayById(o.getId_blacklist());
        
          if(rep!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
    }

    @Override
    public ObservableList<Blacklist> DisplayAll() {
        
         String req="select * from blacklist";
           ObservableList<Blacklist> list=FXCollections.observableArrayList();
             try {
            rs=st.executeQuery(req);
            while(rs.next()){
                  Blacklist bl =new Blacklist();
                  
                  
                bl.setId_blacklist(rs.getInt("id_blacklist"));
                  list.add(bl);
            }
                
            }
             catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Blacklist DisplayById(int id) {
        
         String req="select * from blacklist where id_blacklist="+id;
            Blacklist bl =new Blacklist();
              try {
             rs=st.executeQuery(req);
            while(rs.next()){
              
               bl.setId_blacklist(rs.getInt("id_blacklist"));
               
            }
       
    } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
           }
            return bl;
    }
    
    
    
    @Override
    public boolean update(Blacklist os) {
      
           String qry = "UPDATE report SET id_blacklist='"+os.getId_blacklist()+"')";
              try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
}
}
    

              
