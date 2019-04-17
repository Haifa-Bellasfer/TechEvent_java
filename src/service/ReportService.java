/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import entity.Report;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableSet;
import utils.DataSource;

/**
 *
 * @author khaled
 */
public class ReportService implements InterfaceService<Report>{
    
 private static ReportService instance ; 
 private Statement st;
 private ResultSet rs;
 
 
  private ReportService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public static ReportService getInstance(){
        if(instance==null) 
            instance=new ReportService();
        return instance;
    }
 

    @Override
    public void insert(Report o) {
        String req="INSERT INTO report(comment_id ,nb_report_comment,nb_report_user, date_of_report,user_id)VALUES ('"+o.getCommentid()+"','"+o.getNbreportcomment()+"','"+o.getNbreportuser()+"','"+o.getDateofreport()+"','"+o.getUserid()+"')";
          try { 
            st.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Report o) {
        String req="DELETE FROM report WHERE id_report="+o.getIdreport();
           Report rep =DisplayById(o.getIdreport());
        
          if(rep!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }else System.out.println("n'existe pas");
        
    }

    @Override
    public ObservableList<Report> DisplayAll() {
         String req="select * from Report";
        ObservableList<Report> list=FXCollections.observableArrayList(); 
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Report rep =new Report();
           
               rep.setIdreport(rs.getInt("id_report"));
               rep.setCommentid(rs.getInt("comment_id"));
               rep.setNbreportcomment(rs.getInt("nb_report_comment"));
               rep.setNbreportuser(rs.getInt("nb_report_user"));
               rep.setDateofreport(rs.getDate("date_of_report"));
               rep.setUserid(rs.getInt("user_id"));
          
               list.add(rep);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    @Override
    public Report DisplayById(int id) {
          String req="select * from Report where id_report="+id;
           Report rep =new Report();
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
              
               rep.setIdreport(rs.getInt("id_report"));
               rep.setCommentid(rs.getInt("comment_id"));
               rep.setNbreportcomment(rs.getInt("nb_report_comment"));
               rep.setNbreportuser(rs.getInt("nb_report_user"));
               rep.setDateofreport(rs.getDate("date_of_report"));
               rep.setUserid(rs.getInt("userid"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return rep;
    }

    @Override
    public boolean update(Report os) {
        String qry = "UPDATE report SET  id_report='"+os.getIdreport()+"',comment_id='"+os.getCommentid()+"',nb_report_comment='"+os.getNbreportcomment()+"',nb_report_user='"+os.getNbreportuser()+"',date_of_report='"+os.getDateofreport()+"',user_id='"+os.getUserid()+"')";
        
        try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       }
    
      public int DisplayCountrep(int id) {
          String req="select Count (*) from Report where comment_id="+id;
            int nb = 0;
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
    
              nb=rs.getInt(1);
               
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ReportService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nb;}
        
    
    
    
}

