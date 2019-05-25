/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ThemeServices;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author mbare
 */
public class ThemeViewController implements Initializable {

    @FXML
    private Button btn;
   
    @FXML
    private TextField Name;

    
    
    @FXML
    private TableView<Theme> Table;
    @FXML
    private TableColumn<Theme, String> colName;
    
    ObservableList<Theme> ob = FXCollections.observableArrayList();

    ThemeServices TS = ThemeServices.getInstance();

    @FXML
    private JFXButton Del;
    @FXML
    private JFXButton btnUp;
    @FXML
    private Label label_news;
    @FXML
    private Label themes;
    @FXML
    private Label req;
    @FXML
    private Label label_users;
    @FXML
    private JFXButton btnlogout;
    @FXML
    private Label label_clubs;
    @FXML
    private Label event;
    @FXML
    private Label Report;
         
    public void affT(){
        ob=TS.DisplayAll();
        colName.setCellValueFactory(new PropertyValueFactory<>("theme_name"));
        Table.setItems(ob); 
    }
    
      
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
         Report.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/AdminDashborad.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        event.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/Chart.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        label_clubs.setOnMouseClicked(e -> {
            try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/adminClub.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
 
        btnlogout.setOnAction(e -> {
                Session.current_user=null;
                 try {
                Parent page = FXMLLoader.load(getClass().getResource("/view/News.fxml"));
                Scene scene = new Scene(page);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(NewsController.class.getName()).log(Level.SEVERE, null, ex);
            }
            });
        //navbar
        label_news.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Domain.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });


        label_users.setOnMouseClicked((MouseEvent e) -> {
            try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/UsersList.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(DomainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        themes.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/clubView.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ThemeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        req.setOnMouseClicked(ev->{
              try {
                    Parent page1 = FXMLLoader.load(getClass().getResource("/view/adminClub.fxml"));
                    Scene scene = new Scene(page1);
                    Stage stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                    stage.setScene(scene);
                    stage.setResizable(false);
                    stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ThemeViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
                });
        
        affT();
        
        
        btn.setOnAction(event -> {
            
            Theme p = new Theme(Name.getText());
            
            TS.insert(p);
        
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Info");
        alert.setHeaderText(null);
        alert.setContentText("Theme added !!!");
        alert.show();
        Name.setText("");
        affT();
        });
         Del.setOnAction(event ->{
            Theme d;
            int i;
        i = Table.getSelectionModel().getSelectedItem().getId_theme();
        d = TS.DisplayById(i);
             if (TS.Exist(i)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Be carefull");
                alert.setHeaderText(null);
                alert.setContentText("You have to delete the clubs related to this theme first");
                alert.show();
             }else {
             TS.delete(d);
             } 
        
        affT();
        });
        
        btnUp.setOnAction(event ->{
            Theme d;
            int i;
        d = Table.getSelectionModel().getSelectedItem();
        d.setTheme_name(Name.getText());
        TS.update(d);
        Name.setText("");
        affT();
        });
        
        
        
        
       
       
    }    


   
    
}
