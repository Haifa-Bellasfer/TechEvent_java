/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;


import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import entity.Comment;
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
 * @author khaled
 */
public class CommentService implements InterfaceService<Comment>{
private static CommentService instance;
    private Statement st;
    private ResultSet rs;
    
    
     private CommentService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static CommentService getInstance(){
        if(instance==null) 
            instance=new CommentService();
        return instance;
    }
    

   

    @Override
    public void delete(Comment o){
      
          String req="delete from Comment where id_comment="+o.getId_comment();
        Comment com =DisplayById(o.getId_comment());
        
          if(com!=null)
       try {
           
            st.executeUpdate(req);
             
        } catch (MySQLIntegrityConstraintViolationException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
              Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
          }else System.out.println("n'existe pas");
        
    }

    @Override
    public ObservableList<Comment> DisplayAll() {
         String req="select * from Comment";
        ObservableList<Comment> list=FXCollections.observableArrayList(); ;
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Comment com =new Comment();
           
               com.setId_comment(rs.getInt("id_comment"));
               com.setEvent_id(rs.getInt("event_id"));
               com.setUser_id(rs.getInt("user_id"));
               com.setContent(rs.getString("content"));
               com.setDateofcomment(rs.getDate("dateofcomment"));
              com.setNbrep(rs.getInt("nbrep"));
          
               list.add(com);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    @Override
    public Comment DisplayById(int id) {
          String req="select * from Comment where id_comment="+id;
             Comment com =new Comment();
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
              
               com.setId_comment(rs.getInt("id_comment"));
               com.setEvent_id(rs.getInt("event_id"));
               com.setUser_id(rs.getInt("user_id"));
               com.setContent(rs.getString("content"));
               com.setDateofcomment(rs.getDate("dateofcomment"));
               com.setNbrep(rs.getInt("nbrep"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return com;
    }

     public Comment DisplayByidevent(int id) {
          String req="select * from Comment where event_id="+id;
             Comment com =new Comment();
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
              
               com.setId_comment(rs.getInt("id_comment"));
               com.setEvent_id(rs.getInt("event_id"));
               com.setUser_id(rs.getInt("user_id"));
               com.setContent(rs.getString("content"));
               com.setDateofcomment(rs.getDate("dateofcomment"));
               com.setNbrep(rs.getInt("nbrep"));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return com;
    }
    @Override
    public boolean update(Comment os) {
    String qry = "UPDATE comment SET id_comment='"+os.getId_comment()+"',event_id='"+os.getEvent_id()+"'"
            + ",user_id='"+os.getUser_id()+"',content='"+os.getContent()+"'"
            + ",dateofcomment='"+os.getDateofcomment()+"',nbrep='"+os.getNbrep()+"'WHERE id_comment="+
                    os.getId_comment();
                try {
            if (st.executeUpdate(qry) > 0) {
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
            
    }

   
    
    
     public int DisplayCount(int id) {
          String req="select Count(id_comment) from Comment where event_id="+id;
            int nb = 0;
        try {
             rs=st.executeQuery(req);
            while(rs.next()){
    
              nb=rs.getInt(1);
               
               
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return nb;}
     
      @Override
    public void insert(Comment o) {
   
          String req="INSERT INTO comment(event_id, user_id, content, dateofcomment, nbrep) VALUES ('"+o.getEvent_id()+"','"+o.getUser_id()+"','"+o.getContent()+"','"+o.getDateofcomment()+"','"+o.getNbrep()+"')";
        try { 
            st.executeUpdate(req);
            System.out.println("done");
        } catch (SQLException ex) {
            Logger.getLogger(CommentService.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
      
   
    }
    
   
        
        
    
    
}
