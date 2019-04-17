/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entity.Club;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import service.ClubService;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class ClubAccController implements Initializable {

    @FXML
    private JFXListView<String> list;
     
    ObservableList<Club> ls = FXCollections.observableArrayList();
    ObservableList<String> ss = FXCollections.observableArrayList();
    ClubService cs = ClubService.getInstance();
    @FXML
    private JFXButton bt;

    @FXML
    private void join(MouseEvent event) {
        bt.setVisible(true);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bt.setVisible(false);
        ls=cs.DisplayAll();
        for (int i = 0; i < ls.size(); i++) {
            String s =ls.get(i).affiche();
            ss.add(s);
            list.setItems(ss);
        }
        bt.setOnAction(e ->{
                list.setVisible(false);
                
        });
        
    }
    
}
