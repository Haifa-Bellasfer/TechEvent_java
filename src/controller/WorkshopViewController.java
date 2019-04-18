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
import java.io.IOException;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import service.ClubService;
import service.WorkshopServices;
import view.mainFx;

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
    @FXML
    private Label home;
    @FXML
    private ComboBox<?> searchTheme;
    @FXML
    private JFXButton subS;
    @FXML
    private Label myWork;
    @FXML
    private Label createWork;
    @FXML
    private Label Clubs;

    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopList.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        createWork.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopView.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        home.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/ClubAcc.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        WorkshopServices WS = WorkshopServices.getInstance();
        ClubService cs = ClubService.getInstance();
        ObservableList<Club> list=FXCollections.observableArrayList();
        list = cs.DisplayAll(3);
        combo.setItems(list);
        date.setValue(LocalDate.now());
        add.setOnAction(event -> {
            String Regex= "\\d+";
            
            if ((title.getText().isEmpty())
                    ||(desc.getText().isEmpty())
                    ||(loc.getText().isEmpty())
                    ||(!nbr.getText().matches(Regex))
                    ||(LocalDate.now().isAfter(date.getValue())))
                    
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Data invalid ");
            alert.show();
            }else{
            
            
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
            

            }});
    }    
    
}
