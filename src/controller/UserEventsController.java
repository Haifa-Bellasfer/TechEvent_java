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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
import javafx.scene.control.Button;
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
 * @author Dorsaf
 */
public class UserEventsController implements Initializable {

    @FXML
    private JFXListView<event> userList;
    @FXML
    private ImageView back;
   ObservableList<event> Allevent=FXCollections.observableArrayList();
    EventService es= EventService.getInstance();

    @FXML
    private void back(MouseEvent event) {
    }
    
   static class cell extends ListCell<event>{
        HBox box= new HBox(10);
         EventService es= EventService.getInstance();
        Label lab=new  Label();
        Label lab1=new Label();
        Label lab2=new Label();
        Label lab3=new Label();
        Label lab4=new Label();
        Label lab5=new Label();
        JFXButton btn3 =new JFXButton("Accepete");
        JFXButton btn4 =new JFXButton("Refuse");
        Pane p= new Pane(); 
        
        ImageView eventpicc=new ImageView();
          
          
        public cell() {
            super();
            box.getChildren().addAll(eventpicc,lab,lab4,lab5,lab2,p,btn3,btn4);
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
            eventpicc.setFitWidth(50);
            eventpicc.setFitHeight(50);
            lab.setText(event.getEvent_name());
            lab5.setText(event.getStart_date().toString());
            lab2.setText(event.getEnd_date().toString());
            lab4.setText(event.getAddress());
            
            
            
            btn3.setOnAction((e) -> {
              
               es.updateStatus(event);
                
                
            });
            
            
            
            btn4.setOnAction((e) -> {
              
               es.delete(event);
                
                
            });
            
            
            
            
            
            
            
            
            
            
            
            
             setGraphic(box);
              
           
            
            }
            
            
         }   
        
        
        
        
        
            
        
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventService ed= EventService.getInstance();
       ObservableList<event>  Local=FXCollections.observableArrayList();  
       Allevent=FXCollections.observableArrayList();  
       Allevent=ed.DisplayAll();
       for(int i=1;i<Allevent.size();i++){
           if(Allevent.get(i).getStatus().equalsIgnoreCase("WAITING")){
              Local.add(Allevent.get(i)); 
           }         
          
       }
       
       userList.setItems(Local);
       userList.setCellFactory(s->new cell());
        
       
       
              
        back.setPickOnBounds(true); 
        back.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Chart.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
       
       
       
       
       
       
       
       
       
}}
