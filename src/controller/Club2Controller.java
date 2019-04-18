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
import entity.Theme;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import service.ClubService;
import service.ThemeServices;
import utils.Session;
import view.mainFx;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class Club2Controller implements Initializable {

    @FXML
    private JFXComboBox<Theme> tBox;
    @FXML
    private JFXTextField nameC;
    @FXML
    private JFXTextArea desc;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXTextField fb;
    @FXML
    private JFXButton Submit;
    private JFXButton liste;
    @FXML
    private JFXButton create;
    @FXML
    private JFXButton My;
    @FXML
    private JFXButton home;
    @FXML
    private JFXButton wrk;
    @FXML
    private Label Clubs;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        
        wrk.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/WorkshopAffich.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        create.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/Club2.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(mainFx.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        My.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/ListClub.fxml"));
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
        ThemeServices TS = ThemeServices.getInstance();
        ClubService CS = ClubService.getInstance();
        ObservableList<Theme> ls = FXCollections.observableArrayList();
        ls = TS.DisplayAll();
        tBox.getItems().addAll(ls);

        Submit.setOnAction(event -> {
            String Regex = "^(.+)@(.+)$";
            if ((!email.getText().matches(Regex))
                    ||(email.getText().isEmpty())
                    ||(nameC.getText().isEmpty())
                    ||(desc.getText().isEmpty())
                    ||(fb.getText().isEmpty()))
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Data invalid");
            alert.show();
            }else{
            Club p = new Club(nameC.getText(), desc.getText(), email.getText(), fb.getText(), tBox.getValue().getId_theme(),Session.current_user.getId_user());
            System.out.println(p.getTheme());
            CS.insert(p);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Theme added !!!");
            alert.show();
            nameC.setText("");
            desc.setText("");
            email.setText("");
            fb.setText("");
            tBox.setPromptText("Select a theme");
            }
            
            
            

        });

        

    }
}
