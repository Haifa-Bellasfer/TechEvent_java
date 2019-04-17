package service;

import utils.DataSource;
import entity.Story;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.Session;

public class StoryService implements InterfaceService<Story>{
    
    private static StoryService instance;
    private Statement st;
    private ResultSet rs;
    
    
    private StoryService() {
        DataSource cs=DataSource.getInstance();
        try {
            st=cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static StoryService getInstance(){
        if(instance==null) 
            instance=new StoryService();
        return instance;
    }
    
    
    
  public int getIdByContent (String content){
        String req="select * from story where content_story='"+content+"'";
        int id=0 ;
                   
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                id =  ((Number) rs.getObject(1)).intValue();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
            
        } 
       return id;
        
    }
    @Override
    public void insert(Story s) {
         String req="INSERT INTO story(id_user,content_story,creation_date) VALUES ('"+s.getId_user()+"','"+s.getContent_story()+"','"+s.getCreation_date()+"')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    @Override
    public List<Story> DisplayAll() {
       String req="select * from story";
        List<Story> list=new ArrayList<>();
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
                Story str=new Story();
           
               str.setId_story(rs.getInt("id_story"));
               str.setContent_story(rs.getString("content_story"));
                list.add(str);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public ObservableList<String> DisplayAllById(int id) {
        ObservableList<String> list=FXCollections.observableArrayList();
        
       String req="select * from story where id_user="+id;
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               list.add(rs.getString("content_story"));
              
            }  
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    return list;
    }
    
     public ObservableList<String> DisplayByIds(ArrayList<Integer> array) {
      ObservableList<String> list=FXCollections.observableArrayList();
        
        for (Integer array1 : array) {
            String req = "select * from story where id_story=" + array1;
        
        try {
            rs=st.executeQuery(req);
            while(rs.next()){
               list.add(rs.getString("content_story"));
              
            }  
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }}
    return list;
     
     
     }

    @Override
    public void delete(Story o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Story os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Story DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
}
