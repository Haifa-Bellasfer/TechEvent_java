/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import entity.Club;
import entity.Workshop;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import service.ClubService;
import service.WorkshopServices;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class WorkshopViewController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField loc;
    @FXML
    private JFXTextField nbr;
    @FXML
    private DatePicker date;
    @FXML
    private JFXButton add;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXComboBox<Club> combo;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        WorkshopServices WS = WorkshopServices.getInstance();
        ClubService cs = ClubService.getInstance();
        ObservableList<Club> list=FXCollections.observableArrayList();
        list = cs.DisplayAll(3);
        combo.setItems(list);
        date.setValue(LocalDate.now());
        add.setOnAction(event -> {
            
            Workshop p = new Workshop(title.getText(), Integer.parseInt(nbr.getText()), desc.getText(), Date.valueOf(date.getValue()), loc.getText(),combo.getValue().getId_club());
            WS.insert(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Workshop added !!!");
            alert.show();
            title.setText("");
            desc.setText("");
            nbr.setText("");
            date.setValue(LocalDate.now());
            

        });
    }    
    
}
