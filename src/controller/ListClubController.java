/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXListView;
import entity.Club;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class ListClubController implements Initializable {

    @FXML
    private JFXListView<Club> list;
     
    
      ClubService CS = ClubService.getInstance();
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           
    }    
    
}
