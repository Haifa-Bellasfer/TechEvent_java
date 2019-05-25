/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import com.jfoenix.controls.JFXButton;
import entity.Category;
import entity.Event;
import service.EventService;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.CategoryService;
import service.Event_likeService;
import utils.Session;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class MyEventListController implements Initializable {

    @FXML
    private Pane space;
    @FXML
    private TableView<Event> eventtab;
    @FXML
    private TableColumn<Event, String> name;
    
    private ObservableList<Event> Allevent;
    @FXML
    private TextField address;
    @FXML
    private Button update;
    @FXML
    private Button delete;
    @FXML
    private TextField eventName;
    private  ObservableList<Category> catlist=FXCollections.observableArrayList();  
    private int id;
    @FXML
    private ComboBox<Category> category;
    @FXML
    private DatePicker startdate;
    @FXML
    private DatePicker enddate;
    @FXML
    private TextField price;
    @FXML
    private TextField nbp;
    private  CategoryService c=CategoryService.getInstance();
    ObservableList<Event>  Local=FXCollections.observableArrayList();
    @FXML
    private JFXButton back;
   
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
        
        
       
        
       EventService ed= EventService.getInstance();
 
       Allevent=ed.DisplayAll();
       for(int i=0;i<Allevent.size();i++){
           if(Allevent.get(i).getOrganizer_id() == Session.current_user.getId_user()){
              Local.add(Allevent.get(i)); 
           }       }  
          eventtab.setItems(Local);
       name.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
        
       
       eventtab.setOnMouseClicked(event->{
        
       name.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
        
           
        address.setText((Allevent.get(eventtab.getSelectionModel().getSelectedIndex())
                .getAddress()));
        
       eventName.setText((Allevent.get(eventtab.getSelectionModel().getSelectedIndex())
                .getEvent_name()));
       
       startdate.setValue((Allevent.get(eventtab.getSelectionModel().getSelectedIndex()).getStart_date().toLocalDate()));
       enddate.setValue((Allevent.get(eventtab.getSelectionModel().getSelectedIndex()).getEnd_date().toLocalDate()));    
       
       price.setText(Double.toString(Allevent.get(eventtab.getSelectionModel().getSelectedIndex())
                .getPrice_ticket()));
       
       nbp.setText(Integer.toString(Allevent.get(eventtab.getSelectionModel().getSelectedIndex())
                .getNb_participant()));
       
      
    
      
       catlist=c.DisplayAll();
      
       category.getItems().addAll(catlist);
       
       id=Allevent.get(eventtab.getSelectionModel().getSelectedIndex())
                .getId_event();
       
       });
        
      update.setOnAction(event -> {
            EventService eventdao=EventService.getInstance();
            Event e=eventdao.DisplayById(id);
         
            e.setAddress(address.getText());
            LocalDate start= startdate.getValue();
            Date startdate = Date.valueOf(start);
            LocalDate end= enddate.getValue();
            Date enddate = Date.valueOf(end); 
            e.setDescription(e.getDescription());
            e.setEnd_date(enddate);
            e.setEvent_name(eventName.getText());
            e.setStart_date(startdate);
            e.setNb_participant(Integer.parseInt(nbp.getText()));
            e.setPrice_ticket(Double.parseDouble(price.getText()));
           
           if(!category.getSelectionModel().isEmpty()){
            int cId=catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
           e.setCategory_id(cId);
           }
           eventdao.update(e);
       
          eventtab.getItems().clear();
          
           Allevent=ed.DisplayAll();
          for(int i=0;i<Allevent.size();i++){
           if(Allevent.get(i).getOrganizer_id() == Session.current_user.getId_user()){
              Local.add(Allevent.get(i)); 
           } }        
          eventtab.setItems(Local);
       name.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
          
            
          
           
        });
      
     delete.setOnAction(event -> {
           EventService eventdao=EventService.getInstance();
           Event e=eventdao.DisplayById(id);
           Event_likeService es=Event_likeService.getInstance();
           if(es.DisplayById(id) !=null){
               
               Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Alert");
                    alert.setHeaderText(null);
                    alert.setContentText("First You need to delete All the users Likes");
                    alert.show();
            }else{
                eventdao.delete(e);
               
             }  
           
           
     
             try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/MyEventList.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
     
           
        });

       
   
        
       
        back.setOnMouseClicked((MouseEvent e) -> {
                try {
                Parent page1 = FXMLLoader.load(getClass().getResource("/view/Accueil.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                Logger.getLogger(AccueilController.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        });
        
     
     
        
        
    } }
    

