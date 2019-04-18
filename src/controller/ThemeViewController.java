/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeView;
import entity.Theme;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import service.ThemeServices;

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
    private Label label_news1;
    @FXML
    private Label label_theme;
         
    public void affT(){
        ob=TS.DisplayAll();
        colName.setCellValueFactory(new PropertyValueFactory<>("theme_name"));
        Table.setItems(ob); 

    }
    
      
   
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
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
