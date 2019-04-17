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
import javafx.stage.Stage;
import service.ClubService;
import service.ThemeServices;

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
    @FXML
    private JFXButton liste;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

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
            Club p = new Club(nameC.getText(), desc.getText(), email.getText(), fb.getText(), tBox.getValue().getId_theme());
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

        liste.setOnAction(event -> {
            try {
                Parent lPage = FXMLLoader.load(getClass().getResource("/views/clubListView.fxml"));
                Scene scene2 = new Scene(lPage);
                Stage stage2 = (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage2.setScene(scene2);
                stage2.show();
            } catch (IOException ex) {
                Logger.getLogger(Club2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }
}
