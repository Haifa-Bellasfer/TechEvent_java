/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextArea;
import entity.Club;
import entity.ClubUser;
import entity.Theme;

import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import service.ClubService;
import service.MemberService;
import service.ThemeServices;

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
    MemberService cc = MemberService.getInstance();
    @FXML
    private JFXButton bt;
    @FXML
    private JFXTextArea why;
    @FXML
    private JFXTextArea skills;
    @FXML
    private JFXComboBox<String> you ;
    @FXML
    private JFXButton submit;
    @FXML
    private TextField search;
    @FXML
    private JFXButton ser;
    @FXML
    private ComboBox<Theme> seC;

    @FXML
    private void join(MouseEvent event) {
        bt.setVisible(true);
    }
            ThemeServices TS = ThemeServices.getInstance();
        
        ObservableList<Theme> th = FXCollections.observableArrayList();

    ObservableList<String> y =FXCollections.observableArrayList("Student","Organization","pro");
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        th = TS.DisplayAll();
        seC.getItems().addAll(th);
        
        bt.setVisible(false);
        why.setVisible(false);
        skills.setVisible(false);
        you.setVisible(false);
        submit.setVisible(false);
        
        ls=cs.DisplayAll();
        for (int i = 0; i < ls.size(); i++) {
            String s =ls.get(i).affiche();
            ss.add(s);
            list.setItems(ss);
        }
        
        bt.setOnAction(e ->{
            bt.setVisible(false);
            list.setVisible(false);
            why.setVisible(true);
            skills.setVisible(true);
            you.setVisible(true);
            you.setItems(y);
            submit.setVisible(true);
            
            
            submit.setOnAction(ev->{
                
                if ((why.getText().isEmpty())
                    ||(you.getValue().isEmpty())
                    ||(skills.getText().isEmpty()))
                    
            {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Info");
            alert.setHeaderText(null);
            alert.setContentText("Data invalid");
            alert.show();
            }else{
                
                
                
                ClubUser cu = new ClubUser(why.getText(),you.getValue(),skills.getText(),3,"Waiting",ls.get(list.getSelectionModel().getSelectedIndex()).getId_club());
                cc.insert(cu);
                bt.setVisible(false);
                list.setVisible(true);
                why.setVisible(false);
                skills.setVisible(false);
                you.setVisible(false);
                submit.setVisible(false);
        
                ls=cs.DisplayAll();
                for (int i = 0; i < ls.size(); i++) {
                    String s =ls.get(i).affiche();
                    ss.add(s);
                    list.setItems(ss);
                }
                } });
            
                
        });
            ser.setOnAction(e-> {
                ObservableList<Club> searchl = FXCollections.observableArrayList();
                ObservableList<Club> searchT = FXCollections.observableArrayList();
                searchl = cs.searchClub(search.getText());
                list.getItems().clear();
                for (int i = 0; i < searchl.size(); i++) {
                    String s =searchl.get(i).affiche();
                    ss.add(s);
                    list.setItems(ss);
               }
                searchT= cs.searchByTheme( seC.getValue());
                for (int i = 0; i < searchT.size(); i++) {
                    String s =searchT.get(i).affiche();
                    ss.add(s);
                    list.setItems(ss);
               }
            });
                
        
    }

    
}
