/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import entity.Category;
import entity.event;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.PieChart.Data;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import service.CategoryService;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author wiemhjiri
 */
public class PieChartViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private PieChart pieChart;
    ObservableList<Data> list=FXCollections.
            observableArrayList();
    @FXML
    private JFXComboBox<Category> category;
    private  ObservableList<Category> catlist=FXCollections.observableArrayList();  
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton add;
    @FXML
    private JFXButton update;
    @FXML
    private JFXTextField text;
    @FXML
    private Label Archive;
    @FXML
    private Label events;
    @FXML
    private Label Home;

    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
       
        

        EventService pdao=EventService.getInstance();
        List<event> pers=pdao.DisplayAll();
        for(event p:pers) {
            list.addAll(
                new Data(p.getEvent_name(), p.getNb_like()) 
        );
        }
        pieChart.setAnimated(true);
        pieChart.setData(list);
        
        
      
        
        CategoryService c=CategoryService.getInstance();
        catlist=c.DisplayAll();
        category.getItems().addAll(catlist);
        
        //text.setText(this.category.getSelectionModel().getSelectedItem().toString());
         
       System.out.println(this.category.getSelectionModel().getSelectedItem());

   
        
              
      add.setOnAction(event -> {
           Category cat=new Category(1,text.getText());
           c.insert(cat);
           category.setItems(null);
           catlist=c.DisplayAll();
           category.setItems(catlist);
            
           
          
        });
       
        
      update.setOnAction(event -> {
          
           int cId=catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
           Category c1=c.DisplayById(cId);
           c1.setCategory_name(text.getText());
           c.update(c1);
           category.setItems(null);
           catlist=c.DisplayAll();
           category.setItems(catlist);
            
           
        });
      
     delete.setOnAction(event -> {
            int cId=catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
           Category c1=c.DisplayById(cId);
           c.delete(c1);
           category.setItems(null);
           catlist=c.DisplayAll();
           category.setItems(catlist);
            
           
        });
        
        
        
        
    }

    @FXML
    private void Notify(MouseEvent event) {
        
        
         try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/UserEvents.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PieChartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }     
        
        
        
        
        
         ObservableList<event>  ev=FXCollections.observableArrayList();
                      EventService es=EventService.getInstance();
                     
                      int nb=es.DisplayCountrep();
      Notifications NotifBuild = Notifications.create().title("Notify").text("Hello Manager you have "+nb+" Events Suggestion ")
              .graphic(null).hideAfter(javafx.util.Duration.seconds(10)).position(Pos.TOP_RIGHT)
                       .onAction(new EventHandler<ActionEvent>() {
                   @Override
                   public void handle(ActionEvent event) {
                      
                       
                   }
               });
               NotifBuild.showConfirm();

        
               
               
            
               
               
    }

    @FXML
    private void archive(MouseEvent event) {
        
          try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Archive.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(PieChartViewController.class.getName()).log(Level.SEVERE, null, ex);
            }     
               
        
    }

}
