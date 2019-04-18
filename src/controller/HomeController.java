/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import entity.event;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.Stage;
import service.EventService;

/**
 * FXML Controller class
 *
 * @author ihebc_000
 */
public class HomeController implements Initializable {
    
    private Label label_news;
    @FXML
    private JFXListView<event> AllEvent;
      private ObservableList<event> list;
    @FXML
    private Label NewsLetter;
    
   
   static class cell extends ListCell<event>{
        HBox box= new HBox(70);
      
        Label lab=new  Label();
        Label lab1=new Label();
        Label lab2=new Label();
        Label lab3=new Label();
        Label lab4=new Label();
        Label lab5=new Label();
        JFXButton btn3 =new JFXButton("Participate");
        Pane p= new Pane(); 
        
        ImageView eventpicc=new ImageView();
          
          
        public cell() {
            super();
            box.getChildren().addAll(eventpicc,lab,lab4,lab5,lab2,lab3,p,btn3);
            box.setHgrow(p, Priority.ALWAYS);
        }
         @Override
        public void updateItem(event event  , boolean empty){
        
            super.updateItem(event, empty);
            setText(null);
            setGraphic(null);
            if(event != null && !empty){
            Image im=new Image("http://localhost/PIDEV/dorsaf/TechEvent/web/img/"+event.getPhoto());
            eventpicc.setImage(im);
            eventpicc.setFitWidth(60);
            eventpicc.setFitHeight(60);
            lab.setText(event.getEvent_name());
            lab5.setText(event.getAddress());
            lab3.setText(event.getStart_date().toString());
           
            setGraphic(box);
            
            }
            
            
         }   }
        
        
        

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
        
        EventService ed= EventService.getInstance();
       list=FXCollections.observableArrayList(); 
       ObservableList<event>  Local=FXCollections.observableArrayList();
       list=ed.DisplayAll();
       for(int i=1;i<list.size();i++){
           if(list.get(i).getStatus().equalsIgnoreCase("ACCEPTED")){
              Local.add(list.get(i)); 
           }         
          
       }

       
       
       AllEvent.setItems(Local);
       AllEvent.setCellFactory(s->new AccueilController.cell()); 
        
        
    }    
    
}
