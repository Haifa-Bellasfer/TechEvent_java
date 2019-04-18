/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import static controller.AddEventController.ConvertFileImage;
import entity.event;
import java.awt.image.BufferedImage;
import java.io.File;
import service.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.lang3.RandomStringUtils;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class AccueilController implements Initializable {

    @FXML
    private ImageView addevent;
    @FXML
    private ImageView myevents;
    @FXML
    private ImageView Favorit;
     
    private ObservableList<event> Allevent;
    
    @FXML
    private JFXTextField search;
    @FXML
    private ListView<event> allevent;

    @FXML
    private void affiche(MouseEvent event) {
        if (event.getClickCount()==2) {
            
              try {
                  Session.current_event= allevent.getSelectionModel().getSelectedItem();
                  
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Description.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
            
            
        }
    }

    @FXML
    private void Clubs(MouseEvent event) {
    }

    @FXML
    private void NewsLetter(MouseEvent event) {
    }

    @FXML
    private void Logout(MouseEvent event) {
    }
 

   
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
            box.getChildren().addAll(eventpicc,lab,lab4,lab5,lab2,lab3,p);
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
            
            
         }   
        
        
        
        
        
            
        
        }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       EventService ed= EventService.getInstance();
       Allevent=FXCollections.observableArrayList(); 
       ObservableList<event>  Local=FXCollections.observableArrayList();
       Allevent=ed.DisplayAll();
       for(int i=1;i<Allevent.size();i++){
           if(Allevent.get(i).getStatus().equalsIgnoreCase("ACCEPTED")){
              Local.add(Allevent.get(i)); 
           }         
          
       }

       
       
       allevent.setItems(Local);
       allevent.setCellFactory(s->new cell());
        
        
        
        
          
        addevent.setPickOnBounds(true); 
        addevent.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/AddEvent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
        
        
        
          
        myevents.setPickOnBounds(true); 
        myevents.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/MyEventList.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
        
        
        Favorit.setPickOnBounds(true);
        Favorit.setOnMouseClicked((MouseEvent e) -> {
                try {
                    
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Myevent.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
        
     
        
      
   
       
        
        
          
        
        
      
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        

    }    


    @FXML
    private void AddNewEvent(MouseEvent event) {
        
    }

   
}
