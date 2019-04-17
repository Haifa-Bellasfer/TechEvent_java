/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.event_likes;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        EventService e=EventService.getInstance();
        Session.current_event=e.DisplayById(19);
       
        
       EventName.setText(Session.current_event.getEvent_name());
       Organizer.setText(Integer.toString(Session.current_event.getOrganizer_id()));
       Description.setText(Session.current_event.getDescription());
       DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
       startdate.setText(df.format(Session.current_event.getStart_date()));
       enddate.setText(df.format(Session.current_event.getEnd_date()));
       Price.setText(Double.toString(Session.current_event.getPrice_ticket()));
       nblike.setText(Integer.toString(Session.current_event.getNb_like()));
       
       
       like.setPickOnBounds(true); 
       like.setOnMouseClicked((MouseEvent event) -> {
        
     
             
        });
       
       
       
       
       
       
       
       
       
       
       
       
    }    
    
}
