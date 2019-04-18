/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.event;
import entity.event_likes;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.EventService;
import service.Event_likeService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class DescriptionController implements Initializable {

    @FXML
    private ImageView photo;
    @FXML
    private Label EventName;
    @FXML
    private Label Description;
    @FXML
    private Label Organizer;
    @FXML
    private Label Price;
    @FXML
    private Label startdate;
    @FXML
    private Label enddate;
    @FXML
    private JFXButton book;
    @FXML
    private ImageView like;
    @FXML
    private Label nblike;
    @FXML
    private ImageView back;
    @FXML
    private Label address;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventService e=EventService.getInstance();
        Session.current_event=e.DisplayById(Session.current_event.getId_event());
  
       
       
       EventName.setText(Session.current_event.getEvent_name());
        System.out.println(Session.current_event.getEvent_name());
        
        
       Organizer.setText(Integer.toString(Session.current_event.getOrganizer_id()));
       Description.setText(Session.current_event.getDescription());
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       startdate.setText(df.format(Session.current_event.getStart_date()));
       enddate.setText(df.format(Session.current_event.getEnd_date()));
       Price.setText(Double.toString(Session.current_event.getPrice_ticket()));
       nblike.setText(Integer.toString(Session.current_event.getNb_like()));
       Image im=new Image("http://localhost/PIDEV/dorsaf/TechEvent/web/img/"+Session.current_event.getPhoto());
       photo.setImage(im);
       photo.setFitHeight(200);
       photo.setFitWidth(150);
       like.setPickOnBounds(true); 
       like.setOnMouseClicked((MouseEvent event) -> {
        
     
             
        });
       
//       like.setPickOnBounds(true); 
//       like.setOnMouseClicked((MouseEvent l) -> {
//          
//            Event_likeService ev=Event_likeService.getInstance();
//             event_likes o=ev.DisplayById(Session.current_event.getId_event());
//            if(o!=null){
//               event evn= e.DisplayById(o.getEvent_id());
//                ev.deletelike(evn,Session.user);
//               nblike.setText(Integer.toString(Session.current_event.getNb_like()-1));
//                
//            }else
//            {   
//                 ev.insert(o);
//                 Session.current_event.setNb_like(Session.current_event.getNb_like()+1);
//                 e.update(Session.current_event);
//                 nblike.setText(Integer.toString(Session.current_event.getNb_like())+1);
//            }
//            
//                
//           
//        });
//        
       
       
       
       
       
       
       
       
       
       
    }    
    
}
