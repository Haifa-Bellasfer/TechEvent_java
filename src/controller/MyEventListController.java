/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import entity.Category;
import entity.event;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import service.CategoryService;

/**
 * FXML Controller class
 *
 * @author Dorsaf
 */
public class MyEventListController implements Initializable {

    @FXML
    private Pane space;
    @FXML
    private TableView<event> eventtab;
    @FXML
    private TableColumn<event, String> name;
    
    private ObservableList<event> Allevent;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
       EventService ed= EventService.getInstance();
       Allevent=FXCollections.observableArrayList();  
       Allevent=ed.DisplayAll();
       eventtab.setItems(Allevent);
       name.setCellValueFactory(new PropertyValueFactory<>("Event_name"));
     
       
       eventtab.setOnMouseClicked(event->{
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
            event e=eventdao.DisplayById(id);
         
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
           int cId=catlist.get(category.getSelectionModel().getSelectedIndex()).getId_category();
           e.setCategory_id(cId);
           eventdao.update(e);
          
           
        });
      
     delete.setOnAction(event -> {
           EventService eventdao=EventService.getInstance();
           event e=eventdao.DisplayById(id);
           eventdao.delete(e);
           eventtab.setItems(null);
           Allevent=ed.DisplayAll();
           eventtab.setItems(Allevent);
           
        });

       
        
        
    } 
    
}
