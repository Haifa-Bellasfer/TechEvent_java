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
import com.jfoenix.controls.JFXTextField;
import entity.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
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
    private JFXTextField txtSearch;
    @FXML
    private Label lblError;
    int ids=0;
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
         StoryService strdao=StoryService.getInstance();
     listView.setItems(strdao.DisplayAllById(Session.searched_user.getId_user()));
     UserService udao=UserService.getInstance();
     User user = new User();
     
     
      user = udao.DisplayById(Session.searched_user.getId_user());
     
     ObservableList<String> list=FXCollections.observableArrayList();
     
               list.add(user.getUsername());
               list.add(user.getFirst_name());
               list.add(user.getLast_name());
               list.add(user.getEmail());
               list.add(user.getAddress());
               list.add(String.valueOf(user.getPhone()));
         
         listProfile.setItems(list);
    
    
    
    
    
    
    
        user = udao.DisplayById(Session.current_user.getId_user());
        ids=0;
        txtSearch.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {

                ids = udao.getIdByUsername(txtSearch.getText());
                if (ids != 0 && ids != Session.current_user.getId_user()) {

                    Session.searched_user.setId_user(ids);

                    try {
                        Node node = (Node) event.getSource();
                        Stage stage = (Stage) node.getScene().getWindow();
                        stage.close();
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/view/ElseProfile.fxml")));
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    lblError.setTextFill(Color.TOMATO);
                    lblError.setText("enter valid username");
                }
            }

        });
    
    
    
    
    
    
}


    @FXML
    private void HandleEvents(MouseEvent event) {
        
           
             if (event.getSource() == btnReturn) {
            
   Session.searched_user.setId_user(0);
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
        UserStory us = new UserStory(Session.current_user.getId_user(), id);
        usd.insert(us);
           }
             
        
        
    }
}
