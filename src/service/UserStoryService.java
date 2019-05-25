/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import utils.DataSource;
import entity.Story;
import entity.UserStory;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Dalli
 */
public class UserStoryService implements InterfaceService<UserStory> {

    private static UserStoryService instance;
    private Statement st;
    private ResultSet rs;

    private UserStoryService() {
        DataSource cs = DataSource.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(UserStoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UserStoryService getInstance() {
        if (instance == null) {
            instance = new UserStoryService();
        }
        return instance;
    }

    @Override
    public void insert(UserStory o) {

        String req = "INSERT INTO user_story(id_user,story_id) VALUES ('" + o.getId_user() + "','" + o.getId_story() + "')";
        try {
            st.executeUpdate(req);
        } catch (SQLException ex) {
            Logger.getLogger(StoryService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList< Integer> DisplayByIdUser(int id) {
        ArrayList< Integer> list = new ArrayList<>();
        String req = "select story_id from user_story where id_user='" + id + "'";
        try {
            rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

    @Override
    public void delete(UserStory o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(UserStory os) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ObservableList<UserStory> DisplayAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserStory DisplayById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
