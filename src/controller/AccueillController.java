/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class AccueillController implements Initializable {

    @FXML
    private JFXButton Theme;
    @FXML
    private JFXButton Club;
    @FXML
    private JFXButton Work;
    @FXML
    private JFXButton Admin;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Theme.setOnAction(event -> {
            try {
                Parent TPage = FXMLLoader.load(getClass().getResource("/views/clubView.fxml"));
                Scene scene=new Scene(TPage);
                Stage stage=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueillController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            });
        Club.setOnAction(event -> {
            try {
                Parent CPage = FXMLLoader.load(getClass().getResource("/views/Club2.fxml"));
                Scene sceneC=new Scene(CPage);
                Stage stageC=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stageC.setScene(sceneC);
                stageC.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueillController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            });
        Work.setOnAction(event -> {
            try {
                Parent lPage = FXMLLoader.load(getClass().getResource("/views/ListClub.fxml"));
                Scene scene2=new Scene(lPage);
                Stage stage2=(Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();
                stage2.setScene(scene2);
                stage2.show();
            } catch (IOException ex) {
                Logger.getLogger(Club2Controller.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            });
        
        Admin.setOnAction(event -> {
            try {
                Parent lPage = FXMLLoader.load(getClass().getResource("/views/adminClub.fxml"));
                Scene scene2=new Scene(lPage);
                Stage stage2=(Stage) ((Node) event.getSource())
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
