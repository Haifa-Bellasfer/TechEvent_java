/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import service.StoryService;
import service.UserService;
import service.UserStoryService;
import entity.UserStory;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.Session;

/**
 *
 * @author Dalli
 */
public class ElseProfileController implements Initializable{

    @FXML
    private ListView<String> listView;
    @FXML
    private ListView<String> listProfile;
    @FXML
    private JFXButton btnReturn;
    @FXML
    private Button btnShare;
    
    
    @FXML
    private void HandleEvents(MouseEvent event) {
        
           
             if (event.getSource() == btnReturn) {
            
             try {
                 Node node = (Node) event.getSource();
                 Stage stage = (Stage) node.getScene().getWindow();
                 stage.close();
                 Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/Profile.fxml")));
                 stage.setScene(scene);
                 stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(ElseProfileController.class.getName()).log(Level.SEVERE, null, ex);
             }
           }
             
             
                if (event.getSource() == btnShare) {
            
           
        String content =listView.getSelectionModel().getSelectedItem();
        UserStoryService usd=UserStoryService.getInstance();
        
         StoryService strdao=StoryService.getInstance();
        int id=strdao.getIdByContent(content);
        UserStory us = new UserStory(Session.current_user, id);
        usd.insert(us);
           }
             
        
        
    }
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
         StoryService strdao=StoryService.getInstance();
     listView.setItems(strdao.DisplayAllById(Session.searched_user));
     UserService udao=UserService.getInstance();
     listProfile.setItems(udao.DisplayById(Session.searched_user));
     Session.searched_user=0;
    
    
    }
    
}
